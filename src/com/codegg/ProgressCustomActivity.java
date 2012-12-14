package com.codegg;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ProgressCustomActivity extends Activity {

	private final static int DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_custom);
		findViewById(R.id.progressCustom_startBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_ID);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_progress_custom, menu);
		return true;
	}

	/*
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case DIALOG_ID:
			dialog = new CustomDialog(this);
			break;
		default:
			dialog = null;
			break;
		}
		return dialog;
	}

	/*
	 * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog)
	 */
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DIALOG_ID:
			CustomDialog ref = (CustomDialog) dialog;
			ref.setTitle("Cavolo a merenda");
			ref.setMessage("Devi stare sereno");
			ref.setImage(R.drawable.ciccioli);
			break;
		}
	}

	/*
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		finish();
	}

}
