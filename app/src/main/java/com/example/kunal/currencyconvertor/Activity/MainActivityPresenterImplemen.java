package com.example.kunal.currencyconvertor.Activity;

import android.content.Context;
import android.util.Log;

import com.example.kunal.currencyconvertor.Interface.MainActivityModel;
import com.example.kunal.currencyconvertor.Interface.MainActivityPresenter;
import com.example.kunal.currencyconvertor.Interface.MainActivityView;

/**
 * Created by Kunal on 01-11-2017.
 */

public class MainActivityPresenterImplemen implements MainActivityPresenter,MainActivityModel.onNetworkListener,MainActivityModel.ExchangeRateListener {


    MainActivityView mainActivityView;
    MainActivityModel mainActivityModel;

    public MainActivityPresenterImplemen(Context context,MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        mainActivityModel=new MainActivityModelImplemen(context);
    }


    @Override
    public void updatingExchangeFromInternet() {
        mainActivityView.updatingExchangeFromInternet();
    }

    @Override
    public void updateCompleted() {
        mainActivityView.updateCompleted();
    }

    @Override
    public void updateError() {
        mainActivityView.updateError();
    }

    @Override
    public void noInternetConnection() {
        mainActivityView.noInternetConnection();
    }

    @Override
    public void internetConencted() {
        Log.d("Network","Checking");
        mainActivityView.internetConnected();
    }

    @Override
    public void oncheckInternetConnection() {
        mainActivityModel.onInternetConnectionCheck(this);
    }

    @Override
    public void onupdatingExchangeDataFromInternet() {
        mainActivityModel.onUpdatingExchangeData(this);
    }

    @Override
    public void onClaculateExchangeRateFromFirst(String from, String to, float factor) {
        mainActivityModel.onClaculateExchangeRateFromFirst(from,to,factor,this);
    }

    @Override
    public void onClaculateExchangeRateFromSecond(String from, String to, float factor) {
        mainActivityModel.onClaculateExchangeRateFromSecond(from,to,factor,this);
    }

    @Override
    public void firstRateChange(double rate) {
        mainActivityView.firstRateChange(rate);
    }

    @Override
    public void secondRateChange(double rate) {
        mainActivityView.secondRateChange(rate);
    }
}
