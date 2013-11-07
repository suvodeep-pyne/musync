package com.ecen602.musync;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MasterCommunicatorThread extends Thread {
	private Socket socket = null;
	 
    public MasterCommunicatorThread(Socket socket) {
        super("MasterCommunicatorThread");
        this.socket = socket;
    }
     
    public void run() {
    	try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(new Packet());                
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
