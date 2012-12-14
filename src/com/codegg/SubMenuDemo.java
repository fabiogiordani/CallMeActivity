package com.codegg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SubMenuDemo extends Activity {

	private final static int DIALOG_MSG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * LayoutInflater inflater = getLayoutInflater(); View view =
		 * inflater.inflate(R.layout.toast_xml, null); Toast toast = new
		 * Toast(this); toast.setView(view); toast.show();
		 */
		setContentView(R.layout.activity_sub_menu_demo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		SubMenu subMenu1 = menu.addSubMenu("Submenu 1");
		subMenu1.add("Comando 1");
		subMenu1.add("Comando 2");
		SubMenu subMenu2 = menu.addSubMenu("Submenu 2");
		subMenu2.add("Comando 3");
		subMenu2.add("Comando 4");

		SubMenu subMenu3 = menu.addSubMenu("Tipo checkbox");
		subMenu3.add(1, Menu.NONE, Menu.NONE, "Opzione 1");
		subMenu3.add(1, Menu.NONE, Menu.NONE, "Opzione 2");
		subMenu3.add(1, Menu.NONE, Menu.NONE, "Opzione 3");
		subMenu3.setGroupCheckable(1, true, false);
		SubMenu subMenu4 = menu.addSubMenu("Tipo radio");
		subMenu4.add(2, Menu.NONE, Menu.NONE, "Opzione 1");
		subMenu4.add(2, Menu.NONE, Menu.NONE, "Opzione 2");
		subMenu4.add(2, Menu.NONE, Menu.NONE, "Opzione 3");
		subMenu4.setGroupCheckable(2, true, true);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		showDialog(DIALOG_MSG_ID);
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
		builder.setTitle("Conferma chiusura");
		builder.setMessage("Vuoi tornare indietro? Verrà mostrata una foto di Giulietto e Valerio.");
		builder.setCancelable(false);
		builder.setPositiveButton("Torna e chiudi", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismissDialog(DIALOG_MSG_ID);
				SubMenuDemo.this.finish(); // E' tutto qui il finish() chiude
											// l'Activity e ti fa ritornare
											// indietro.
			}
		});
		builder.setNegativeButton("Annulla e mostra foto", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismissDialog(DIALOG_MSG_ID);
				mostraToastConFoto();
			}
		});
		AlertDialog alert = builder.create();
		return alert;
	}

	private void mostraToastConFoto() {
		// Toast.makeText(this, "Finalizzo. \n Questo è un Toast con immagine",
		// Toast.LENGTH_SHORT).show();
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CLIP_VERTICAL);

		ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.ciccioli);

		TextView tv = new TextView(this);
		tv.setText("Ciccioli.....questi sono i miei cicciottini....");
		// tv.setGravity(Gravity.BOTTOM);
		tv.setTextColor(Color.BLACK);

		TextView tv1 = new TextView(this);
		tv1.setText("Ciccioli.....questi sono i miei cicciottini....");
		tv1.setTextColor(Color.BLACK);

		layout.addView(image); // il testo on si vede....bhà....bisogna fare con
								// l'XML.
		layout.addView(tv);
		layout.addView(tv1);
		Toast toast = new Toast(this);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);

		// toast.setText("Questo è un toast con immagine....");
		toast.show();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.isChecked()) {
			item.setChecked(false);
		} else {
			item.setChecked(true);
		}
		return true;
	}
}
