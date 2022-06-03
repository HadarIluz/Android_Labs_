package com.example.ex7;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

//my
public class Frag2  extends Fragment {

    //note: fragments should not hold LiveData instances because his role is to display data, not hold state.
    private TextView detailsTextView;
    private MainMVVM myViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        detailsTextView = view.findViewById(R.id.country_details_text_view);
        myViewModel = MainMVVM.getInstance(getActivity().getApplication());

        // OBSERVE
        Observer<Country> userListUpdateObserver = new Observer<Country>() {
            @Override
            public void onChanged(Country country) {
                if(country != null){
                    Log.i("TEST load", "da");
                    detailsTextView.setText(country.getDetails());
                }else{
                    detailsTextView.setText("");
                }

            }
        };


        myViewModel.getItemSelected().observe(getViewLifecycleOwner(), userListUpdateObserver);

        super.onViewCreated(view, savedInstanceState);

    }
}









/** *
 * https://developer.android.com/topic/libraries/architecture/livedata
 */

/***
 * https://developer.android.com/topic/libraries/architecture/viewmodel.html#sharing
 */
