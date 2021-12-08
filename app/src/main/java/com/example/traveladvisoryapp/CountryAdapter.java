package com.example.traveladvisoryapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {

    //interface created
        interface countryClickListner {
        void countryClicked(Country selectedCountry);
    }
    private Context context;
    public List<Country> countryList;
    private List<Country> copycountryList;
      public countryClickListner clistner; //to get the interface
    public CountryAdapter(Context context, List<Country> countryList) {
        this.context=context;
        this.countryList = countryList ;
        this.copycountryList = new ArrayList<>(countryList);//Created copy of Arraylist
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
            countryTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Country country = countryList.get(getAdapterPosition());
                    clistner.countryClicked(country);
                }
            });
        }
        public TextView getCountryTextView() {
            return countryTextView;
        }

               @Override
        public void onClick(View v) {
//            Country country = countryList.get(getAdapterPosition());
//            clistner.countryClicked(country);
        }

    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override//execute in background thread

        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Country> filteredList = new ArrayList<>();//contains Filtered Item
            copycountryList = new ArrayList<>(countryList);
            if(constraint.length() == 0){
                filteredList.addAll(copycountryList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
               // System.out.println("my list new copyyyyy"+ copycountryList);
                for(Country item: copycountryList){ //can use startwith
                    if((item.getCountryName()).toLowerCase().startsWith(filterPattern)){
                        filteredList.add(item);

                  }
              }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            System.out.println("My filterd results is  + "+ results.values.toString()+"");
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countryList.clear();
            countryList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

}
