package com.example.contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ListView listView;
	private SimpleAdapter adapter;
	private ArrayList<Map<String, String>> contact_list = new ArrayList<Map<String, String>>();
	DatabaseHelper dbHelper;
	SQLiteDatabase sdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(android.R.id.list);
		dbHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);
		sdb = dbHelper.getReadableDatabase();
		
		readDB();
		createList();
	}

	private void readDB(){
		Cursor cursor = sdb.query("contacts", new String[] {dbHelper.FIRST_NAME_COLUMN}, 
			    null, null, 
			    null, null, null);
		if (cursor.moveToFirst()){
			Map<String, String> contact_data = new HashMap<String, String>();
			
			do{
				String firstName = cursor.getString(cursor.getColumnIndex(dbHelper.FIRST_NAME_COLUMN));
				//String lastName = cursor.getString(cursor.getColumnIndex(dbHelper.LAST_NAME_COLUMN));
				contact_data.put("firstName", firstName);
				//contact_data.put("lastName", lastName);
				
				contact_list.add(contact_data);
			} while (cursor.moveToNext());
		}
	}
	
	private void createList() {
		adapter = new SimpleAdapter(MainActivity.this, contact_list,
				R.layout.list, new String[] { "firstName" }, new int[] { R.id.listTitle });

		listView.setAdapter(adapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add_contact) {
			Intent intent = new Intent(this, EditorActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
