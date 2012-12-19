package com.codegg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author fabio.giordani
 * 
 */
public class MyReceiver extends BroadcastReceiver {

	/**
	 * 
	 */
	public MyReceiver() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context content, Intent intent) {

		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			String phoneNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
			System.out.println(phoneNumber);

			// if (phoneNumber != null &&
			// CallService.phoneNumber.equals("ANY_NUMBER_YOU_WANNA_INTERCEPT_ON"))
			// {
			//
			// // do what you want to do :)
			// }
		}

	}

}
