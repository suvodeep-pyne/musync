package com.ecen602.musync;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class MasterCommunicator {
	private Thread listener, sender;
	private ServerSocket listenerSocket = null;
	
	boolean listening = true;
	final List<ClientHandler> clients = new ArrayList<ClientHandler>();
	
	public MasterCommunicator() {
		init();
	}

	public void init() {
		listener = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (listenerSocket == null)
						listenerSocket = new ServerSocket(Constants.PORT);
					
					while (listening) {
						Socket socket = listenerSocket.accept();
						if (socket != null) {
							
							final ClientHandler ch = new ClientHandler(socket);
							ch.setup();
							clients.add(ch);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (listenerSocket != null) 
							listenerSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread sender = new Thread(new Runnable() {
			@Override
			public void run() {
				send();
			}
		}, "MasterCommunicator Sender");
	}
	
	public void startListener() {
		listener.start();
	}
	
	public void sendMessage() {
		sender.start();
	}
	private void send() {
		List<ClientHandler> remove = new ArrayList<ClientHandler>();
		
		for (ClientHandler ch : clients) {
			try {
				ch.send();
			} catch (IOException e) {
				Log.w("Musync", "Can't send to client. Removing from list");
				remove.add(ch);
				ch.close();
			}
		}
		
		clients.removeAll(remove);
	}

}
