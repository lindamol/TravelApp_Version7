package com.example.traveladvisoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.viewHolder> {
    private Context context;
    public ArrayList<String> countryInfoList;

    public InfoAdapter(Context context, ArrayList<String> countryInfo) {
        this.context = context;
        this.countryInfoList = countryInfo;
    }
    //Inner static class
    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView infoTextview;
        //viewholder, ie 1 row
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            infoTextview = itemView.findViewById(R.id.info_row_textview);
                   }
        public TextView getInfoTextview() { //getters for elements in innerclass
            return infoTextview;
        }

    }
    @NonNull
    @Override
    public InfoAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_row,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        if(countryInfoList.get(0).isEmpty())
//        { holder.getInfoTextview().setText("No Information Available."+"\n" + " Please follow regular Guidelines");
//           // holder.getInfoTextview().setText("No Information Available."+"\n" + " Please follow regular Guidelines");
//        }
//        else{
        holder.getInfoTextview().setText(countryInfoList.get(position));
    //}
        //holder.getQuantity().setText(mylist.get(position).getQuantity()+"");
    }

   @Override
    public int getItemCount() {
        return countryInfoList.size();
    }
}
