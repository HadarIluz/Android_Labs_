package com.example.ex5x;

//import android.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


public class FragA extends Fragment implements OnClickListener{
	FragAListener listener;

	@Override
	public void onAttach(@NonNull Context context) {
		try{
			this.listener = (FragAListener)context;
		}catch(ClassCastException e){
			throw new ClassCastException("the class " +
					context.getClass().getName() +
					" must implements the interface 'FragAListener'");
		}
		super.onAttach(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_a, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		view.findViewById(R.id.button1).setOnClickListener(this);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		listener.OnClickEvent();
	}

	public interface FragAListener{
		public void OnClickEvent();
	}
}
