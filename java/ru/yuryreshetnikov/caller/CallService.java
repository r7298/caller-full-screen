package ru.yuryreshetnikov.caller;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class CallService extends Service {

    IncomingCall incomingCall;

    @Override
    public IBinder onBind(Intent intent) {
	return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	try {
	    Log.i("main", "Call Service started");
	    incomingCall = new IncomingCall();
	    registerReceiver(incomingCall, new IntentFilter());
	}
	catch (Exception e) {
	    Log.e("Register Error", e.toString());
	}
	return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
	try {
	    Log.i("main", "call service destroyed");
	    this.unregisterReceiver(incomingCall);
	} catch (Exception e) {
	    Log.e("Unregister Error", e.toString());
	}
	super.onDestroy();
    }
}
