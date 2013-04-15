package com.example.bulgariantripadvisor;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CityAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<String> values = new ArrayList<String>();
	
	public CityAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.activity_city, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.activity_city, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values.get(position));

		String s = values.get(position);

		if (s.equals("Budapest Hotel")) {
			imageView.setImageResource(R.drawable.budapesthotel);
		}
		if (s.equals("Central Park Hotel")) {
			imageView.setImageResource(R.drawable.centralparkhotel);
		}
		if (s.equals("Grand Hotel Sofia")) {
			imageView.setImageResource(R.drawable.grandhotelsofia);
		}
		if (s.equals("Hilton Sofia")) {
			imageView.setImageResource(R.drawable.hiltonsofia);
		}
		if(s.equals("Holiday Inn Sofia")){
			imageView.setImageResource(R.drawable.holidayinnsofia);
		}
		if (s.equals("Hotel Anel")) {
			imageView.setImageResource(R.drawable.hotelanel);
		}
		if (s.equals("Hotel Maria Luisa")) {
			imageView.setImageResource(R.drawable.hotelmarialuisa);
		}
		if (s.equals("Metropolitan Hotel")) {
			imageView.setImageResource(R.drawable.metropolitanhotel);
		}
		if (s.equals("Radisson Blu Grand Hotel")) {
			imageView.setImageResource(R.drawable.radissonblugrandhotelsofia);
		}
		if (s.equals("Sheraton Sofia Hotel Balkan")) {
			imageView.setImageResource(R.drawable.sheratonsofiahotelbalkan);
		}
		
		return rowView;
	}
}

