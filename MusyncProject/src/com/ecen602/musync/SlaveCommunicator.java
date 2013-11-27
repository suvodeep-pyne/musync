package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SlaveCommunicator implements Runnable {

	Socket socket;

	@Override
	public void run() {
		try {
			socket = new Socket("127.0.0.1", 8000);
			System.out.println("Client connected to server");
			communicate();

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void communicate() {
		try {
			System.out.println("Client inside communicate");
			ObjectInputStream objectInput = new ObjectInputStream(
					socket.getInputStream());
			Packet packet;
			packet = (Packet) objectInput.readObject();
			System.out.println("Client received" + packet);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}