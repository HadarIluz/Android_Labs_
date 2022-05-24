package com.example.ex6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/*this dialog attach to frag B and we will display the seekbar only in frag B*/
public class MySettingDialog extends DialogFragment implements SeekBar.OnSeekBarChangeListener {
    public static String PROG = "progress";
    private ISettingDialog mListener;
    private SeekBar sb;
    private TextView tvExample;

    @Override
    public void onAttach(@NonNull Context context) {
        //this connect our mainActivity with the B fragment when the context var is the mainActivity
        try{
            this.mListener = (ISettingDialog)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    getActivity().getClass().getName() +
                    " must implements the interface 'FragBListener'");
        }
        super.onAttach(context);
    }

    public static MySettingDialog newInstance(String title) {
        MySettingDialog frag = new MySettingDialog();
        return frag;
    }

    public interface ISettingDialog {
        public void onSeekBarChanged(int progress);
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.SettingsDialog);
        alertDialogBuilder.setTitle(title);

        View seekBarView = getActivity().getLayoutInflater().inflate(R.layout.seek_bar, null);

        sb = (SeekBar) seekBarView.findViewById(R.id.sbZero);
        tvExample = (TextView)seekBarView.findViewById(R.id.tvExample);
        sb.setOnSeekBarChangeListener(this);


        Bundle zeroCntArg = getArguments();
        if (zeroCntArg != null) {
            int zeroCnt = zeroCntArg.getInt(PROG);
            if (0 <= zeroCnt && zeroCnt <= 5){}
                sb.setProgress(zeroCnt);
        }

        // Inflate and set the layout for the dialog
        alertDialogBuilder.setTitle(R.string.settings_precision)
                .setView(seekBarView)
                .setPositiveButton("Apply",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mListener.onSeekBarChanged(sb.getProgress());
                                dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                );
        return alertDialogBuilder.create();
    }


   /*################## Seek Bar Methods ##################*/
    @Override
    public void onProgressChanged(SeekBar seekBar, int mode0to5, boolean fromUser) {

        switch(mode0to5){
            case 0:{
                //String floatStr = String.format("%." + zeroCnt + "f", num);
                //tvExample.setText("Example: " + floatStr);
                tvExample.setText("example: 123");
            }
            break;
            case 1:{
                tvExample.setText("example: 123.0");
            }
            break;
            case 2:{
                tvExample.setText("example: 123.00");
            }
            break;
            case 3:{
                tvExample.setText("example: 123.000");
            }
            break;
            case 4:{
                tvExample.setText("example: 123.0000");
            }
            break;
            case 5:{
                tvExample.setText("example: 123.00000");
            }
            break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    /*############## End of Seek Bar Methods ##############*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
