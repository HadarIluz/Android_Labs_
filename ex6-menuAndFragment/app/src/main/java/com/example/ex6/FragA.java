package com.example.ex6;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragA extends Fragment implements OnClickListener {
    FragAListener listener;  // hold the mainActivity referance
    private EditText op1, op2;
    private Button addButton,minusButton,divideButton,mulButton;

    //bonding the main activity with this fragment (A), Gets context as mainActivity
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

    //Take all the info to the buffer memory
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_1, container,false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myTextWatcher textWatcher = new myTextWatcher();
        addButton = view.findViewById(R.id.btPlus);
        divideButton = view.findViewById(R.id.btDiv);
        minusButton = view.findViewById(R.id.btMin);
        mulButton = view.findViewById(R.id.btMul);
        op1 = view.findViewById(R.id.etNo1);
        op2 = view.findViewById(R.id.etNo2);
        op1.addTextChangedListener(textWatcher);
        op2.addTextChangedListener(textWatcher);

        addButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        setAllButtonState(false);
        super.onViewCreated(view, savedInstanceState);
    }


    // Handle onClick event, sent to mainActivity all the relevant data for the calculation, using interface
    @Override
    public void onClick(View v) {
        Log.i(" on click Fram A: %f", op1.getText().toString());
        Log.i(" %f ###########", op2.getText().toString());
        Button btn = v.findViewById(v.getId());
        String myPressBtn=btn.getText().toString();
        Log.i("button frag A is: %s", myPressBtn);
        listener.OnClickEvent(myPressBtn,op1.getText().toString(),op2.getText().toString());
    }

    //the interface of this fragment that include the methods
    public interface FragAListener{
        public void OnClickEvent(String btAction, String ed1, String ed2);
    }

    /* This function disable the buttons */
    private void setAllButtonState(boolean state) {
        addButton.setEnabled(state);
        minusButton.setEnabled(state);
        mulButton.setEnabled(state);
        divideButton.setEnabled(state);
    }

    /* Member class option */
    private class myTextWatcher  implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // nothing to do.
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // nothing to do.
        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (op1.getText().toString().length() != 0 && op2.getText().toString().length() != 0){
                addButton.setEnabled(true);
                minusButton.setEnabled(true);
                mulButton.setEnabled(true);
                float num2 = Float.parseFloat(op2.getText().toString());
                if(num2 != 0){
                    divideButton.setEnabled(true);
                }
                else  divideButton.setEnabled(false);
            }
            else {
                setAllButtonState(false);
            }
        }
    }

}
