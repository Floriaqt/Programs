package com.example.bulgariantripadvisor;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class ThingsToDoActivity extends ListActivity {

	private ThingsHelper dbIngredientHelper = null;
	private Cursor ourCursor = null;
	private ArrayList<String> things = new ArrayList<String>();
	public static final String NAME = "thingname";
	private String tablename = "Sofia";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		try{
			  dbIngredientHelper=new ThingsHelper(this,tablename); 
			  dbIngredientHelper.createDatabase(); 
			  dbIngredientHelper.openDataBase();
			  ourCursor=dbIngredientHelper.getCursor();
			  
			  while(ourCursor.moveToNext() ){
				  things.add( (dbIngredientHelper.getName(ourCursor)).toString() );
			  }
			  setListAdapter(new ThingsToDoAdapter(this, things));
			  dbIngredientHelper.closeDB();
			  
		}catch(Exception e) { 
			   Log.e("ERROR", "ERROR IN CODE: " + e.toString()); 
			   e.printStackTrace(); 
		}
			

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String thingName = (String) getListAdapter().getItem(position);
			Intent intent = new Intent(this, ThingActivity.class);
			intent.putExtra(NAME, thingName);
			intent.putExtra("tablename", tablename);
			startActivity(intent);
	}
}
