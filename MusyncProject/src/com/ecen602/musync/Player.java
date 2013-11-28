package com.ecen602.musync;

import java.util.Date;

import android.content.Context;
import android.media.MediaPlayer;

public class Player {
	final Context context;
	private MediaPlayer mediaPlayer;
	
	Player(Context context) {
		this.context = context;
		mediaPlayer = MediaPlayer.create(context, R.raw.sample_song);
		
	}
	
	public void play(){
		mediaPlayer.start();
	}
	
	public void play(final Date startTime, final int offset){
		Thread playbackSchedulerThread = new Thread() {
		    public void run() {
		    	long now = System.currentTimeMillis();
		    	long scheduled = startTime.getTime();
		        
		    	int seekTime = offset;
		    	/* Already missed out on some time. Compensate with offset */
		    	if (scheduled < now) {
		    		seekTime += (int) (now - scheduled);
		    	}
		    	
		    	/* Sleep the thread if there is a long delay. 
		    	 * Else run an active loop */
		    	while (scheduled > now) {
		        	long diff = scheduled - now;
		        	if (diff > 2000) {
		        		try {
							Thread.sleep(diff - 1500);
						} catch (InterruptedException e) {}
		        	}
		        	now = System.currentTimeMillis();
		        }
		        mediaPlayer.seekTo(seekTime);
		    	mediaPlayer.start();
		    }
		};
		playbackSchedulerThread.start();
	}
	
	public void pause(){
		mediaPlayer.pause();
	}	
}
