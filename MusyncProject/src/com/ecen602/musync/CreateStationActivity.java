package com.ecen602.musync;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class CreateStationActivity extends Activity {
	private Player player;
	
	private ImageButton playButton, pauseButton;
	
	MasterCommunicator communicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_station);
		
		player = new Player(this.getApplicationContext());
		
		playButton = (ImageButton) findViewById(R.id.playButton);
		pauseButton = (ImageButton) findViewById(R.id.pauseButton);
		
		communicator = new MasterCommunicator();
		communicator.startListener();
		
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
		
		communicator.send();
	}

	public void onPausePressed(View v){
		player.pause();
		playButton.setVisibility(View.VISIBLE);
		pauseButton.setVisibility(View.INVISIBLE);
	}	
}
