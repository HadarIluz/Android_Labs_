package com.example.ex7;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

/*MVVM- Model0View-ViewModel*/
public class MainMVVM extends ViewModel {
    private static MainMVVM instance;

    // ******* The observable vars *********************

    //  The MutableLiveData class has the setValue(T) and postValue(T)
    //  methods publicly and you must use these if you need to edit the value stored in a LiveData object.
    //  Usually, MutableLiveData is used in the ViewModel and then the ViewModel only exposes

    private MutableLiveData<ArrayList<Country>> countryLiveData ;
    private MutableLiveData<Country> indexItemSelected;
    private MutableLiveData<Integer> positionSelected;
    // *****************************

    public MainMVVM(@NonNull Application application) {
        //super(application);
        // call your Rest API in init method
        init(application);
    }

    public MutableLiveData<ArrayList<Country>>  getCountryLiveData() {
        return countryLiveData;
    }

    public MutableLiveData<Country> getItemSelected() {
        return indexItemSelected;
    }

    public LiveData<ArrayList<Country>> getCountriesLiveData() {
        return countryLiveData;
    }

    public void setItemSelect(Country country){
        indexItemSelected.setValue(country);
    }

    public void setPositionSelected(Integer index){
        positionSelected.setValue(index);
    }

    public MutableLiveData<Integer> getPositionSelected(){
        return positionSelected;
    }

    public void setCountryLiveData(ArrayList<Country> list){
        countryLiveData.setValue(list);
    }

    public static MainMVVM getInstance(Application application){
        if(instance ==null){
            instance =new MainMVVM(application);
        }
        return instance;
    }


    // This use the setValue
    public void init(Application application){
        countryLiveData = new MutableLiveData<>();
        indexItemSelected = new MutableLiveData<>();
        positionSelected = new MutableLiveData<>();
        positionSelected.setValue(-1);
        countryLiveData.setValue(CountryXMLParser.parseCountries(application.getApplicationContext()));
    }
}
