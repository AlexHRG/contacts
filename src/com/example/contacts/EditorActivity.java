package com.example.contacts;

import android.content.Intent;
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
	private EditText et_social_network;
	private DB db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		
		db = new DB(this);
		db.open();
		
		et_first_name = (EditText) findViewById(R.id.firstName);
		et_last_name = (EditText) findViewById(R.id.lastName);
		et_phone_number = (EditText) findViewById(R.id.phoneNumber);
		et_email = (EditText) findViewById(R.id.email);
		et_birthdate = (EditText) findViewById(R.id.birthdate);
		et_social_network = (EditText) findViewById(R.id.social_net_id);
		
		Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
		
		buttonAdd.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.editor, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
			db.addRec(et_first_name.getText().toString()
					, et_last_name.getText().toString()
					, et_phone_number.getText().toString()
					, et_email.getText().toString()
					, et_birthdate.getText().toString()
					, et_social_network.getText().toString());
			
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
	
	  protected void onDestroy() {
		    super.onDestroy();
		    db.close();
		  }
}
