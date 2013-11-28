package com.ecen602.musync;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class JoinStationActivity extends Activity {
	SlaveCommunicator slaveCommunicator;
	private ImageButton playButton, pauseButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_station);
		
		playButton = (ImageButton) findViewById(R.id.playButton);
		pauseButton = (ImageButton) findViewById(R.id.pauseButton);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join_station, menu);
		return true;
	}
	

	public void onPlayPressed(View v){
		playButton.setVisibility(View.INVISIBLE);
		pauseButton.setVisibility(View.VISIBLE);
		
		slaveCommunicator = new SlaveCommunicator(this);
		slaveCommunicator.init();
	}

	public void onPausePressed(View v){
		playButton.setVisibility(View.VISIBLE);
		pauseButton.setVisibility(View.INVISIBLE);
	}	

}
