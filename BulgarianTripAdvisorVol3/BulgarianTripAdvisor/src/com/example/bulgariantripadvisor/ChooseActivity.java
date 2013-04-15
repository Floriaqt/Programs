package com.example.bulgariantripadvisor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ChooseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose, menu);
		return true;
	}

	public void HotelsActivity(View view){
		Intent intent = new Intent(this, HotelsActivity.class);
		startActivity(intent);
	}
	
	public void ThingsToDoActivity(View view){
		Intent intent = new Intent(this, ThingsToDoActivity.class);
		startActivity(intent);
	}
}
