package com.example.contacts;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
	
	private static final String TABLE_CONTACTS = "contacts";
	public static final String FIRST_NAME_COLUMN = "first_name";
	public static final String LAST_NAME_COLUMN = "last_name";
	public static final String PHONE_COLUMN = "phone";
	public static final String EMAIL_COLUMN = "email";
	public static final String BIRTHDATE_COLUMN = "birthdate";
	public static final String FACEBOOK_ID_COLUMN = "facebook";
	
	private static final String DATABASE_CREATE_SCRIPT = "create table "
			+ TABLE_CONTACTS + " (" + BaseColumns._ID
			+ " integer primary key autoincrement, "
			+ FIRST_NAME_COLUMN + " text not null, "
			+ LAST_NAME_COLUMN + " text not null, "
			+ PHONE_COLUMN + " text, "
			+ EMAIL_COLUMN + " text, "
			+ BIRTHDATE_COLUMN + " text, "
			+ FACEBOOK_ID_COLUMN + " text);";


	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_SCRIPT);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
