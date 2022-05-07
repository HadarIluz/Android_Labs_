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
public class MySettingDialog extends DialogFragment implements SeekBar.OnSeekBarChangeListener{

    public static String zeroCnt;
    private ISettingDialog mListener;
    private SeekBar sb;
    private TextView tvExample;

    public MySettingDialog() {}

    public static MySettingDialog newInstance(String title, int num) {
        MySettingDialog frag = new MySettingDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("numOfZero", String.valueOf(num));
        frag.setArguments(args);
        return frag;
    }

    public interface ISettingDialog {
        public void onSeekBarChanged(int progress);
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        zeroCnt = getArguments().getString("numOfZero");
        Log.i("zeroCnt is (MySettingDialog): %", zeroCnt);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(),R.style.SettingsDialog);
        alertDialogBuilder.setTitle(title);

        View seekBarView = getActivity().getLayoutInflater().inflate(R.layout.seekbar, null);

        //alertDialogBuilder.setView(seekBarView);

        sb = (SeekBar) seekBarView.findViewById(R.id.sbZero);
        tvExample = (TextView)seekBarView.findViewById(R.id.tvExample);
        sb.setOnSeekBarChangeListener(this);

        sb.setProgress(Integer.parseInt(zeroCnt));    //set progress to the seekBar by the zeroCnt
        float num = 123;
        String floatStr = String.format("%." + zeroCnt + "f", num);
        tvExample.setText("Example: " + floatStr);

//        /*############## Seek Bar Methods ##############*/
//        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if (tvExample != null) {
//                    int numberZERO= seekBar.getProgress();
//                    zeroCnt =String.valueOf(numberZERO);
//                    float num = 123;
//                    String floatStr = String.format("%." + zeroCnt + "f", num);
//                    tvExample.setText("Example: " + floatStr);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//
//        });
//        /*############## End of Seek Bar Methods ##############*/

// Inflate and set the layout for the dialog
        alertDialogBuilder.setTitle(R.string.settings_precision)
                .setView(seekBarView)
                .setPositiveButton("Ok",
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




    public interface SettingsDialogListener {
        void onFinishEditDialog(int inputNum);
    }

    // Call this method to send the data back to the parent fragment
    public void sendBackResult() {
        SettingsDialogListener listener = (SettingsDialogListener) getTargetFragment();
        String num = zeroCnt;
        listener.onFinishEditDialog(Integer.parseInt(num));
        dismiss();
    }






    @Override
    public void onAttach(@NonNull Context context) {
        //this connect our mainactivity with the B fragment when the context var is the mainactivity
        try{
            this.mListener = (ISettingDialog)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    getActivity().getClass().getName() +
                    " must implements the interface 'FragBListener'");
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }











    /*############## Seek Bar Methods ##############*/
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (tvExample != null) {
                int numberZERO= seekBar.getProgress();
                zeroCnt =String.valueOf(numberZERO);
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

    /*############## End of Seek Bar Methods ##############*/

}
