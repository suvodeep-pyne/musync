package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommunicationSlave implements Runnable {

	Socket socket;

	@Override
	public void run() {
		try {
			socket = new Socket("192.168.1.3", 5000);
			communicate();

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void communicate() {
		try {
			ObjectInputStream objectInput = new ObjectInputStream(
					socket.getInputStream());
			Packet packet;
			try {
				packet = (Packet) objectInput.readObject();
				System.out.println("Client received" + packet);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
