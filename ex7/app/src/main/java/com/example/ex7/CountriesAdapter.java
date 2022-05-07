package com.example.ex7;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private final List<Country> countriesList;
    private boolean selectedRow = false;

    public CountriesAdapter(Context context) {
        this.countriesList = CountryXMLParser.parseCountries(context);
    }

    @NonNull
    @Override
    public CountriesAdapter.CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.CountriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
