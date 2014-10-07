package com.example.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ListView listView;
	private SimpleAdapter adapter;
	private DB db;
	SimpleCursorAdapter scAdapter;
	Cursor cursor;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new DB(this);
		db.open();
		
		listView = (ListView) findViewById(android.R.id.list);
		
	    cursor = db.getAllData();
	    startManagingCursor(cursor);
	    
	    String[] from = new String[] { DB.COLUMN_FN, DB.COLUMN_LN };
	    int[] to = new int[] { R.id.listFN, R.id.listLN };
	    scAdapter = new SimpleCursorAdapter(this, R.layout.list, cursor, from, to);
        listView.setAdapter(scAdapter);

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
	
	  protected void onDestroy() {
		    super.onDestroy();
		    db.close();
		  }
}
