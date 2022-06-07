package com.example.ex9_permissionsandbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


//extern class which extends BroadcastReceiver.
//This class for dynamic and static registrations!!
//This declared in the manifest file and works even if the app is closed.
public class SMSReceiver extends BroadcastReceiver {

    private String tag = "sms received is null";
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        SmsMessage message = messages[0];
        if(message != null)
        {
            String sender = message.getDisplayOriginatingAddress();// get who write the sms.
            String body = message.getDisplayMessageBody();// get the content from the sms.

            //create string with the name of the sender and content as requested in lab.
            String senderAndContent = "New message from: " + sender + "\nThe message: " + body;

            Toast.makeText(context, senderAndContent, Toast.LENGTH_LONG).show(); // show toast with the string we created.
        }
        else {
            Log.e(tag, "message is null");
        }
    }
}


