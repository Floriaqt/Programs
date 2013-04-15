package com.example.bulgariantripadvisor;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class CityActivity extends ListActivity {

	private DatabaseHelper dbIngredientHelper = null;
	private Cursor ourCursor = null;
	private ArrayList<String> hotels = new ArrayList<String>();
	public static final String NAME = "hotelname";
	public static final String Table_Name = "tablename";
	private String tablename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		tablename = intent.getExtras().getString("tablename");
		
		try{
			  dbIngredientHelper=new DatabaseHelper(this,tablename); 
			  dbIngredientHelper.createDatabase(); 
			  dbIngredientHelper.openDataBase();
			  ourCursor=dbIngredientHelper.getCursor();
			  
			  while(ourCursor.moveToNext() ){
				  hotels.add( (dbIngredientHelper.getName(ourCursor)).toString() );
			  }
			  setListAdapter(new CityAdapter(this, hotels));
			  dbIngredientHelper.closeDB();
			  
		}catch(Exception e) { 
			   Log.e("ERROR", "ERROR IN CODE: " + e.toString()); 
			   e.printStackTrace(); 
		}
			

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String hotelName = (String) getListAdapter().getItem(position);
			Intent intent = new Intent(this, HotelActivity.class);
			intent.putExtra(NAME, hotelName);
			intent.putExtra(Table_Name, tablename);
			startActivity(intent);
	}
}
