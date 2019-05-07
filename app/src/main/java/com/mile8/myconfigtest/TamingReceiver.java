package com.mile8.myconfigtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TamingReceiver extends BroadcastReceiver {

    public static final String ALARM_WAKE_ACTION = "youga.tamingtask.taming.ALARM_WAKE_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TamingReceiver", "onReceive()--Action:" + intent.getAction());
    }
}
