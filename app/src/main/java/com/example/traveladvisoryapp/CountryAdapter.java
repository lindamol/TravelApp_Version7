package com.example.traveladvisoryapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
       //interface created
        interface countryClickListner {
        void countryClicked(Country selectedCountry);
    }
    private Context context;
    public List<Country> countryList;
      public countryClickListner clistner; //to get the interface
    public CountryAdapter(Context context, List<Country> countryList) {
        this.context=context;
        this.countryList = countryList ;
        clistner = (countryClickListner)context;
    }
    @Override //Inflate the row for Recyclerview and return viewholder containing view for that row
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_row_item,parent,false);
        return new CountryViewHolder(view);
    }
    @Override //Binds value and row in the RecyclerView using the viewholder which holds the view of that row
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        Country c = countryList.get(position);//Country list already with data from constructor
       // holder.getCountryTextView().setText("Canada");
        holder.countryTextView.setText(c.getCountryName()); // To pass country name to the row
    }
    @Override
    public int getItemCount() {return countryList.size();}
    //inner class extends from inbuilt class which implenets the listener
   class  CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView countryTextView;
        public CountryViewHolder(View itemView) {
            super(itemView);
            countryTextView = itemView.findViewById(R.id.country_row);
        }
        public TextView getCountryTextView() {
            return countryTextView;
        }

               @Override
        public void onClick(View v) {
            Country country = countryList.get(getAdapterPosition());
            clistner.countryClicked(country);
        }

    }
 }
