package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

public class SlaveCommunicator {
	final Activity parent;
	private Socket socket;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;
	private static MulticastSocket multicastSocket = null;
	
	private Map<String, StationInfo> hostMap = new HashMap<String, StationInfo>();
	private Player player;


	public SlaveCommunicator(Activity parent) {
		this.parent = parent;
	}

	public void init() {
		Thread listener = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (socket == null) {
						socket = new Socket(Constants.SERVERIP, Constants.PORT);
						input = new ObjectInputStream(socket.getInputStream());
						output = new ObjectOutputStream(socket.getOutputStream());
					}

					sendTimePacket();
					
					while (true) {
						Object o = input.readObject();
						if (o instanceof TimePacket) {
							TimePacket tpacket = (TimePacket) o;
							tpacket.clientRecv = System.currentTimeMillis();
							synchronize(tpacket);
						} else if (o instanceof PlayPacket) {
							PlayPacket packet = (PlayPacket) o;
							if (packet != null) {
								start_playback(packet);
							}
						}
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "SlaveCommunicator listener");
		listener.start();
		Thread multicastThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					multicastSocket = new MulticastSocket(Constants.BROADCAST_PORT);
					InetAddress group = InetAddress.getByName(Constants.BROADCAST_IP);
					multicastSocket.joinGroup(group);
					
					while (true) {
					    byte[] buf = new byte[256];
					    DatagramPacket packet = new DatagramPacket(buf, buf.length);
					    multicastSocket.receive(packet);

					    String ip = packet.getAddress().getHostAddress();
					    StationInfo stationInfo = hostMap.get(ip);
					    if (stationInfo == null) hostMap.put(ip, stationInfo);
					    
					    stationInfo.name = new String(packet.getData());
					    stationInfo.lastseen = System.currentTimeMillis();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				multicastSocket.close();
			}
		}, "Client Multicast Receiver");
		multicastThread.start();
		player = new Player(parent.getApplicationContext());
	}

	private void start_playback(PlayPacket packet) {
		
		player.play(packet.playTime, packet.offset);
	}

	private void sendTimePacket() {
		TimePacket tp = new TimePacket();
		tp.clientSend = System.currentTimeMillis();
		
		try {
			output.writeObject(tp);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void synchronize(TimePacket tp) {
		// di = t + t’ = T2 – T1 + T4 - T3
		long delay = tp.serverRecv - tp.clientSend + 
				tp.clientRecv - tp.serverSend;
		
		// o = oi + (t’ - t )/2 where oi = (T2 - T1 + T3 – T4) /2
		// ignoring difference in t' and t 
		long offset = (tp.serverRecv - tp.clientSend + 
				tp.serverSend - tp.clientRecv) / 2;
		
		player.setSyncParams(delay, offset);
	}
}
