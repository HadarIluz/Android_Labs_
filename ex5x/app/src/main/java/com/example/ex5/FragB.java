package com.example.ex5x;

//import android.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragB extends Fragment implements SeekBar.OnSeekBarChangeListener{
	//TextView tvValue;
	FragBListener listener;
	TextView tvRes, tvExample;
	SeekBar sb;
	static int zeroCnt = 0;
	static String action = "";

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
		return inflater.inflate(R.layout.frag_b, container,false);
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
	public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
		if(tvExample != null && tvRes != null) {
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




	public interface FragBListener{
		//put here methods you want to utilize to communicate with the hosting activity
	}


	//new-hadar
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	//the activity informs fragB about new click in fragA
	public void onNewClickSetResult(String res) {
		this.tvRes.setText( res );
	}

}
