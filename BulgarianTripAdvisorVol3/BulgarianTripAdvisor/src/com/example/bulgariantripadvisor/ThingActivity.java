package com.example.bulgariantripadvisor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ThingActivity extends Activity {

	private ThingsHelper dbIngredientHelper = null;
	private Cursor ourCursor = null;
	private String name;
	private String tablename = "Sofia";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thing);
		
		Intent intent = this.getIntent();
		name = intent.getExtras().getString("thingname");
		tablename = intent.getExtras().getString("tablename");
		
		setBackgrount();  
		
		try{
			  dbIngredientHelper=new ThingsHelper(this,tablename); 
			  dbIngredientHelper.createDatabase(); 
			  dbIngredientHelper.openDataBase();
			  ourCursor=dbIngredientHelper.getCursor();
              while(ourCursor.moveToNext() ){
            	  String something = dbIngredientHelper.getName(ourCursor).toString();
            	  if ( something.equals(name)){
					  String description = dbIngredientHelper.getDescription(ourCursor).toString();
					  String type = dbIngredientHelper.getType(ourCursor).toString();

					  TextView textName = (TextView) findViewById(R.id.thingname);
					  textName.setText(something);
					  TextView textDescription = (TextView) findViewById(R.id.thingdescription);
					  textDescription.setText("Description: "+description);
					  TextView textPrices = (TextView) findViewById(R.id.thingtype);
					  textPrices.setText("Type: "+type);
					  break;
				  }
			  }
             			  
			  dbIngredientHelper.closeDB();
			  ourCursor.close();
			  
		}catch(Exception e) { 
			   Log.e("ERROR", "ERROR IN CODE: " + e.toString()); 
			   e.printStackTrace(); 
		}
	}

	private void setBackgrount() {
		RelativeLayout rlayout= (RelativeLayout) findViewById(R.id.thinglayout);
		if(name.equals("Alexander Nevski Church")){
		       rlayout.setBackgroundResource(R.drawable.im1);
		        this.setContentView(rlayout);
		}
		if(name.equals("Ivan Vazov National Theater")){
		       rlayout.setBackgroundResource(R.drawable.im2);
		        this.setContentView(rlayout);
		}  
		if(name.equals("Boyana Church")){
		       rlayout.setBackgroundResource(R.drawable.im3);
		        this.setContentView(rlayout);
		}  
		if(name.equals("Vitosha Mountain")){
		       rlayout.setBackgroundResource(R.drawable.im4);
		        this.setContentView(rlayout);
		}
		if(name.equals("National Opera and Ballet")){
		       rlayout.setBackgroundResource(R.drawable.im5);
		        this.setContentView(rlayout);
		} 
		if(name.equals("The Rotunda of St George (Sveti Georgi)")){
		       rlayout.setBackgroundResource(R.drawable.im6);
		        this.setContentView(rlayout);
		} 
		if(name.equals("Monument to the Unknown Warrior")){
		       rlayout.setBackgroundResource(R.drawable.im7);
		        this.setContentView(rlayout);
		} 
		if(name.equals("Vitosha Boulevard")){
		       rlayout.setBackgroundResource(R.drawable.im8);
		        this.setContentView(rlayout);
		}
		if(name.equals("Vasil Levski Monument")){
		       rlayout.setBackgroundResource(R.drawable.im9);
		        this.setContentView(rlayout);
		} 
		if(name.equals("Statue of Tsar Alexander II")){
		       rlayout.setBackgroundResource(R.drawable.im10);
		        this.setContentView(rlayout);
		} 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.hotel, menu);
		return true;
	}

}