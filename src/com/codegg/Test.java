package com.codegg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		findViewById(R.id.test_backBtn).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO non mi piace per niente non poter parametrizzare...
				Log.i(getLocalClassName(), "Back test");
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_call_me, menu);
		return true;
	}

}
