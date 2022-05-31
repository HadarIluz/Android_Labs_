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

    //-->lab 7b: this class now implements CountriesAdapter.ICountriesAdapterListener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-->lab7b:
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        //menu.setLogo(R.drawable.logo);
        menu.setDisplayUseLogoEnabled(true);
    }



    //-->lab7b:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        //inflater.inflate(R.menu.menu_exit, menu);
        //inflater.inflate(R.menu.world_settings, menu);

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.exit:
//                MyExit newFragment = MyExit.newInstance();
//                newFragment.show(getSupportFragmentManager(), "exitDialog");
//                break;
//            case R.id.worldSettings:
//                //countriesAdapter.reloadCounteyList(this);
//                break;
//        }
//
//        return  true;
//    }

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