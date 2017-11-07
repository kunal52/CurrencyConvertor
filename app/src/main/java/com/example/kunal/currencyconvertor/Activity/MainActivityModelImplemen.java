package com.example.kunal.currencyconvertor.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kunal.currencyconvertor.Database.ExchangesDatabase;
import com.example.kunal.currencyconvertor.Gson.JsonToGsonObject;
import com.example.kunal.currencyconvertor.Interface.MainActivityModel;
import com.example.kunal.currencyconvertor.R;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kunal on 01-11-2017.
 */

public class MainActivityModelImplemen implements MainActivityModel {





    private Context context;
    private ExchangesDatabase exchangesDatabase;
    RequestQueue requestQueue;
    int total;
    List<String> arrayList;
    Gson gson;

    public MainActivityModelImplemen(Context context) {
        this.context = context;
        this.exchangesDatabase=new ExchangesDatabase(context);
        requestQueue=Volley.newRequestQueue(context);
        arrayList= Arrays.asList(context.getResources().getStringArray(R.array.country_code));
        total=30;
        gson=new Gson();
    }

    @Override
    public void onInternetConnectionCheck(onNetworkListener listener) {

        if(isNetworkAvailable())
            listener.internetConencted();
        else listener.noInternetConnection();

    }

    @Override
    public void onUpdatingExchangeData(final onNetworkListener listener) {

        final int[] count = {0};

        listener.updatingExchangeFromInternet();
        for(int i=0;i<arrayList.size();i++) {
            String requestUrl = "http://api.fixer.io/latest?base=" + arrayList.get(i);
            StringRequest request = new StringRequest(Request.Method.GET, requestUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    insertIntoDatabase(gson.fromJson(response,JsonToGsonObject.class));
                    count[0] = count[0] +1;
                    if(count[0]==31)
                        listener.updateCompleted();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.updateError();
                    count[0]=count[0]+1;
                    if(count[0]==31)
                        listener.updateError();
                }
            });
            requestQueue.add(request);
        }
    }

    @Override
    public void onClaculateExchangeRateFromFirst(String from, String to, float factor, ExchangeRateListener exchangeRateListener) {
       // exchangeRateListener.firstRateChange(exchangesDatabase.getExchange(from,to)*factor);
        exchangeRateListener.firstRateChange(65.5*factor);
    }

    @Override
    public void onClaculateExchangeRateFromSecond(String from, String to, float factor, ExchangeRateListener exchangeRateListener) {
        exchangeRateListener.secondRateChange(exchangesDatabase.getExchange(from,to)*factor);
    }

    private boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void insertIntoDatabase(JsonToGsonObject jsonToGsonObject)
    {
        exchangesDatabase.insert(jsonToGsonObject);
    }


}
