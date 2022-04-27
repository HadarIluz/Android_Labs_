package com.example.ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragA.FragAListener, FragB.FragBListener {
    EditText operand1;
    EditText operand2;
    FragB fragB;

    static float op1, op2, initial_result;
    static String strAction = "";
    static int zeroCnt = 0;

    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operand1 = (EditText)findViewById(R.id.etNo1);
        operand2 = (EditText)findViewById(R.id.etNo2);
    }


    public void OnClickEvent(View view, String ed1, String ed2){
        op1 = Float.parseFloat(ed1);
        op2 = Float.parseFloat(ed2);
        Log.i(" on click Fram Main: %s", String.valueOf(op1));
        Log.i("%s !!!!!!!!!!!!!!!!", String.valueOf(op2));
        Button btn = findViewById(((Button)view).getId());

        if (btn.getText().toString().equals("/")) {
               if (!div_check(op2)) {
                    return;
               }
        }
        int btId = ((Button)view).getId();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
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
            float res= calRes(btId);
            Log.i(" res in main: %s @@@@@@@@@@@@@@@", String.valueOf(res));
            fragB.onNewClickSetResult(res);
            //fragB.setOperandResult(op1, op2, btn.getText().toString(), zeroCnt);
        }



    private float calRes(int btn) {
        String str_res = "";

        switch (btn){
            case R.id.btPlus:
                initial_result = op1 + op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            case R.id.btMin:
                initial_result = op1 - op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            case R.id.btMul:
                initial_result = op1 * op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            case R.id.btDiv:
                initial_result = op1 / op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            default:
                break;
        }
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

    public boolean checkOperandExist(EditText operand1, EditText operand2){
        if(operand1.getText().toString().isEmpty()){
            showToast("fill operand1", getApplicationContext());
            return false;
        }
        else if(operand2.getText().toString().isEmpty()){
            showToast("fill operand2", getApplicationContext());
            return false;
        }
        return true;
    }

    private void showToast(CharSequence str, Context context){
        Toast toast = Toast.makeText(context, str, duration);
        toast.show();
    }

}