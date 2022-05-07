package com.example.ex6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SettingsDialog extends DialogFragment {
    int zeroCnt = 0;

    public SettingsDialog() {}

    public static SettingsDialog newInstance(String title, int num) {
        SettingsDialog frag = new SettingsDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("numOfZero", num);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        zeroCnt = getArguments().getInt("numOfZero");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.seekbar, null);
        alertDialogBuilder.setView(v);
        SeekBar sb = (SeekBar) v.findViewById(R.id.sbZero);
        sb.setProgress(zeroCnt);    //set progress to the seekBar by the zeroCnt
        TextView tvExample = (TextView)v.findViewById(R.id.tvExample);
        float num = 123;
        String floatStr = String.format("%." + zeroCnt + "f", num);
        tvExample.setText("Example: " + floatStr);

//------------------------------- SEEKBAR -------------------------------
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(tvExample != null) {
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
//------------------------------- SEEKBAR -------------------------------

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
