package com.example.ex7;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//my
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {
    //in the < > is the name of the internal class.
    private ArrayList<Country> countriesList;
    private int selectedRow = -1;

    //-->lab7b:
    private MainMVVM viewModel;
    private Application myContext;
    private  ICountriesAdapterListener mListener;
    private CountriesViewHolder viewHolder;
    private Context mContext;

    /*This constructor fills our list named:countriesList with all the
     information we have in a database which is an XML file*/
    public CountriesAdapter(Application application, Context context) {
        countriesList = CountryXMLParser.parseCountries(context);
        //-->lab7b:
        myContext = application;
        mContext = context;
        viewModel = MainMVVM.getInstance(myContext);
        countriesList = viewModel.getCountriesLiveData().getValue();
        viewModel = MainMVVM.getInstance(application);
    }

    /* ##########_3 functions from Adapter abstract class_##########*/
   /*3 function that we must to implement because we extends (Heirs) the RecyclerView.Adapter class.
   * 1. onCreateViewHolder
   * 2. onBindViewHolder
   * 3. getItemCount
   *   */

    /*In this function we do inflate to the layout: "country_row_frag"
    * and sent it to the constructor*/
    @Override
    public CountriesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        mListener = (ICountriesAdapterListener)parent.getContext();//-->lab7b:
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View countryView = inflater.inflate(R.layout.country_row_frag, parent, false);
        viewHolder = new CountriesViewHolder(countryView);

         return viewHolder;
    }

    /*This function gets 1.the 'countryViewHolder' that we want to fill with all the data
    * and 2. the location in the data structure that from there(from the location in the array) we take the data.*/
    @Override
    public void onBindViewHolder(CountriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Country country = countriesList.get(position);

        //-->Lab7b: OBSERVE
        // Here we will observe and update the selected row
        Observer<Integer> observeSelectedIndex = new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                selectedRow = index;
            }
        };
        viewModel.getPositionSelected().observe((LifecycleOwner)mContext, observeSelectedIndex);

        if(selectedRow == position){
            holder.row_linearLayout.setBackgroundColor(Color.parseColor("#EC96EC"));
        }else{
            holder.row_linearLayout.setBackgroundColor(Color.parseColor("#E4E4E4"));
        }

        // This listener will change the color of selected row
        holder.row_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRow = position;
                notifyItemChanged(selectedRow);
                notifyDataSetChanged();//notify there is change and do refresh.
                viewModel.setItemSelect(country);
                viewModel.setPositionSelected(selectedRow);
                mListener.countryClicked();
            }
        });

        //Long Press
        //*****************************************************************************************
        // This listener function call the function removeItem -> wil remove row item from the ArrayList
        //*****************************************************************************************
        holder.row_linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                System.out.println(getItemCount());
                countriesList.remove(position);// ****** this notify to the recycle that there is change in data base


                viewModel.setCountryLiveData(countriesList);

                // we must set it -1 because the item was removed and this will help us to remove info data from fragment
                viewModel.setPositionSelected(-1);

                viewModel.setItemSelect(null);

                // this logic will keep the selected row select when change the position index
                if (position < selectedRow){
                    selectedRow--;
                } else if (position == selectedRow){
                    selectedRow = -1;
                }
                notifyDataSetChanged();
                return true;
            }
        });

        holder.bindData(country);
    }

    /*3.This function return how data from this specific type there is in the database */
    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public void sortByName() {
        Comparator<Country> sortByName = Comparator.comparing(Country:: getName);
        Collections.sort(countriesList, sortByName);
    }
    /* ##########_3 functions from Adapter abstract class_##########*/



    /* @@@@@@@@@@@@@@@@@@@@@@@@@@@_Internal_Class_@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    // Each row in RecyclerView will get reference of this CountriesViewHolder
    // *** Include the function that can remove the row
    public class CountriesViewHolder extends RecyclerView.ViewHolder {
        private final Context   context;
        private final View      countryItem;
        private final ImageView flagImageView;
        private final TextView nameTextView;
        private final TextView  populationTextView;
        private LinearLayout row_linearLayout;

        public LinearLayout getRow(){
            return row_linearLayout;
        }

        public CountriesViewHolder( View itemView) {
            super(itemView);
            context             = itemView.getContext();
            countryItem         = itemView.findViewById(R.id.country_row_frag);
            flagImageView       = itemView.findViewById(R.id.flagImageView);
            nameTextView        = itemView.findViewById(R.id.countryNameTextView);
            populationTextView  = itemView.findViewById(R.id.countryPopulationTextView);
            row_linearLayout        = itemView.findViewById(R.id.country_row_frag);
        }

        //******** This function bind\connect the row widgets with the data
        public void bindData(Country country) {
            //.getName(),country.getShorty(),country.getFlag()
            flagImageView.setImageResource(getDrawableId(context, country.getFlag()));
            nameTextView.setText(country.getName());
            populationTextView.setText(country.getShorty());
        }
    }
    /* @@@@@@@@@@@@@@@@@@@@@@@@@@@End_Of_Private_Class_@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

    public int getSelectedRow (){
        return selectedRow;
    }

    public CountriesViewHolder getViewHolder(){
        return viewHolder;
    }


    private static int getDrawableId(Context context, String drawableName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(drawableName, "drawable", context.getPackageName());
    }


    //-->lab7b:
    public interface ICountriesAdapterListener {
        void countryClicked();
    }






}
