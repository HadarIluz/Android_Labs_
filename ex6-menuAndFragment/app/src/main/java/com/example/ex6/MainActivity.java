package com.example.ex6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragA.FragAListener, FragB.FragBListener {
    FragA fragA;
    FragB fragB;

    static float op1, op2, initial_result;
    static int zeroCnt = 0;

    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            fragA = (FragA) getSupportFragmentManager().findFragmentByTag("FRAG1");
            fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAG2");

            if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)){
                if (fragB != null) {
                    //begin of the transaction, remove fragment B in order to show only fragA.
                    getSupportFragmentManager().beginTransaction()
                            .show(fragB)
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragContainer, FragB.class,null, "FRAGB")
                            .commit();
                }
                getSupportFragmentManager().executePendingTransactions(); //execute now.
            }
    }


    /*This function gets data inputs from fragment A and to the calculation, then send the res to FragB by onNewClickSetResult() function.
    view in an object that hold the button which pressed
    */
    public void OnClickEvent(String btAction, String ed1, String ed2){
        op1 = Float.parseFloat(ed1);
        op2 = Float.parseFloat(ed2);
        Log.i(" on click Fram Main: %s", String.valueOf(op1));
        Log.i("%s !!!!!!!!!!!!!!!!", String.valueOf(op2));
        Log.i("my botton is: %S", btAction);
        //Button btn = findViewById(((Button)view).getId());

        if (btAction.equals("/")) {
               if (!div_check(op2)) {
                    return;
               }
        }

        //int btId = ((Button)view).getId();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //---change the current fragment to fragContainer==fragA---
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragContainer, FragB.class, null, "FRAG2")
                        .addToBackStack("BBB")
                        .commit();
                getSupportFragmentManager().executePendingTransactions();
                fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAG2");
            }
            else{
                fragB = (FragB)getSupportFragmentManager().findFragmentById(R.id.frag2);
            }
            float res= calRes(btAction);
            Log.i(" res in main: %s @@@@@@@@@@@@@@@", String.valueOf(res));
            fragB.onNewClickSetResult(res); // sent the result to frag B in order to display it.
        }



    private float calRes(String btn) {
        String str_res = "";

        switch (btn){
            case "+":
                initial_result = op1 + op2;
                break;
            case "-":
                initial_result = op1 - op2;
                break;
            case "*":
                initial_result = op1 * op2;
                break;
            case "/":
                initial_result = op1 / op2;
                break;
            default:
                break;
        }
        str_res = String.format("%." + zeroCnt + "f", initial_result);
        Log.i(" calc res: %f", String.valueOf(initial_result));
        return initial_result;
    }

    public boolean div_check(float ope2){
        if(ope2 == 0) {
            showToast("Divide by zero!!", getApplicationContext());
            return false;
        }
        return true;
    }

    private void showToast(CharSequence str, Context context){
        Toast toast = Toast.makeText(context, str, duration);
        toast.show();
    }

}