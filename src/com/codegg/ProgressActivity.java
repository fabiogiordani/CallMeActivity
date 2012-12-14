package com.codegg;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class ProgressActivity extends Activity {

	private final static int DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		findViewById(R.id.progress_startBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_ID);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_progress, menu);
		return true;
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
		case DIALOG_ID:
			dialog = createProgressDialog();
			break;
		default:
			dialog = null;
			break;
		}
		return dialog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog)
	 */
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DIALOG_ID:
			final ProgressDialog ref = (ProgressDialog) dialog;
			ref.setProgress(0);
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < ref.getMax(); i += 25) {
						ref.setSecondaryProgress(i + 1);
						for (int j = 0; j < ref.getMax(); j++) {
							try {
								Thread.sleep(50);
							} catch (InterruptedException ie) {
								Log.e(getLocalClassName(), ie.getMessage());
							}
							ref.setProgress(j);
						}
					}
					ref.setSecondaryProgress(100);
					dismissDialog(DIALOG_ID);
				}
			});
			thread.start();
			break;
		}
	}

	private ProgressDialog createProgressDialog() {
		ProgressDialog progress = new ProgressDialog(this);
		progress.setTitle("Attendi e zitto!");
		progress.setMessage("Operazione in corso attendere in silenzio!");
		progress.setIndeterminate(false);
		progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progress.setMax(100);
		progress.setCancelable(false);
		return progress;
	}

}
