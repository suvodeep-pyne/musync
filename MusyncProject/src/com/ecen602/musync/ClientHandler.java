package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class ClientHandler {
	final Socket socket;
	ObjectOutputStream output = null;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void setup() {
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(Date now) throws IOException {
		if (output == null) 
			throw new IOException("ObjectOutputStream not initialized");
		output.writeObject(new PlayPacket(now, 0));
		output.flush();
	}

	public void close() {
		try {
			output.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
