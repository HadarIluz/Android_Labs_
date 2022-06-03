package com.example.ex7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//my
public class MainActivity extends AppCompatActivity implements CountriesAdapter.ICountriesAdapterListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //-->lab 7b: this class now implements CountriesAdapter.ICountriesAdapterListener
    @Override
    public void countryClicked() {
        Frag2 frag2;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            frag2 = (Frag2) getSupportFragmentManager().findFragmentById(R.id.frag2);
        else //I am in portrait
        {
            frag2 = new Frag2();
            getSupportFragmentManager().beginTransaction().
                    add(R.id.mainActivity, frag2).//add on top of the static fragment
                    addToBackStack("BBB").//cause the back button scrolling through the loaded fragments
                    commit();
            getSupportFragmentManager().executePendingTransactions();
        }

    }
}