package com.example.ex6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/*this dialog attach to frag B and we will display the seekbar only in frag B*/
public class MySettingDialog extends DialogFragment {

    static int zeroCnt = 0;
    private ISettingDialog mListener;
    private SeekBar sb;
    //public static String PROG = "progress";

    public static MySettingDialog newInstance(String title, int num) {
        MySettingDialog frag = new MySettingDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("numOfZero", num);
        frag.setArguments(args);
        return frag;
    }

    public interface ISettingDialog {
        public void onSeekBarChanged(int progress);
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        zeroCnt = getArguments().getInt("numOfZero");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.seekbar, null); //-->inflate the new seekBar layout.
        alertDialogBuilder.setView(v);

        sb = (SeekBar) v.findViewById(R.id.sbZero);
        sb.setProgress(zeroCnt);    //set progress to the seekBar by the zeroCnt
        TextView tvExample = (TextView)v.findViewById(R.id.tvExample);

        float num = 123;
        String floatStr = String.format("%." + zeroCnt + "f", num);
        tvExample.setText("Example: " + floatStr);


    /*############## Seek Bar Methods ##############*/
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (tvExample != null) {
                    zeroCnt = seekBar.getProgress();
                    float num = 123;
                    String floatStr = String.format("%." + zeroCnt + "f", num);
                    tvExample.setText("Example: " + floatStr);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
    /*############## End of Seek Bar Methods ##############*/

        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendBackResult();
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        return alertDialogBuilder.create();
    }




    public interface SettingsDialogListener {
        void onFinishEditDialog(int inputNum);
    }

    // Call this method to send the data back to the parent fragment
    public void sendBackResult() {
        SettingsDialogListener listener = (SettingsDialogListener) getTargetFragment();
        listener.onFinishEditDialog(zeroCnt);
        dismiss();
    }


}
