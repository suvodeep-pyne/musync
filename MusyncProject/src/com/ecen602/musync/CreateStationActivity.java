package com.ecen602.musync;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateStationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_station);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_station, menu);
		return true;
	}

}
