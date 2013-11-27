package com.ecen602.musync;

import java.io.Serializable;

import android.text.format.Time;

public class Packet implements Serializable{

	/** Auto generated! Do not modify! */
	private static final long serialVersionUID = 3429538255807019677L;

	// Time to start playing
	final Time playTime;
	
	// Seek time in msec
	final int offset;
	
	Packet(Time playTime, int offset) {
		this.playTime = playTime;
		this.offset = offset;
	}
}
