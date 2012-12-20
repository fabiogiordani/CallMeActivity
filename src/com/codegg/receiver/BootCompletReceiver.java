package com.codegg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.codegg.service.MyService;

/**
 * @author fabio.giordani
 */
public class BootCompletReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent serviceIntent = new Intent(context, MyService.class);
		context.startService(serviceIntent);

		// if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
		// String phoneNumber =
		// intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
		// System.out.println(phoneNumber);
		// if (phoneNumber != null &&
		// CallService.phoneNumber.equals("ANY_NUMBER_YOU_WANNA_INTERCEPT_ON"))
		// {
		//
		// // do what you want to do :)
		// }
		// }

	}

}
