package com.ecen602.musync;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JoinStationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_station);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join_station, menu);
		return true;
	}

}
