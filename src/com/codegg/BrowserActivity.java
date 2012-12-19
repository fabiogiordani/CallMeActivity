package com.codegg;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Ha un textfield e un pulsante vai per aprire uan url Al click del bottone
 * salvo i dati dentro un database in una tabella che si chiama HISTORY Al click
 * su un altro pulsante della history apro un menu contestuale che si carica con
 * i dati della history stessa.
 * 
 * @author fabio.giordani
 * 
 */
public class BrowserActivity extends Activity {

	private MioDatabaseHelper dbHelper;

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.dbHelper = new MioDatabaseHelper(this);

		setContentView(R.layout.activity_browser);

		// carico il browser con google e imposto lo status text
		WebView wv = ((WebView) findViewById(R.id.browser_WEB));
		wv.setWebViewClient(new MyWebViewClient());
		String url = "http://www.google.it";
		wv.loadUrl(url);
		TextView tv = (TextView) findViewById(R.id.browser_STATUS);
		tv.setText("opening " + url + "...");

		findViewById(R.id.browser_URLBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebView wv = ((WebView) findViewById(R.id.browser_WEB));
				String url = ((EditText) findViewById(R.id.browser_URL_text)).getText().toString();
				Log.i(getLocalClassName(), "http://" + url);
				wv.clearView();
				wv.loadUrl("http://" + url);
				TextView tv = (TextView) findViewById(R.id.browser_STATUS);
				tv.setText("http://" + url);
				writeURLToDB(url);
			}
		});

		registerForContextMenu(findViewById(R.id.browser_HistoryBtn));

		// creaMenu(readURLFromDB());
	}

	private long writeURLToDB(String url) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("url", url);
		long id = db.insert("history", null, values);
		return id;
	}

	private ArrayList<String> readURLFromDB() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("history", null, null, null, null, null, null);
		ArrayList<String> items = new ArrayList<String>();
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				items.add(cursor.getString(1));
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return items;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_browser, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
	 * android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.browser_HistoryBtn) {
			menu.setHeaderTitle("History");
			menu.setHeaderIcon(R.drawable.play);
			ArrayList<String> items = readURLFromDB();
			for (int i = 0; i < items.size(); i++) {
				menu.add(Menu.NONE, i, 1, items.get(i));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		/*
		 * int id = item.getItemId(); Toast.makeText(this, "CI SONO!",
		 * Toast.LENGTH_SHORT).show(); switch (id) { case MENUITEM_BTN4_1:
		 * Toast.makeText(this, "Comando menu contestuale 1",
		 * Toast.LENGTH_SHORT).show(); return true; case MENUITEM_BTN4_2:
		 * Toast.makeText(this, "Comando menu contestuale 2",
		 * Toast.LENGTH_SHORT).show(); return true; } return false;
		 */
		return false;
	}

}
