package com.example.ex9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RECEIVE_SMS_REQUEST_CODE   = 1;
    private static final int READ_SMS_REQUEST_CODE      = 2;

    private BroadcastReceiver br = new NetworkReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This two function will call each time when MainActivity will create
        askForSmsDangerousPermissions();
        registerNetworkReceiver();
    }

    private void registerNetworkReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(br, filter, Manifest.permission.ACCESS_NETWORK_STATE, null);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        unregisterReceiver(br);
    }


    private void askForSmsDangerousPermissions() {
        requestSmsDangerousPermission(Manifest.permission.READ_SMS, READ_SMS_REQUEST_CODE);
        requestSmsDangerousPermission(Manifest.permission.RECEIVE_SMS, RECEIVE_SMS_REQUEST_CODE);
    }

    private void requestSmsDangerousPermission(String permission, int permissionRequestCode)
    {
        // check if permission already granted
        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
            return;

        // Permission is not granted. show an explanation.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
            Toast.makeText(this, "You must grant this permission in order to see SMS messages", Toast.LENGTH_LONG).show();

        // request the permission
        ActivityCompat.requestPermissions(this, new String[] { permission }, permissionRequestCode);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0)
            return;


        boolean firstPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

        switch (requestCode) {
            case RECEIVE_SMS_REQUEST_CODE:
                Toast.makeText(this, "RECEIVE_SMS permission granted: " + firstPermissionGranted, Toast.LENGTH_SHORT).show();
                break;
            case READ_SMS_REQUEST_CODE:
                Toast.makeText(this, "READ_SMS permission granted: " + firstPermissionGranted, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}