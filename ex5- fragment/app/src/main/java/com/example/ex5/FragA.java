package com.example.ex5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragA extends Fragment implements OnClickListener {
    FragAListener listener;  // hold the mainActivity referance
    EditText op1, op2;

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (FragAListener)context;
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
        this.op1 = view.findViewById(R.id.etNo1);
        this.op2 = view.findViewById(R.id.etNo2);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        listener.OnClickEvent(v,op1.getText().toString(),op2.getText().toString());;
    }

    //the interface of this fragment that include the methods
    public interface FragAListener{
        public void OnClickEvent(View view, String ed1, String ed2);
    }

}
