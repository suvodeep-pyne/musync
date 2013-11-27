package com.ecen602.musync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.util.Log;

public class MasterCommunicatorThread extends Thread {
	private Socket socket = null;

	public MasterCommunicatorThread(Socket socket) {
		super("ListenerThread");
		this.socket = socket;
	}

	public void run() {
		try {
			ObjectOutputStream objectOutput = new ObjectOutputStream(
					socket.getOutputStream());
			objectOutput.writeObject(new Packet());

			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Server: " + inputLine);
				Log.w("Musync", inputLine);

				if (inputLine.equals("Bye."))
					break;
			}

			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
