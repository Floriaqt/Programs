package com.example.bulgariantripadvisor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	
	private static final String DATABASE_PATH = "/data/data/com.example.bulgariantripadvisor/databases/";
	private static final String DATABASE_NAME = "hotels.db";
	private static final int SCHEMA_VERSION = 1;
	public String TABLE_NAME;
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME ="name";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_PRICES = "prices";
	public static final String COLUMN_INCLUDES = "includes";
	public static final String COLUMN_CLASS = "class";
	public static final String COLUMN_CONTACT = "contact";
	
	
	
	public SQLiteDatabase dbSQLite;
	private Context myContext;
	
	public DatabaseHelper (Context context,String tablename){
		super(context,DATABASE_NAME,null,SCHEMA_VERSION);
		TABLE_NAME=tablename;
		this.myContext=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
	
	public void createDatabase(){
		createDB();
	}
	private void createDB(){
		boolean dbExists=DBExist();
		if(!dbExists){
			this.getReadableDatabase();
			copyDBFromResource();
		}
	}
		
	private boolean DBExist(){
		SQLiteDatabase db= null;
		try{
			/*String databasePath = DATABASE_PATH + DATABASE_NAME;
			db=SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
			db.setLocale(Locale.getDefault());
			db.setLockingEnabled(true);
			db.setVersion(1);*/
			File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
		    return dbFile.exists();
		}catch (SQLiteException e){
			Log.e("SQLiteHelper","database not found");
		}
		//if (db!=null){
			//db.close();
		//}
		return db!=null ? true:false;
	}

	private void copyDBFromResource(){
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String dbFilePath = DATABASE_PATH + DATABASE_NAME;
		
		try{
			
			inputStream = myContext.getAssets().open(DATABASE_NAME);
			outputStream = new FileOutputStream(dbFilePath);
			
			byte[] buffer = new byte[1024];
			int lenght;
			while ( (lenght = inputStream.read(buffer) )>0 ){
				outputStream.write(buffer,0,lenght);
			}
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			
		}catch (IOException e){
			throw new Error ("Problem copying database from resource file");
		}
	}
	
	public void openDataBase() throws SQLException { 
			String myPath = DATABASE_PATH + DATABASE_NAME; 
			dbSQLite = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
		
	public Cursor getCursor(){
			SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
			queryBuilder.setTables(TABLE_NAME);
			
			String [] asColumnsToReturn = new String [] {COLUMN_ID, COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_PRICES,COLUMN_INCLUDES,COLUMN_CLASS,COLUMN_CONTACT};
			Cursor mCursor = queryBuilder.query(dbSQLite, asColumnsToReturn, null, null, null, null, "name ASC");
			
			return mCursor;
	}
		
	public String getName (Cursor c){
		return (c.getString(1));
	}
	public String getDescription (Cursor c){
		return (c.getString(2));
	}
	public String getPrices (Cursor c){
		return (c.getString(3));
	}
	public String getIncludes (Cursor c){
		return (c.getString(4));
	}
	public String getStars (Cursor c){
		return (c.getString(5));
	}
	public String getContacts (Cursor c){
		return (c.getString(6));
	}
	public void closeDB(){
		dbSQLite.close();
	}
}
