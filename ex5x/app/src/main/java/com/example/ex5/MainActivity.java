package com.example.ex5x;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

public abstract class MainActivity extends AppCompatActivity implements FragA.FragAListener, FragB.FragBListener{
	EditText operand1;
	EditText operand2;
	FragB fragB;
	FragA fragA;

	int duration = Toast.LENGTH_SHORT;

	static float op1, op2, initial_result;
	static String strAction = "";
	static int zeroCnt = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		operand1 = (EditText)findViewById(R.id.etNo1);
		operand2 = (EditText)findViewById(R.id.etNo2);
/*
		FragB fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");

		if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)){
			if (fragB != null) {
				//getSupportFragmentManager().beginTransaction()
				//		.show(fragB)
				//		.commit();
				getSupportFragmentManager().beginTransaction().show(fragA).commit(); //new-hadar
			}
			else {
				getSupportFragmentManager().beginTransaction()
						.add(R.id.fragContainer, FragB.class,null, "FRAGB")
						.commit();
			}
			getSupportFragmentManager().executePendingTransactions();
		}

 */
	}


	//@Override
	public void OnClickEvent(View v) {

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
						.add(R.id.fragContainer, FragB.class, null, "FRAGB")
						.addToBackStack("BBB")
						.commit();
				getSupportFragmentManager().executePendingTransactions();
				FragB fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");
			}
			else{
				FragB fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");
			}
			String res= calRes();
			fragB.onNewClickSetResult(res);
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

	private boolean div_check(float ope2){
		if(ope2 == 0) {
			showToast("Divide by zero!!", getApplicationContext());
			return false;
		}
		return true;
	}

	private boolean checkOperandExist(EditText operand1, EditText operand2){
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
