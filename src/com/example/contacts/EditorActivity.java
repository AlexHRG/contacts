package com.example.contacts;

import android.content.Intent;
import android.database.Cursor;
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

public class EditorActivity extends ActionBarActivity implements
		OnClickListener {

	private EditText et_first_name;
	private EditText et_last_name;
	private EditText et_phone_number;
	private EditText et_email;
	private EditText et_birthdate;
	private EditText et_social_network;
	private DB db;
	private long row_id;
	private String row_no;
	private boolean edit_mode = false;
	private Cursor cursor;

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

		Button buttonAdd = (Button) findViewById(R.id.buttonSave);
		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);

		buttonAdd.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);

		Intent intent = getIntent();
        row_id = intent.getLongExtra("row_id", 0);
		if (row_id != 0) {
			row_no = String.valueOf(row_id);
			readData(row_id);
			edit_mode = true;
		}
	}

	private void readData(Long row_id) {
		cursor = db.getLine(row_no);
		if (cursor.moveToFirst()){
			int fn_index = cursor.getColumnIndex(db.COLUMN_FN);
			int ln_index = cursor.getColumnIndex(db.COLUMN_LN);
			int phone_index = cursor.getColumnIndex(db.COLUMN_PHONE);
			int email_index = cursor.getColumnIndex(db.COLUMN_EMAIL);
			int birth_index = cursor.getColumnIndex(db.COLUMN_BIRTH);
			int social_index = cursor.getColumnIndex(db.COLUMN_SN);
	
			et_first_name.setText(cursor.getString(fn_index));
			et_last_name.setText(cursor.getString(ln_index));
			et_phone_number.setText(cursor.getString(phone_index));
			et_email.setText(cursor.getString(email_index));
			et_birthdate.setText(cursor.getString(birth_index));
			et_social_network.setText(cursor.getString(social_index));
		}
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
		switch (v.getId()) {
		case R.id.buttonSave:
			if (!edit_mode) {
				db.addRec(et_first_name.getText().toString()
						, et_last_name.getText().toString()
						, et_phone_number.getText().toString()
						, et_email.getText().toString()
						, et_birthdate.getText().toString()
						, et_social_network.getText().toString());
				toMainActivity();
				break;
			} else {
				db.updRec(et_first_name.getText().toString()
						, et_last_name.getText().toString()
						, et_phone_number.getText().toString()
						, et_email.getText().toString()
						, et_birthdate.getText().toString()
						, et_social_network.getText().toString()
						, row_no);
				toMainActivity();
			}
		case R.id.buttonCancel:
			finish();
			break;
		default:
			break;
		}
	}

	private void toMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
}
