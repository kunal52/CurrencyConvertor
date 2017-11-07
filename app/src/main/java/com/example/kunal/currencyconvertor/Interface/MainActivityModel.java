package com.example.kunal.currencyconvertor.Interface;

/**
 * Created by Kunal on 01-11-2017.
 */

public interface MainActivityModel {

    interface onNetworkListener
    {
        void updatingExchangeFromInternet();
        void updateCompleted();
        void updateError();
        void noInternetConnection();
        void internetConencted();
    }

    interface ExchangeRateListener
    {
        void firstRateChange(double rate);
        void secondRateChange(double rate);
    }

    void onInternetConnectionCheck(onNetworkListener listener);
    void onUpdatingExchangeData(onNetworkListener listener);
    void onClaculateExchangeRateFromFirst(String from,String to,float factor,ExchangeRateListener exchangeRateListener);
    void onClaculateExchangeRateFromSecond(String from,String to,float factor,ExchangeRateListener exchangeRateListener);


}
