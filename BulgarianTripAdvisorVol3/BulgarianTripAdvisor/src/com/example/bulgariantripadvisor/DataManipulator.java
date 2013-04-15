package com.example.bulgariantripadvisor;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;


public class DataManipulator {
	private static final  String DATABASE_NAME = "users.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NAME = "newtable";
	private static Context context;
	static SQLiteDatabase db;

	private SQLiteStatement insertStmt;
	
    private static final String INSERT = "insert into "
		+ TABLE_NAME + " (username,email,password) values (?,?,?)";

	public DataManipulator(Context context) {
		DataManipulator.context = context;
		OpenHelper openHelper = new OpenHelper(DataManipulator.context);
		DataManipulator.db = openHelper.getWritableDatabase();
		this.insertStmt = DataManipulator.db.compileStatement(INSERT);
		

	}
	public long insert(String username,String email,String password) {
		this.insertStmt.bindString(1, username);
		this.insertStmt.bindString(2, email);
		this.insertStmt.bindString(3, password);
		return this.insertStmt.executeInsert();
		
	}
	public void close(){
		db.close();
	}

	public List<String[]> selectAll()
	{

		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, new String[] { "id","username","email","password" },
				null, null, null, null, "username asc"); 

		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)};

				list.add(b1);

				x=x+1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		db.close();
		cursor.close();
		
		return list;
	}

	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, username TEXT, email TEXT, password TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}



}

