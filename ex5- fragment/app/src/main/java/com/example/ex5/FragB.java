package com.example.ex5;

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
    TextView tvRes, tvExample;
    SeekBar sb;
    float numRes;
    static int zeroCnt = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(false);
        super.onCreate(savedInstanceState);

    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_2, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sb = view.findViewById(R.id.sbZero);
        sb.setOnSeekBarChangeListener(this);
        this.tvRes= (TextView) view.findViewById(R.id.tvResulat);
        this.tvExample = (TextView) view.findViewById(R.id.tvExample);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //listener.onProgressChanged(seekBar, progress, fromUser);

        if(tvExample != null && tvRes != null) {
            zeroCnt = seekBar.getProgress();
            float num = 123;
            String floatStr = String.format("%." + zeroCnt + "f", num);
            tvExample.setText("Example: " + floatStr);
            tvRes.setText(String.format( "%." + zeroCnt + "f", numRes));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //the activity informs fragB about new click in fragA
    public void onNewClickSetResult(float res) {
        Log.i("this is res: %f $$$$$$$$$$$", String.valueOf(res));
        numRes=res;
        tvRes.setText(String.format( "%." + "2" + "f", numRes));
        sb.setProgress(2);
    }

    public interface FragBListener {
//        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);
    }
}
