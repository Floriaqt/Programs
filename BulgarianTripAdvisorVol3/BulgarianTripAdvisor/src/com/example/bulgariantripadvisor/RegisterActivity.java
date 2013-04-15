package com.example.bulgariantripadvisor;

import java.util.List;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity implements OnClickListener {  
	private DataManipulator dh;     
	static final int DIALOG_ID = 0;
	private boolean cancel;
	public List<String[]> names2 =null ;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
        View add = findViewById(R.id.Button01add);
		add.setOnClickListener(this);
		View home = findViewById(R.id.Button01home);
		home.setOnClickListener(this);           
	}

	public void onClick(View v){
		switch(v.getId()){

		case R.id.Button01home:
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			break;

		case R.id.Button01add:
			cancel = false;
			View editText1 = (EditText) findViewById(R.id.username);
			View editText2 = (EditText) findViewById(R.id.email);
			View editText3 = (EditText) findViewById(R.id.password);
			String myEditText1=((TextView) editText1).getText().toString();
			String myEditText2=((TextView) editText2).getText().toString();
			String myEditText3=((TextView) editText3).getText().toString();

			View focusView = null;
			if (TextUtils.isEmpty(myEditText3)) {
				((TextView) editText3).setError(getString(R.string.error_field_required));
				focusView = editText3;
				cancel=true;
			} else if (myEditText3.length() < 4) {
				((TextView) editText3).setError(getString(R.string.error_invalid_password));
				focusView = editText3;
				cancel=true;
			}
			if (TextUtils.isEmpty(myEditText1)) {
				((TextView) editText1).setError(getString(R.string.error_field_required));
				focusView = editText1;
				cancel=true;
			} else if (myEditText1.length() < 4) {
				((TextView) editText1).setError(getString(R.string.error_invalid_username));
				focusView = editText1;
				cancel=true;
			}
			if (TextUtils.isEmpty(myEditText2)) {
				((TextView) editText2).setError(getString(R.string.error_field_required));
				focusView = editText2;
				cancel = true;
			} else if (!myEditText2.contains("@")) {
				((TextView) editText2).setError(getString(R.string.error_invalid_email));
				focusView = editText2;
				cancel = true;
			}
			dh = new DataManipulator(this);
		    names2 = dh.selectAll();
		    View focusView2 = null;
		    for (String[] name : names2) {
				if (name[1].equals(myEditText1)) {
					((TextView) editText1).setError(getString(R.string.error_existing_username));
					focusView2 = editText1;
					cancel=true;
				}
				if (name[2].equals(myEditText2)) {
					((TextView) editText2).setError(getString(R.string.error_existing_email));
					focusView2 = editText2;
					cancel=true;
				}
				if (name[3].equals(myEditText3)) {
					((TextView) editText3).setError(getString(R.string.error_existing_password));
					focusView2 = editText3;
					cancel=true;
				}
		    }		

			if (!cancel){
				this.dh = new DataManipulator(this);
				this.dh.insert(myEditText1,myEditText2,myEditText3);
				this.dh.close();
				//showDialog(DIALOG_ID);
				Intent intent = new Intent(this, ChooseActivity.class);
			    startActivity(intent);
			}

            break;

		}
	}  
	/*
	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch(id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Information saved successfully !")
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					RegisterActivity.this.finish();

              }
			});
			AlertDialog alert = builder.create(); 
			dialog = alert;
			break;

		default:

		}
		return dialog;
	}
*/

}

