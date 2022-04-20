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

public class FragB extends Fragment {
	TextView tvValue;
	FragBListener listener;
	static int myInt=0;


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
		this.tvValue= (TextView) view.findViewById(R.id.textView1);
		tvValue.setText(""+myInt);
		super.onViewCreated(view, savedInstanceState);
	}

	//the activity informs fragB about new click in fragA
	public void onNewClick(){
		myInt++;
		tvValue.setText(""+myInt);
	}


	public interface FragBListener{
		//put here methods you want to utilize to communicate with the hosting activity
	}

}
