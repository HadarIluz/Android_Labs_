package com.example.ex6;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class Frag2 extends Fragment implements SettingsDialog.SettingsDialogListener{
    Frag2Listener listener;
    TextView tvRes, tvExample;
//    SeekBar sb;
    static float op1, op2, initial_result;
    static String action = "";
    static int zeroCnt = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(false);
        super.onCreate(savedInstanceState);

    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.settings, menu);    //add setting menu to the main menu
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSettings: // the settings menu pressed - create a dialog settings alert
                FragmentManager fm = getActivity().getSupportFragmentManager();
                SettingsDialog alertDialog = SettingsDialog.newInstance("Closing the application", zeroCnt);
                alertDialog.setTargetFragment(this, 1);
                alertDialog.show(fm, "fragment_alert");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag2Listener)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    getActivity().getClass().getName() +
                    " must implements the interface 'FragBListener'");
        }

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.frag_2, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        sb = view.findViewById(R.id.sbZero);
//        sb.setOnSeekBarChangeListener(this);
        this.tvRes= (TextView) view.findViewById(R.id.tvResulat);
        this.tvExample = (TextView) view.findViewById(R.id.tvExample);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setOperandResult(float op1, float op2, String action){
        this.op1 = op1;
        this.op2 = op2;
        this.action = action;
        //this.zeroCnt = zeroCnt;
        String str_res = "";
        switch (action){
            case "+":
                initial_result = op1 + op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                this.tvRes.setText(op1 + " + " + op2 + " = " + str_res);
                break;
            case "-":
                initial_result = op1 - op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                this.tvRes.setText(op1 + " - " + op2 + " = " + str_res);
                break;
            case "*":
                initial_result = op1 * op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                this.tvRes.setText(op1 + " * " + op2 + " = " + str_res);
                break;
            case "/":
                initial_result = op1 / op2;
                str_res = String.format("%." + zeroCnt + "f", initial_result);
                this.tvRes.setText(op1 + " / " + op2 + " = " + str_res);
                break;
            default:
                break;
        }

    }

    //this function gets parameters from the settings dialog
    @Override
    public void onFinishEditDialog(int inputNum) {
        zeroCnt = inputNum;
        if(!tvRes.getText().toString().isEmpty())
            setOperandResult(op1, op2, action);
    }


//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        //listener.onProgressChanged(seekBar, progress, fromUser);
//
//        if(tvExample != null && tvRes != null) {
//            zeroCnt = seekBar.getProgress();
//            float num = 123;
//            String floatStr = String.format("%." + zeroCnt + "f", num);
//            tvExample.setText("Example: " + floatStr);
//            if (!tvRes.getText().toString().isEmpty()) {
//                setOperandResult(op1, op2, action, zeroCnt);
//            }
//        }
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }

    public interface Frag2Listener{
//        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);
    }
}
