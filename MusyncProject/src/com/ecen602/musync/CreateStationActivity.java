package com.ecen602.musync;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;

public class CreateStationActivity extends Activity {
	CommunicationMaster communicationMaster;
	Thread communicationMasterThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_station);
		
		MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), 
				R.raw.pherari_mon);
		mediaPlayer.start();
		
		communicationMaster = new CommunicationMaster();
		communicationMasterThread = new Thread(communicationMaster);
		communicationMasterThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_station, menu);
		return true;
	}

}
