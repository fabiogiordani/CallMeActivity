package com.codegg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class FileSystemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_system);
		findViewById(R.id.fileSystem_saveBtn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save("testo.txt");
			}
		});
		findViewById(R.id.fileSystem_loadBtn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				load("testo.txt");
			}
		});
		Log.i(getLocalClassName(), "Directory: " + getFilesDir().getAbsolutePath());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_file_system, menu);
		return true;
	}

	/**
	 * Salva il testo preso da una text area in un file.
	 * 
	 * @param fileName
	 */
	private void save(String fileName) {
		EditText textArea = (EditText) findViewById(R.id.fileSystem_textArea);
		String text = textArea.getText().toString();
		Writer writer = null;

		try {
			writer = new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE));
			writer.write(text);
			setTitle("Scritto " + getFilesDir().getAbsolutePath() + "/testo.txt");
			Toast.makeText(this, "Testo salvato dentro " + getFilesDir().getAbsolutePath() + "/testo.txt\nPare vero! :-)", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Log.e(getLocalClassName(), "Impossibile salvare il file", e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Throwable t) {
					t.printStackTrace();
					Log.e(getLocalClassName(), t.getMessage());
				}
			}
		}

	}

	/**
	 * Legge un testo da un file e lo mette in una text area.
	 * 
	 * @param fileName
	 */
	private void load(String fileName) {
		String text;
		Reader reader = null;
		try {
			reader = new InputStreamReader(openFileInput(fileName));
			StringBuffer sb = new StringBuffer();
			char[] charBuffer = new char[1024];
			int len;
			while ((len = reader.read(charBuffer)) != -1) {
				sb.append(charBuffer, 0, len);
			}
			text = sb.toString();
			Toast.makeText(this, "Testo caricato dal file " + getFilesDir().getAbsolutePath() + "/testo.txt", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			text = "";
			Toast.makeText(this, "Testo non trovato." + e.getMessage(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Log.e("FileDemo", "Impossibile aprire il file", e);
			text = "";
			Toast.makeText(this, "Errore:" + e.getMessage(), Toast.LENGTH_LONG).show();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Throwable t) {
					t.printStackTrace();
					Log.e(getLocalClassName(), t.getMessage());
				}
			}
		}
		EditText textArea = (EditText) findViewById(R.id.fileSystem_textArea);
		setTitle("Letto " + getFilesDir().getAbsolutePath() + "/testo.txt");
		textArea.setText(text);
	}

}
