package com.example.ex7;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;

/*MVVM- Model0View-ViewModel*/
public class MainMVVM extends AndroidViewModel {
    private static MainMVVM instance;

    // ******* The observable vars *********************

    //  The MutableLiveData class has the setValue(T) and postValue(T)
    //  methods publicly and you must use these if you need to edit the value stored in a LiveData object.
    //  Usually, MutableLiveData is used in the ViewModel and then the ViewModel only exposes

    private MutableLiveData<ArrayList<Country>> countryLiveData ;
    private ArrayList<Country> indexItemSelected;
    private MutableLiveData<Integer> positionSelected;
    // *****************************

    private int itemSelected;
    //------------------file objects-------------------------------
    private static String file_path;
    public static final String FILE_NAME="remove_countries.txt";
    private static ArrayList<String> remove_countries;
    private ArrayList<Country> tempCountryList;
    //--------------------------------------------------------------

    public MainMVVM(@NonNull Application application) {
        super(application);
        // call your Rest API in init method
        countryLiveData = new MutableLiveData<>();//-->Lab8
        positionSelected = new MutableLiveData<>();//-->Lab8
        init(application);
    }

    public MutableLiveData<ArrayList<Country>>  getCountryLiveData() {
        return countryLiveData;
    }
    public MutableLiveData<Integer> getItemSelectedLiveData() {
        return positionSelected;
    }
    public ArrayList<Country> getItemSelected() {
        return indexItemSelected;
    }

    public LiveData<ArrayList<Country>> getCountriesLiveData() {
        return countryLiveData;
    }

    public void setItemSelect(Country country){
        indexItemSelected.add(country);
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
        //countryLiveData = new MutableLiveData<>();
        //indexItemSelected = new MutableLiveData<>();
        //positionSelected = new MutableLiveData<>();
        positionSelected.setValue(-1);
        //countryLiveData.setValue(CountryXMLParser.parseCountries(application.getApplicationContext()));

        //_______________________

        indexItemSelected = CountryXMLParser.parseCountries(application.getApplicationContext());
        countryLiveData.setValue(indexItemSelected);
        // initially files params
        file_path = application.getApplicationContext().getFilesDir().getAbsolutePath();
        tempCountryList = new ArrayList<Country>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(application.getApplicationContext());
        boolean remember = prefs.getBoolean("switch_preference_1",false);

        //--------SharedPreferences-----------
        remove_countries = new ArrayList<>();
        for(Map.Entry<String,?> entry : prefs.getAll().entrySet()){
            if (entry.getValue() instanceof String) {
                remove_countries.add(String.valueOf(entry.getValue()));
            }
        }
        //-----End SharedPreferences-----------

        //---------Raw file-----------
//        remove_countries = new ArrayList<String>(Arrays.asList(readFile().split("\n")));
        //-----End Raw file-----------

        if(remember == true)
        {
            for (Country country : countryLiveData.getValue()) {
                if (!remove_countries.contains(country.getName())) {
                    tempCountryList.add(country);
                }
            }
            countryLiveData.setValue(tempCountryList);
        }
        else
        {
            //-----SharedPreferences-----------
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
            //-------End SharedPreferences------

            //-----Raw file-----------
//            try {
//                FileOutputStream writer = new FileOutputStream(file_path + File.separator + FILE_NAME);
//                writer.write(("").getBytes());
//                writer.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
            //-----End Raw file-----------
        }

    }






    //---------function for raw file---------
    public void writeData(String data) {
        File directory = new File(file_path);
        if(!directory.exists())
            directory.mkdir();

        File newFile = new File(file_path,File.separator + FILE_NAME);
        try  {
            if(!newFile.exists())
                newFile.createNewFile();

            FileOutputStream fOut = new FileOutputStream(newFile,true);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fOut);
            outputWriter.write(data + "\n");
            outputWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String readFile() {
        StringBuilder text = null;
        String line;
        //Get the text file
        File file = new File(file_path,File.separator+FILE_NAME);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            //Read text from file
            InputStream inputStream = new FileInputStream(file);
            text = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            inputStream.close();
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}
