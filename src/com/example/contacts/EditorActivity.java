package com.example.contacts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
	private EditText et_social;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		
		et_first_name = (EditText) findViewById(R.id.firstName);
		et_last_name = (EditText) findViewById(R.id.lastName);
		et_phone_number = (EditText) findViewById(R.id.phoneNumber);
		et_email = (EditText) findViewById(R.id.email);
		et_birthdate = (EditText) findViewById(R.id.birthdate);
		et_social = (EditText) findViewById(R.id.socialID);
		
		Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
		
		buttonAdd.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);
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
			String first_name = et_first_name.getText().toString();
			String last_name = et_last_name.getText().toString();
			String phone_number = et_phone_number.getText().toString();
			String email = et_email.getText().toString();
			String birthdate = et_birthdate.getText().toString();
			String social = et_social.getText().toString();
			
			Toast.makeText(this, "FirstName = " + first_name, Toast.LENGTH_SHORT).show();
			break;
		case R.id.buttonCancel:
			finish();
			break;
		default:
			break;				
		}
		
	}
}
