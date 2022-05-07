package com.example.ex6;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag1 extends Fragment implements View.OnClickListener {
    Frag1Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag1.Frag1Listener)context;
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
        return inflater.inflate(R.layout.frag_1, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.btPlus).setOnClickListener(this);
        view.findViewById(R.id.btDiv).setOnClickListener(this);
        view.findViewById(R.id.btMin).setOnClickListener(this);
        view.findViewById(R.id.btMul).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        listener.OnClickEvent(v);
    }


    public interface Frag1Listener{
        public void OnClickEvent(View v);
    }
}
