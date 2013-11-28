package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.media.MediaPlayer;

public class SlaveCommunicator implements Runnable {
	final Activity parent;
	Socket socket;

	public SlaveCommunicator(Activity parent) {
		this.parent = parent;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(Constants.SERVERIP, Constants.PORT);
			System.out.println("Client inside communicate");
			ObjectInputStream objectInput = new ObjectInputStream(
					socket.getInputStream());
			Packet packet = (Packet) objectInput.readObject();

			if (packet != null) {
				start_playback(packet);
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
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
