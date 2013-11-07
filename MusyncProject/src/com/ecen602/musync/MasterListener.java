package com.ecen602.musync;

import java.io.IOException;
import java.net.ServerSocket;

public class MasterListener implements Runnable {
	boolean listening = true;

	@Override
	public void run() {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(Constants.PORT);
			while (listening) {
				new MasterCommunicatorThread(listener.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
