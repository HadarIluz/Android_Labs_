package com.example.ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Frag1.Frag1Listener, Frag2.Frag2Listener{
    EditText operand1;
    EditText operand2;
    Frag2 frag2;

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

    @Override
    public void OnClickEvent(View v) {
        operand1 = (EditText)findViewById(R.id.etNo1);
        operand2 = (EditText)findViewById(R.id.etNo2);
        Button btn = findViewById(v.getId());
        if(checkOperandExist(operand1, operand2)) {
            op1 = Float.parseFloat(operand1.getText().toString());
            op2 = Float.parseFloat(operand2.getText().toString());
            if (btn.getText().toString().equals("/")) {
                if (!div_check(op2)) {
                    return;
                }
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragContainer, Frag2.class, null, "FRAG2")
                        .addToBackStack("BBB")
                        .commit();
                getSupportFragmentManager().executePendingTransactions();
                frag2 = (Frag2) getSupportFragmentManager().findFragmentByTag("FRAG2");
            }
            else{
                frag2 = (Frag2)getSupportFragmentManager().findFragmentById(R.id.frag2);
            }
            String res= calRes();
            frag2.onNewClickSetResult(res);
            //frag2.setOperandResult(op1, op2, btn.getText().toString(), zeroCnt);
        }
    }


    private String calRes() {
        String str_res = "";

        switch (strAction){
            case "+":
                initial_result = op1 + op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            case "-":
                initial_result = op1 - op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            case "*":
                initial_result = op1 * op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            case "/":
                initial_result = op1 / op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                break;
            default:
                break;
        }
        return str_res;
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