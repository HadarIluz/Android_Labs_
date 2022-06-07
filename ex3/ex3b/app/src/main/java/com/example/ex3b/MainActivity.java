package com.example.ex3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final int REGISTER_ID = 1;
    private TextView tv1;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // load the main activity.
        getSupportActionBar().setTitle("Main Activity"); // Set the name of the new activity to "Register"(the second activity).
        tv1 = (TextView) findViewById(R.id.textView);
        registerBtn = (Button) findViewById(R.id.buttonReg);
    }

    //This method will called when we will click in the register button.
    public void registerButton (View view){
        Intent intent = new Intent(this,Register.class); //This is an explicit intent because we the target component(Register.class).
        startActivityForResult(intent,REGISTER_ID); // requires an result from the Register activity.
    }

    //This method handle the result from the Register activity.
    //data is the intent send by the register activity.
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_ID) { // check if we get result from the Register activity through the REGISTER_ID.
            if (resultCode == RESULT_OK) { // now check if the result is valid.
                if (data.getExtras() != null) { // check if there is some extras in the intent.
                    getSupportActionBar().setTitle("Main Activity with the registeration details"); // Set the name of the new activity to "Register"
                    String gender;
                    gender = data.getStringExtra(Register.GENDER);
                    if (gender.equals("Male"))
                        gender = "Mr.";
                    else gender = "Ms.";
                    tv1.setText("Welcome back " + gender + data.getStringExtra(Register.FIRST_NAME) + ", " + data.getStringExtra(Register.LAST_NAME));
                    registerBtn.setText("again..."); // change the name of the register button.
                }
            }
        }
    }
}