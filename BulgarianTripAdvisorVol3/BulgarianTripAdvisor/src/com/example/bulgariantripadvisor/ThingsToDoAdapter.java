package com.example.bulgariantripadvisor;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThingsToDoAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<String> values = new ArrayList<String>();
	
	public ThingsToDoAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.activity_things_to_do, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.activity_things_to_do, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.thing);
		textView.setText(values.get(position));

		String s = values.get(position);
		
		return rowView;
	}
}