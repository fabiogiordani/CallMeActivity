package com.codegg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Toast;

public class CallMe extends Activity {
	private final static int OPTION_1 = 1;
	private final static int OPTION_2 = 2;
	private final static int OPTION_3 = 3;

	private final static int MENUITEM_BTN4_1 = 41;
	private final static int MENUITEM_BTN4_2 = 42;

	private final static int DIALOG_MSG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_me);

		findViewById(R.id.call_me_callMeBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(getLocalClassName(), "Chiamami!");
				startSimulateCall();
			}
		});

		findViewById(R.id.call_me_testBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(getLocalClassName(), "Test!");
				startTest();
			}
		});

		registerForContextMenu(findViewById(R.id.call_me_menuBtn));

		findViewById(R.id.call_me_subMenuBtn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(CallMe.this, "Apro l'intent....", Toast.LENGTH_SHORT).show();
				Intent subMenuIntent = new Intent(CallMe.this, SubMenuDemo.class);
				startActivity(subMenuIntent);
			}
		});

		findViewById(R.id.call_me_alertBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_MSG_ID);
			}
		});

		findViewById(R.id.call_me_progressBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CallMe.this, "Inizio progress intent", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(CallMe.this, ProgressActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.call_me_progressCustomBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CallMe.this, "Inizio progress intent", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(CallMe.this, ProgressCustomActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.call_me_fileSystemBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CallMe.this, "Lettura e scrittura file", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(CallMe.this, FileSystemActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.call_me_fileSystemExternalBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CallMe.this, "Demo lettura ile da SD CARD", Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(CallMe.this, FileSystemGalleryActivity.class);
				startActivity(intent);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case DIALOG_MSG_ID:
			dialog = createMessageDialog();
			break;
		default:
			dialog = null;
			break;
		}
		return dialog;
	}

	/**
	 * Metodo privato per la creazione di finestre di dialogo personalizzate. Il
	 * metodo è deprecato ma funziona lo stesso da vedere da quale metodo è
	 * stato sostuito.
	 * 
	 * @return
	 */
	private Dialog createMessageDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Avviso");
		builder.setMessage("Attenzione! Questo è un avviso!");
		builder.setCancelable(false);
		builder.setPositiveButton("Chiudi", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismissDialog(DIALOG_MSG_ID);
			}
		});
		AlertDialog alert = builder.create();
		return alert;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_call_me, menu);
		MenuItem com1 = menu.add(menu.NONE, 1, 1, "Comando 1");
		com1.setIcon(R.drawable.play);
		menu.add(menu.NONE, 2, 2, "Comando 2");
		menu.add(menu.NONE, 3, 3, "Comando 3");
		MenuItem com4 = menu.add(menu.NONE, 4, 4, "Comando 4");
		com4.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(CallMe.this, "Comando 4 da linstener", Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		menu.add(menu.NONE, 5, 5, "Comando 5");
		menu.add(menu.NONE, 6, 6, "Comando 6");
		menu.add(menu.NONE, 7, 7, "Comando 7");
		menu.add(menu.NONE, 8, 8, "Comando 8");
		menu.add(menu.NONE, 9, 9, "Comando 9");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case OPTION_1:
			Toast.makeText(this, getResources().getText(R.string.callMe_option1), Toast.LENGTH_SHORT).show();
			return true;
		case OPTION_2:
			Toast.makeText(this, getResources().getText(R.string.callMe_option2), Toast.LENGTH_SHORT).show();
			return true;
		case OPTION_3:
			Toast.makeText(this, getResources().getText(R.string.callMe_option3), Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
		// return super.onOptionsItemSelected(item);
	}

	private void startSimulateCall() {
		Intent simulateInt = new Intent(this, SimulateCall.class);
		startActivity(simulateInt);
	}

	private void startTest() {
		Intent testInt = new Intent(this, Test.class);
		startActivity(testInt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
	 * android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.call_me_menuBtn) {
			menu.setHeaderTitle("Menu bottone 4");
			menu.setHeaderIcon(R.drawable.play);
			menu.add(Menu.NONE, MENUITEM_BTN4_1, 1, "Comando contestuale 1");
			menu.add(Menu.NONE, MENUITEM_BTN4_2, 2, "Comando contestuale 2");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		Toast.makeText(this, "CI SONO!", Toast.LENGTH_SHORT).show();
		switch (id) {
		case MENUITEM_BTN4_1:
			Toast.makeText(this, "Comando menu contestuale 1", Toast.LENGTH_SHORT).show();
			return true;
		case MENUITEM_BTN4_2:
			Toast.makeText(this, "Comando menu contestuale 2", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

}
