package com.codegg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codegg.service.MyService;

public class ServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		findViewById(R.id.service_startBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(ServiceActivity.class.getName(), "Avvio il servizio...");
				avviaServizio();
			}
		});
		findViewById(R.id.service_stopBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(ServiceActivity.class.getName(), "Arresto il servizio...");
				Toast.makeText(ServiceActivity.this, "Arresto il servizio", Toast.LENGTH_SHORT).show();
				arrestaServizio();
			}
		});
	}

	private void avviaServizio() {
		startService(new Intent(this, MyService.class));
	}

	private void arrestaServizio() {
		stopService(new Intent(this, MyService.class));
	}

}
