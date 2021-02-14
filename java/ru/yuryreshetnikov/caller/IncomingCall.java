package ru.yuryreshetnikov.caller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingCall extends BroadcastReceiver {

    Context context = null;

    public void onReceive(Context context, Intent intent) {
	try {
	    this.context = context;
	    TelephonyManager tmgr = (TelephonyManager) context
		.getSystemService(Context.TELEPHONY_SERVICE);
	    MyPhoneStateListener phone_listener = new MyPhoneStateListener();
	    tmgr.listen(phone_listener,
			PhoneStateListener.LISTEN_CALL_STATE);
	}
	catch (Exception e) {
	    Log.e("Phone Receive Error", " " + e);
	}
    }

    private class MyPhoneStateListener extends PhoneStateListener {
	public void onCallStateChanged(int state, String incomingNumber) {
	    Log.d(getClass().getName(),
		  state + " incoming no:" + incomingNumber);
	    if (state == 1) {
		Toast.makeText(context, "incoming no:" + incomingNumber,
			       Toast.LENGTH_LONG).show();
	    }
	}
    }
}
