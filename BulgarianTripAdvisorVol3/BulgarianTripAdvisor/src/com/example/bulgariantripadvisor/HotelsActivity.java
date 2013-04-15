package com.example.bulgariantripadvisor;

import com.example.bulgariantripadvisor.HotelsAdapter;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;


public class HotelsActivity extends ListActivity{
	
	static final String[] CITIES= new String[] {"Sofia"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new HotelsAdapter(this, CITIES));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		String item = (String) getListAdapter().getItem(position);
		
		if (item.equals("Sofia"))
		{
			Intent intent = new Intent(this, CityActivity.class);
			intent.putExtra("tablename", item);
			startActivity(intent);
		}
			
	}


}
