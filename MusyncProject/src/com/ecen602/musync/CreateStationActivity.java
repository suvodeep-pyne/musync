package com.ecen602.musync;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CreateStationActivity extends Activity {
	private Player player = new Player(this.getApplicationContext());
	
	private Button playButton, pauseButton;
	
	MasterListener listener;
	Thread listenerThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_station);
		
		playButton = (Button) findViewById(R.id.playButton);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		
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
		player.play();
		playButton.setVisibility(View.INVISIBLE);
		pauseButton.setVisibility(View.VISIBLE);
	}

	public void onPausePressed(View v){
		player.pause();
		playButton.setVisibility(View.VISIBLE);
		pauseButton.setVisibility(View.INVISIBLE);
	}	
}
