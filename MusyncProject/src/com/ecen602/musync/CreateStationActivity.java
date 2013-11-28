package com.ecen602.musync;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class CreateStationActivity extends Activity {
	MasterListener listener;
	Thread listenerThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_station);
		
/*		MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), 
				R.raw.pherari_mon);
		mediaPlayer.start();*/
		
		listener = new MasterListener();
		listenerThread = new Thread(listener);
		listenerThread.start();
		
		Log.w("Musync", "Started ListenerThread");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_station, menu);
		return true;
	}

	public void onPlayPressed(View v){
		MediaPlayer mediaPlayer = MediaPlayer.create(
				getApplicationContext(), R.raw.sample_song);
		mediaPlayer.start();
	}
}
