package com.example.contacts;

import java.util.logging.Logger;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

public class EditorActivity extends ActionBarActivity implements
		OnClickListener {

	private EditText et_first_name;
	private EditText et_last_name;
	private EditText et_phone_number;
	private EditText et_email;
	private EditText et_birthdate;
	private EditText et_social_network;
	private TextView tv_image_path;
	private String imagePath;
	private DB db;
	private long row_id;
	private boolean edit_mode = false;
	private Cursor cursor;
	private final int REQUEST_SAVE = 1;
	private final int REQUEST_LOAD = 2;

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
		tv_image_path = (TextView) findViewById(R.id.file_path);
		

		Button buttonAdd = (Button) findViewById(R.id.buttonSave);
		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
		Button buttonOpen = (Button) findViewById(R.id.buttonChoosePicture);

		buttonAdd.setOnClickListener(this);
		buttonCancel.setOnClickListener(this);
		buttonOpen.setOnClickListener(this);

		Intent intent = getIntent();
        row_id = intent.getLongExtra("row_id", 0);
		if (row_id != 0) {
			edit_mode = true;
			readData(row_id);
		}
	}

	private void readData(Long row_id) {
		cursor = db.getLine(row_id);
		if (cursor.moveToFirst()){
			int fn_index = cursor.getColumnIndex(DB.COLUMN_FN);
			int ln_index = cursor.getColumnIndex(DB.COLUMN_LN);
			int phone_index = cursor.getColumnIndex(DB.COLUMN_PHONE);
			int email_index = cursor.getColumnIndex(DB.COLUMN_EMAIL);
			int birth_index = cursor.getColumnIndex(DB.COLUMN_BIRTH);
			int social_index = cursor.getColumnIndex(DB.COLUMN_SN);
			int image_path_index = cursor.getColumnIndex(DB.COLUMN_IMAGE_PATH);
	
			et_first_name.setText(cursor.getString(fn_index));
			et_last_name.setText(cursor.getString(ln_index));
			et_phone_number.setText(cursor.getString(phone_index));
			et_email.setText(cursor.getString(email_index));
			et_birthdate.setText(cursor.getString(birth_index));
			et_social_network.setText(cursor.getString(social_index));
			tv_image_path.setText(cursor.getString(image_path_index));
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.editor, menu);
//		return true;
//
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonChoosePicture:
			Intent intent = new Intent(this, FileDialog.class);
            intent.putExtra(FileDialog.START_PATH, "/sdcard");
            //can user select directories or not
            intent.putExtra(FileDialog.CAN_SELECT_DIR, false);
            //alternatively you can set file filter
            //intent.putExtra(FileDialog.FORMAT_FILTER, new String[] { "png" });
            startActivityForResult(intent, REQUEST_SAVE);
			break;
		case R.id.buttonSave:
			db.addOrUpd(edit_mode
					, et_first_name.getText().toString()
					, et_last_name.getText().toString()
					, et_phone_number.getText().toString()
					, et_email.getText().toString()
					, et_birthdate.getText().toString()
					, et_social_network.getText().toString()
					, imagePath
					, 0
					, row_id);
			toMainActivity();
			break;
		case R.id.buttonCancel:
			finish();
			break;
		default:
			break;
		}
	}

    public synchronized void onActivityResult(final int requestCode,
            int resultCode, final Intent data) {

            if (resultCode == Activity.RESULT_OK) {
                    if (requestCode == REQUEST_SAVE) {
                            System.out.println("Saving...");
                    } else if (requestCode == REQUEST_LOAD) {
                            System.out.println("Loading...");
                    }
                    imagePath = data.getStringExtra(FileDialog.RESULT_PATH);
                    tv_image_path.setText(imagePath);
            } else if (resultCode == Activity.RESULT_CANCELED) {
            		return;
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
