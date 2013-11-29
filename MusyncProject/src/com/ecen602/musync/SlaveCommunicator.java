package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;

public class SlaveCommunicator {
	final Activity parent;
	private Socket socket;
	private ObjectInputStream input = null;

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
					}

					while (true) {
						PlayPacket packet = (PlayPacket) input.readObject();
						if (packet != null) {
							start_playback(packet);
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
	}

	private void start_playback(PlayPacket packet) {
		Player player = new Player(parent.getApplicationContext());
		player.play(packet.playTime, packet.offset);
	}
}
