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
			socket = new Socket("192.168.1.3", 8000);
			System.out.println("Client inside communicate");
			ObjectInputStream objectInput = new ObjectInputStream(
					socket.getInputStream());
			Packet packet = (Packet) objectInput.readObject();
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}