package com.example.ex9_permissionsandbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.widget.Toast;

//This class for dynamic and static registrations!!
public class NetworkAvailability extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities activeNetwork = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

        if(activeNetwork != null) {
            Toast.makeText(context, "Network is ON", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Network is OFF", Toast.LENGTH_LONG).show();
        }
    }
}