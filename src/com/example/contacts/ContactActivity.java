package com.example.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactActivity extends ActionBarActivity {
	private long row_id;
	private DB db;
	private Cursor cursor;
	private TextView f_name;
	private TextView l_name;
	private TextView phone;
	private TextView email;
	private TextView birthdate;
	private TextView social;
	private ImageView image_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		Intent intent = getIntent();
		row_id = intent.getLongExtra("row_id", 0);
		
		db = new DB(this);
		db.open();
		
		f_name = (TextView) findViewById(R.id.field_name);
		l_name = (TextView) findViewById(R.id.field_lname);
		phone = (TextView) findViewById(R.id.field_phone);
		email = (TextView) findViewById(R.id.field_email);
		birthdate = (TextView) findViewById(R.id.field_birthdate);
		social = (TextView) findViewById(R.id.field_social);
		image_view = (ImageView) findViewById(R.id.imageView1);
		
		readData();
	}
	
	private void readData(){
		cursor = db.getLine(row_id);
		if (cursor.moveToFirst()){
			int fn_index = cursor.getColumnIndex(DB.COLUMN_FN);
			int ln_index = cursor.getColumnIndex(DB.COLUMN_LN);
			int phone_index = cursor.getColumnIndex(DB.COLUMN_PHONE);
			int email_index = cursor.getColumnIndex(DB.COLUMN_EMAIL);
			int birth_index = cursor.getColumnIndex(DB.COLUMN_BIRTH);
			int social_index = cursor.getColumnIndex(DB.COLUMN_SN);
			int image_path = cursor.getColumnIndex(DB.COLUMN_IMAGE_PATH);
	
			f_name.setText(cursor.getString(fn_index));
			l_name.setText(cursor.getString(ln_index));
			phone.setText(cursor.getString(phone_index));
			email.setText(cursor.getString(email_index));
			birthdate.setText(cursor.getString(birth_index));
			social.setText(cursor.getString(social_index));
			
			Bitmap image = BitmapFactory.decodeFile(cursor.getString(image_path));
			image_view.setImageBitmap(image);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_edit) {
			Intent intent = new Intent(this, EditorActivity.class);
			intent.putExtra("row_id", row_id);
			
			startActivity(intent);
		} else if (id == R.id.action_to_main){
			startActivity(new Intent(this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
}
