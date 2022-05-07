package com.example.ex6;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragB extends Fragment implements SeekBar.OnSeekBarChangeListener{
    FragBListener listener;
    private TextView tvRes;
    float numRes;
    private String zeroCnt = "2";

    //bonding the main activity with this fragment (B), Gets context as mainActivity
    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (FragBListener)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    getActivity().getClass().getName() +
                    " must implements the interface 'FragBListener'");
        }

        super.onAttach(context);
    }
    //Take all the info to the buffer memory
    //-->Do inflate to the layout of the fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*-->Informs the operating system that there is a menu.*/
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.frag_2, container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(false);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.tvRes= (TextView) view.findViewById(R.id.tvResulat);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    //implements methods of seekBar onClickListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        zeroCnt = String.valueOf(progress);
        float num = 123;
        String floatStr = String.format("%." + zeroCnt + "f", num);
        tvRes.setText(String.format( "%." + zeroCnt + "f", numRes));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /*This function gets the result which has been calculate in the mainActivity.*/
    //the activity informs fragB about new click in fragA
    public void onNewClickSetResult(float res) {
        Log.i("this is res: %f $$$$$$$$$$$", String.valueOf(res));
        numRes=res;
        tvRes.setText(String.format( "%." + zeroCnt + "f", numRes));
    }

    public void setZeroFormatResult(String newZeroCnt) {
        zeroCnt= newZeroCnt;
    }

    public interface FragBListener {
        //put here methods you want to utilize to communicate with the hosting activity
    }
}
