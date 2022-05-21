package com.example.ex7;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//my
public class CountryFragment extends Fragment  {

    private RecyclerView recyclerView;
    CountriesFragmentListener listener; // hold the mainActivity reference
    private CountriesAdapter countriesAdapter;

    /*1.we do infale to the layout of the fragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.countries_list_frag, container, false);
    }

    /*2. we sech by using "findViewById" the recycle_view that already done --> at this point!*/
    public void onViewCreated(View view, Bundle savedInstanceState)  {
        recyclerView = view.findViewById(R.id.recycle_view);
        super.onViewCreated(view, savedInstanceState);
    }

    //*******************************************************************
    // After activity created we can set the adapter for the recycle view
    //*******************************************************************
    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        countriesAdapter = new CountriesAdapter(getActivity()); // call the constructor
        /*-------Here we do the connection between to the adapter-------*/
        recyclerView.setAdapter(countriesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }




    @Override
    public void onAttach(@NonNull Context context) {
        //this connect our mainactivity with the B fragment when the context var is the mainactivity
        try{
            this.listener = (CountriesFragmentListener)context;
        }catch(ClassCastException e){}
        super.onAttach(context);
    }


    //the interface of this fragment that include the methods
    public interface CountriesFragmentListener{
        //put here methods you want to utilize to communicate with the hosting activity
    }
}

