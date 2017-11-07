package com.example.kunal.currencyconvertor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kunal.currencyconvertor.Adapter.CounteryListRecyclerViewAdapter;
import com.example.kunal.currencyconvertor.Extras.CountryModel;
import com.example.kunal.currencyconvertor.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryLists extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    CounteryListRecyclerViewAdapter counteryListRecyclerViewAdapter;
    ArrayList<CountryModel>countryModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_lists);
        recyclerView=findViewById(R.id.country_list_recyclerview);

        countryModels=new ArrayList<>();
        List<String>countryCode= Arrays.asList(getResources().getStringArray(R.array.country_name));
        List<String>countryname= Arrays.asList(getResources().getStringArray(R.array.country_code));
        for(int i=0;i<countryname.size();i++)
        {
            countryModels.add(new CountryModel(countryname.get(i).toUpperCase(),countryCode.get(i).toUpperCase()));
        }


        counteryListRecyclerViewAdapter=new CounteryListRecyclerViewAdapter(this,countryModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(counteryListRecyclerViewAdapter);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
}
