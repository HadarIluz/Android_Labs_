package com.example.ex3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    //Those variables will use for key value in map.
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String GENDER = "gender_from_rg";

    private EditText firstName;
    private EditText lastName;
    private RadioGroup genderFromGroup;
    private Toast missingFirstName;
    private Toast missingLastName;
    private Toast missingRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // load the register activity.
        getSupportActionBar().setTitle("Register"); // Set the name of the new activity to "Register"

        firstName = (EditText) findViewById(R.id.edFirstName);
        lastName = (EditText) findViewById(R.id.edLastName);
        genderFromGroup = (RadioGroup) findViewById(R.id.rgGender);

        //Create new toasts.
        missingFirstName = Toast.makeText(getApplicationContext(), "First Name field missing", Toast.LENGTH_SHORT);
        missingLastName = Toast.makeText(getApplicationContext(), "Last Name field missing", Toast.LENGTH_SHORT);
        missingRadioButton = Toast.makeText(getApplicationContext(),"Radio button field missing",Toast.LENGTH_SHORT);
    }

    //This method called when we click on the "Send Back" button.
    public void returnBtnClicked (View view) {
        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastName.getText().toString();
        RadioButton rgGenderChosen = (RadioButton) findViewById(genderFromGroup.getCheckedRadioButtonId()) ;
        if( firstNameStr.isEmpty()) missingFirstName.show();
        else if ( lastNameStr.isEmpty()) missingLastName.show();
        else if ( genderFromGroup.getCheckedRadioButtonId() == -1) missingRadioButton.show();
        else {
            /* Now will create intent for transfer data to previous activity */
            Intent intent = new Intent(); // This will be an implicit intent becuase we dont care who called us.

            //now put extras inside the intent
            intent.putExtra(FIRST_NAME, firstNameStr);
            intent.putExtra(LAST_NAME, lastNameStr);
            intent.putExtra(GENDER, rgGenderChosen.getText().toString());
            setResult(RESULT_OK, intent);
            finish();// return the answer to the one who ask.
        }

    }
}