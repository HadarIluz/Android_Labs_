package com.example.ex7;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryFragment extends Fragment {


    private RecyclerView recyclerView;
    CountryFragmentListener countryFragmentListener; // hold the mainActivity reference.
    private CountriesAdapter countriesAdapter;

    /*This function connect our mainActivity with the B fragment
    when the context var is the mainActivity*/
    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.countryFragmentListener = (CountryFragmentListener)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    getActivity().getClass().getName() +
                    " must implements the interface 'FragBListener'");
        }
        super.onAttach(context);
    }

    public void onViewCreated(View view, Bundle savedInstanceState)  {
        recyclerView = view.findViewById(R.id.recycle_view);
        super.onViewCreated(view, savedInstanceState);
    }


    public View onCreateView(View view, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycle_view);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.countryRow_frag, container, false);
    }


    //the interface of this fragment that include the methods
    public interface CountryFragmentListener{
        //put here methods you want to utilize to communicate with the hosting activity
    }
}