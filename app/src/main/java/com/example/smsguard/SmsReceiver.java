package com.example.smsguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SMS_GUARD";
    private static final int MESSAGE_LIMIT = 5;
    private static final HashMap<String, Integer> messageCount = new HashMap<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!"android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) return;

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) return;

        String format = bundle.getString("format");

        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu, format);
            String sender = sms.getOriginatingAddress();
            String message = sms.getMessageBody();

            if (sender == null) sender = "Unknown";

            int count = messageCount.containsKey(sender)
                    ? messageCount.get(sender) + 1 : 1;
            messageCount.put(sender, count);

            if (count >= MESSAGE_LIMIT) {
                Log.d(TAG, "SPAM FLAGGED | Sender: " + sender
                        + " | Count: " + count + " | Message: " + message);
                Toast.makeText(context,
                        "SMS Guard: repeated SMS flagged as spam from " + sender,
                        Toast.LENGTH_LONG).show();
            } else {
                Log.d(TAG, "Allowed | Sender: " + sender + " | Count: " + count);
            }
        }
    }
}
