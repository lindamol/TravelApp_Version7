package com.example.traveladvisoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private Context context;
    public List<Country> countryList ;

    //interface created
    interface favcountryClickListner {
        void favcountryClicked(Country favselectedCountry);
        void codeforFavCountry(String favcountryCode);
    }
    public FavouriteAdapter.favcountryClickListner favlistener;
    public FavouriteAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
        favlistener = (favcountryClickListner)context;
    }

    @Override
    public FavouriteAdapter.FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_row, parent, false);
        return new FavouriteViewHolder(view);
        }

        @Override
        public void onBindViewHolder (@NonNull FavouriteAdapter.FavouriteViewHolder holder,int position){
            Country c = countryList.get(position);
            holder.getFavTextview().setText(c.getCountryName());
        }

        @Override
        public int getItemCount () {
            return countryList.size();
        }
        class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView favTextview;

            public FavouriteViewHolder(View itemView) {
                super(itemView);
                favTextview = itemView.findViewById(R.id.favcountrytexview);
                favTextview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Country country = countryList.get(getAdapterPosition());
                        favlistener.favcountryClicked(country);
                        favlistener.codeforFavCountry(country.getCountryCode());
                    }
                });
            }

            public TextView getFavTextview() {
                return favTextview;
            }


            @Override
            public void onClick(View v) {

            }
        }

}