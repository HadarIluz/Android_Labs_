package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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

    private SeekBar sb;
    float initial_result = 0;


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

        //add edit text listeners
        ed1.addTextChangedListener(new HandleTextChange());
        ed2.addTextChangedListener(new HandleTextChange());

        disableButtons(); //disable all buttons in powerUp action.



        //Dynamic layout for seek bar:
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.MainLayout);
        View child = getLayoutInflater().inflate(R.layout.seek_bar_layout, parentLayout, false);
        /*RelativeLayout.LayoutParams rlp =new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.BELOW, R.id.button_clear);*/
        //rlp.setMargins(0,dp2px(100),0,0);
        parentLayout.addView(child);
        /*
        ViewGroup parentLayout = (ViewGroup) findViewById(R.id.MainLayout); //this ia the mainLayout
        View child = getLayoutInflater().inflate(R.layout.seek_bar_layout, parentLayout, false);
        RelativeLayout.LayoutParams rlp =new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.BELOW, R.id.btnClear); //set the seek bar below the 'CLEAR' button.
        rlp.setMargins(0,dp2px(100),0,0);
        parentLayout.addView(child,rlp); //add the child with the ruls into the calculator.
*/
        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new HandleSeekBar());

        //listener for "Clear" button - ***Anonymous Inner Class***
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                tv1.setText("");
                ed1.setText("");
                ed2.setText("");
            }
        });


    }
//
    public int dp2px(int dp){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
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
        float res = num1 + num2;
        initial_result = res;
        changeResult(res, tv1);
        tv1.setText(String.valueOf(res));
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
        float res = num1 - num2;
        initial_result = res;
        changeResult(res, tv1);
        tv1.setText(String.valueOf(res));
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
            float res = num1 / num2;
            initial_result = res;
            changeResult(res, tv1);
            tv1.setText(String.valueOf(res));
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
        float res = num1 * num2;
        initial_result = res;
        changeResult(res, tv1);
        tv1.setText(String.valueOf(res));
    }

    //this function display the accurate number.
    private void changeResult(float res, TextView tvRes){
        int zeroCnt = sb.getProgress();
        tvRes.setText(String.format("%." + zeroCnt + "f", res));
    }

    // private class:
    private class HandleTextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if(!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty()){
                if( ed2.getText().toString().equals("0")){
                    addButton.setEnabled(true);
                    minusButton.setEnabled(true);
                    multipicationButton.setEnabled(true);
                }
                else{
                    addButton.setEnabled(true);
                    minusButton.setEnabled(true);
                    multipicationButton.setEnabled(true);
                    divideButton.setEnabled(true);
                }

            }
            else{
                tv1.setText("");
                disableButtons();
            }
        }
    }

    private void disableButtons(){
        addButton.setEnabled(false);
        minusButton.setEnabled(false);
        multipicationButton.setEnabled(false);
        divideButton.setEnabled(false);
    }


    private class HandleSeekBar implements SeekBar.OnSeekBarChangeListener {

        TextView tvExample = (TextView)findViewById(R.id.textView_Example);

        /*i- the location of the circle`s seek bar*/
        @Override
        public void onProgressChanged(SeekBar sb, int i, boolean b) {
            int zeroCnt = sb.getProgress();
            float num = 0;
            String floatStr = String.format("%." + zeroCnt + "f", num);
            tvExample.setText("zero accuracy: " + floatStr);
            if(!tv1.getText().toString().isEmpty()){
                tv1.setText(String.format("%." + zeroCnt + "f", initial_result));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }



}