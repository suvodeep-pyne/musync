package com.ecen602.musync;

import java.io.Serializable;
import java.util.Date;

public class Packet implements Serializable{

	/** Auto generated! Do not modify! */
	private static final long serialVersionUID = 3429538255807019677L;

	// Time to start playing
	final Date playTime;
	
	// Seek time in msec
	final int offset;
	
	Packet(Date playTime, int offset) {
		this.playTime = playTime;
		this.offset = offset;
	}
}
