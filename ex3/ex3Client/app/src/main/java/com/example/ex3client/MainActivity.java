package com.example.ex3client;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REGISTER_ID = 1;
    private EditText edCall;
    private EditText edSurf;
    private EditText edEmail;
    private TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCall = (EditText) findViewById(R.id.edTelephone);
        edSurf = (EditText) findViewById(R.id.edAddress);
        edEmail = (EditText) findViewById(R.id.edEmail);
        tv1 = (TextView) findViewById(R.id.textView);
    }

    //This method called when the call button pressed.
    public void callBtnClicked(View view) {
            String phoneNumberStr = edCall.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumberStr));
            startActivity(intent); // will open the phone dialer.
        }

    //This method called when the surf button pressed.
    public void surfBtnClicked(View view) {
            String websiteAddressStr = edSurf.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteAddressStr));
            startActivity(intent); //will open the web explorer.

    }

    //This method called when the email button pressed.
    public void emailBtnClicked(View view) {
        String emailStr = edEmail.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailStr)); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "subjerweect");
        intent.putExtra(Intent.EXTRA_TEXT, "gfdgfdgdfgdfgdfgdfgdf");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //This method will called when the register button pressed.
    public void registerBtnClicked(View view) {
        Intent intent = new Intent(); //implicit activity.
        intent.setAction("com.action.ex3.register"); // set action to ex3 register.
        startActivityForResult(intent, REGISTER_ID); // requires an result from the ex3a or ex3b apps.
    }

    //This method handle the result from the ex3a or ex3b apps.
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REGISTER_ID) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {

                    String firstName = data.getStringExtra("first_name");
                    String lastName = data.getStringExtra("last_name");
                    String genderType = data.getStringExtra("gender_from_rg");

                    if (genderType.equals("Male"))
                        genderType = "Mr.";
                    else genderType = "Ms.";

                    tv1.setText(genderType + " " + firstName + " " + lastName);
                }
            }
        }
    }
}