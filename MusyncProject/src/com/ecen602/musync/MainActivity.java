package com.ecen602.musync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** Called when the user clicks the create_station button */
	public void createStation(View view) {
		Intent intent = new Intent(this, CreateStationActivity.class);
		startActivity(intent);
	}
	
	/** Called when the user clicks the join_station button */
	public void joinStation(View view) {
		Intent intent = new Intent(this, JoinStationActivity.class);
		startActivity(intent);
	}
}
