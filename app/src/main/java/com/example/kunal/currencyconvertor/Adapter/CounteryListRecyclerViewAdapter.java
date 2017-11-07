package com.example.kunal.currencyconvertor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunal.currencyconvertor.Extras.CountryModel;
import com.example.kunal.currencyconvertor.R;

import java.util.ArrayList;

/**
 * Created by Kunal on 02-11-2017.
 */

public class CounteryListRecyclerViewAdapter extends RecyclerView.Adapter<CounteryListRecyclerViewAdapter.ViewHolder>{

    Activity context;
    int selectedItem=-1;
    ArrayList<CountryModel>conutry_list;
    public CounteryListRecyclerViewAdapter(Activity context, ArrayList<CountryModel>country_list)
    {
        this.context=context;
        this.conutry_list=country_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.countrylistrecyclerviewlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.countryname.setText(conutry_list.get(position).getName());
        holder.countrycode.setText(conutry_list.get(position).getCode());
        if(selectedItem==position)
            holder.selected.setVisibility(View.VISIBLE);
        else holder.selected.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return conutry_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryname;
        TextView countrycode;
        ImageView selected;

        public ViewHolder(final View itemView) {
            super(itemView);
            countryname=itemView.findViewById(R.id.country_name);
            countrycode=itemView.findViewById(R.id.country_code);
            selected=itemView.findViewById(R.id.selected);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem=getLayoutPosition();
                    notifyDataSetChanged();
                    Intent intent=new Intent();
                    intent.putExtra("firstname",conutry_list.get(getLayoutPosition()).getName());
                    intent.putExtra("firstcode",conutry_list.get(getLayoutPosition()).getCode());
                    intent.putExtra("secondname",conutry_list.get(getLayoutPosition()).getName());
                    intent.putExtra("secondcode",conutry_list.get(getLayoutPosition()).getCode());
                    context.setResult(100,intent);
                    context.finish();
                }
            });
        }
    }
}
