package com.example.contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditorActivity extends ActionBarActivity implements OnClickListener {
	
	private EditText et_first_name;
	private EditText et_last_name;
	private EditText et_phone_number;
	private EditText et_email;
	private EditText et_birthdate;
	private EditText et_facebook;
	DatabaseHelper dbHelper;
	SQLiteDatabase sdb;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		
		et_first_name = (EditText) findViewById(R.id.firstName);
		et_last_name = (EditText) findViewById(R.id.lastName);
		et_phone_number = (EditText) findViewById(R.id.phoneNumber);
		et_email = (EditText) findViewById(R.id.email);
		et_birthdate = (EditText) findViewById(R.id.birthdate);
		et_facebook = (EditText) findViewById(R.id.facebookID);
		
		Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
		
		buttonAdd.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);
		
		
		dbHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);
		sdb = dbHelper.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editor, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.buttonAdd:			
			ContentValues newValues = new ContentValues();
			
			newValues.put(dbHelper.FIRST_NAME_COLUMN, et_first_name.getText().toString());
			newValues.put(dbHelper.LAST_NAME_COLUMN, et_last_name.getText().toString());
			newValues.put(dbHelper.PHONE_COLUMN, et_phone_number.getText().toString());
			newValues.put(dbHelper.EMAIL_COLUMN, et_email.getText().toString());
			newValues.put(dbHelper.BIRTHDATE_COLUMN, et_birthdate.getText().toString());
			newValues.put(dbHelper.FACEBOOK_ID_COLUMN, et_facebook.getText().toString());
			long rowId = sdb.insert("contacts", null, newValues);
			
			Log.d("MyLog", " --- rows inserted: " + rowId);
			Toast.makeText(this, "Data added to db", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonCancel:
			finish();
			break;
		default:
			break;				
		}
		
	}
}
