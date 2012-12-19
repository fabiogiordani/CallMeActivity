package com.codegg;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentProviderActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_content_provider);

		Uri uri = Contacts.CONTENT_URI;
		String[] projection = { Contacts._ID, Contacts.DISPLAY_NAME };
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = Contacts.DISPLAY_NAME + " ASC";
		Cursor cursor = managedQuery(uri, projection, selection, selectionArgs, sortOrder);
		setListAdapter(new CursorAdapter(this, cursor, true) {

			@Override
			public View newView(Context context, Cursor cursor, ViewGroup vgroup) {
				String displayName = cursor.getString(1);
				TextView tv = new TextView(context);
				tv.setText(displayName);
				return tv;
			}

			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				String displayName = cursor.getString(1);
				TextView tv = (TextView) view;
				tv.setText(displayName);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_content_provider, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
