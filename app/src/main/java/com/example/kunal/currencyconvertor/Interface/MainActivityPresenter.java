package com.example.kunal.currencyconvertor.Interface;

/**
 * Created by Kunal on 01-11-2017.
 */

public interface MainActivityPresenter {

    void oncheckInternetConnection();
    void onupdatingExchangeDataFromInternet();
    void onClaculateExchangeRateFromFirst(String from,String to,float factor);
    void onClaculateExchangeRateFromSecond(String from,String to,float factor);

}
