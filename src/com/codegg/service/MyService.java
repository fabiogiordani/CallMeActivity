package com.codegg.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private NotificationManager mgr;
	// private Timer timer;

	private TelephonyManager telManager;

	private class TelListener extends PhoneStateListener {
		/*
		 * @see android.telephony.PhoneStateListener#onCallStateChanged(int,
		 * java.lang.String)
		 */
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			Log.v("Phone State", "state:" + state);
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				Log.v("Phone State", "incomingNumber:" + incomingNumber + " ringing");
				Toast.makeText(getApplicationContext(), "numero:" + incomingNumber + " squilla", Toast.LENGTH_SHORT).show();
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.v("Phone State", "incomingNumber:" + incomingNumber + " CALL_STATE_OFFHOOK");
				Toast.makeText(getApplicationContext(), "numero:" + incomingNumber + " inizio chiamata", Toast.LENGTH_SHORT).show();
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				Log.v("Phone State", "incomingNumber:" + incomingNumber + " STATE_IDLE");
				Toast.makeText(getApplicationContext(), "numero:" + incomingNumber + " fine chiamata", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/*
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("MyService", "Servizio avviato");
		Toast.makeText(this, "Servizio avviato.\nIn ascolto...", Toast.LENGTH_SHORT).show();

		telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		telManager.listen(new TelListener(), PhoneStateListener.LISTEN_CALL_STATE);

		/*
		 * Serve per ripetere delle operazioni ogni x millisecondi.
		 */
		// Toast.makeText(this, "Servicio avviato", Toast.LENGTH_SHORT).show();
		// TimerTask task = new TimerTask() {
		// @Override
		// public void run() {
		// Log.i("MyService", "Servizio in esecuzione ");
		// }
		// };
		// timer = new Timer();
		// timer.schedule(task, 0, 5000);
	}

	/*
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		// timer.cancel();
		// timer = null;
		Toast.makeText(this, "Servizio arrestato", Toast.LENGTH_SHORT).show();
		Log.i("MyService", "Servizio arrestato");
	}

}
