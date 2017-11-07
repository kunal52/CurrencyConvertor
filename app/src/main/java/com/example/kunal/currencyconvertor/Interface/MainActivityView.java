package com.example.kunal.currencyconvertor.Interface;

/**
 * Created by Kunal on 01-11-2017.
 */

public interface MainActivityView {

    void updatingExchangeFromInternet();
    void updateCompleted();
    void updateError();
    void noInternetConnection();
    void internetConnected();
    void firstRateChange(double rate);
    void secondRateChange(double rate);
}
