package com.example.ex9_permissionsandbroadcast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //those are the REQUEST_CODE FOR THE RECEIVE SMS AND READ_SMS
    private static final int RECEIVE_SMS_REQUEST_CODE   = 1;
    private static final int READ_SMS_REQUEST_CODE      = 2;

    //for networkActivity (flight mode)
    BroadcastReceiver networkActivity =  new NetworkAvailability();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This function will call each time when MainActivity will create
        askForSmsDangerousPermissions();

    }

    @Override
    public void onResume(){
        super.onResume();
        registerNetworkReceiver();
    }

//@@@@@@@@@@@@@@@@@@@@@___networkActivity___@@@@@@@@@@@@@@@@@@@@@
    //in this method we will add all the events we want to listen on them.(CONNECTIVITY_ACTION)
    private void registerNetworkReceiver() {
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"); //permission for change in the network connection.
        filter.addAction(Manifest.permission.ACCESS_NETWORK_STATE);
        registerReceiver(networkActivity, filter);
    }

    //because we register dynamically(OnResume) we need to unregister in onPause.
    @Override
    protected void onPause () {
        super.onPause();
        unregisterReceiver(networkActivity);
    }
//@@@@@@@@@@@@@@@@@@@@@___End___networkActivity___@@@@@@@@@@@@@@@@@@@@@




//@@@@@@@@@@@@@@@@@@@@@___SMSReceiver___@@@@@@@@@@@@@@@@@@@@@
    //we need to ask for permission because we use dangerous permission.
    private void askForSmsDangerousPermissions() {
        requestSmsDangerousPermission(Manifest.permission.RECEIVE_SMS, RECEIVE_SMS_REQUEST_CODE);
        requestSmsDangerousPermission(Manifest.permission.READ_SMS, READ_SMS_REQUEST_CODE);

    }

    //we choose to use the new android flow in order to handle request.
    //this function will request permission for user respond.
    //permission= READ_SMS.
    //permissionRequestCode = READ_SMS_REQUEST_CODE.
    private void requestSmsDangerousPermission(String permission, int permissionRequestCode)
    {
        // check if permission already User approved
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
            return;

        // Permission is not granted. show an explanation.
        else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
            Toast.makeText(this, "You must grant this permission in order to see SMS messages", Toast.LENGTH_LONG).show();

        // request the permission
        ActivityCompat.requestPermissions(this, new String[] { permission }, permissionRequestCode);
    }


    //this method is called when a respond from the user has been made.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case RECEIVE_SMS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                     Toast.makeText(this, "RECEIVE_SMS permission granted: ", Toast.LENGTH_SHORT).show();

                /*The feature of receive sms is unavailable because the feature requires a permission that that user has denied. */
                else
                    Toast.makeText(this, "RECEIVE_SMS permission isn't granted: ", Toast.LENGTH_SHORT).show();
                break;
            case READ_SMS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "READ_SMS permission granted: ", Toast.LENGTH_SHORT).show();

                    /*The feature of read sms is unavailable because the feature requires a permission that that user has denied. */
                else Toast.makeText(this, "READ_SMS permission isn't granted: ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@___End___SMSReceiver___@@@@@@@@@@@@@@@@@@@@@


}