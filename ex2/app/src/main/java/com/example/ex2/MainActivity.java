package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText ed1;
    private EditText ed2;
    private TextView tv1;
    private Button addButton;
    private Button minusButton;
    private Button multipicationButton;
    private Button divideButton;
    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("*** onCreate ***","*** onCreate ***");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calculator);
        ed1 = (EditText) findViewById(R.id.edit_text1);
        ed2 = (EditText) findViewById(R.id.edit_text2);
        tv1 = (TextView) findViewById(R.id.textView_result);
        addButton = (Button) findViewById(R.id.button_add);
        minusButton = (Button) findViewById(R.id.button_minus);
        multipicationButton = (Button) findViewById(R.id.button_multipication);
        divideButton = (Button) findViewById(R.id.button_divide);
    }
    @Override
    protected void onStart(){
        Log.i("*** onStart ***","*** onStart ***");
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outstate) {
        Log.i("*** onSaveInstanceState ***","*** onSaveInstanceState ***");
        outstate.putString("result",tv1.getText().toString());
        super.onSaveInstanceState(outstate);
    }

    @Override
    protected void onResume(){
        Log.i("*** onResume ***","*** onResume ***");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.i("*** onPause ***","*** onPause ***");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.i("*** onStop ***","*** onStop ***");
        super.onStop();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("*** onRestoreInstanceState ***","*** onRestoreInstanceState ***");
        super.onRestoreInstanceState(savedInstanceState);
        tv1.setText(savedInstanceState.getString(("result")));
    }

    @Override
    protected void onDestroy(){
        Log.i("*** onDestroy ***","*** onDestroy ***");
        super.onDestroy();
    }


    public void pressedAdd(View v) {
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        float sum = num1 + num2;
        tv1.setText(String.valueOf(sum));
    }

    public void pressMinus(View v){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
            float sum = num1 - num2;
            tv1.setText(String.valueOf(sum));
    }

    public void pressDivide(View v){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        if(num2 == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Divide by zero",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else{
            float sum = num1 / num2;
            tv1.setText(String.valueOf(sum));
        }
    }
    public void pressMultipication(View v){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
            float sum = num1 * num2;
            tv1.setText(String.valueOf(sum));
    }
}