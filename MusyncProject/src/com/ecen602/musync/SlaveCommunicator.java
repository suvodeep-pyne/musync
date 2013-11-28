package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;

public class SlaveCommunicator implements Runnable {
	final Activity parent;
	private Socket socket;
	private ObjectInputStream input;

	public SlaveCommunicator(Activity parent) {
		this.parent = parent;
	}

	public void init() {
		try {
			socket = new Socket(Constants.SERVERIP, Constants.PORT);
			input = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (input.available() > 0) {
					Packet packet = (Packet) input.readObject();
					if (packet != null) {
						start_playback(packet);
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void start_playback(Packet packet) {
		Player player = new Player(parent.getApplicationContext());
		player.play(packet.playTime, packet.offset);

	}
}
