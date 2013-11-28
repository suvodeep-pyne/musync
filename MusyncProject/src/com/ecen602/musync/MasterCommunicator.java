package com.ecen602.musync;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class MasterCommunicator {
	private Thread listener;
	
	boolean listening = true;
	final List<ClientHandler> clients = new ArrayList<ClientHandler>();

	public void startListener() {
		listener = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocket listenerSocket = null;
				try {
					listenerSocket = new ServerSocket(Constants.PORT);
					while (listening) {
						Socket socket = listenerSocket.accept();
						if (socket != null) {
							clients.add(new ClientHandler(socket));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						listenerSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		listener.start();
	}
	
	public void send() {
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
