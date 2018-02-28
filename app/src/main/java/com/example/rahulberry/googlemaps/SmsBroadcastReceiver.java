package com.example.rahulberry.googlemaps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


/**
 * Created by niall on 13/02/2018.
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String TAG = "BOOOOBIES";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "test");
        if (intent.getAction().equals(SMS_RECEIVED)) {
           Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                Toast.makeText(context, sender, Toast.LENGTH_SHORT).show();

                //MapFragment inst = new MapFragment();
                //inst.bikeupdate(message);
                // prevent any other broadcast receivers from receiving broadcast
                // abortBroadcast();
            }

        }
    }
}