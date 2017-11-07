package com.example.kunal.currencyconvertor.Gson;

/**
 * Created by Kunal on 03-11-2017.
 */

public class JsonToGsonObject {

    private String base;
    private String date;
    private Rates rates;

    public JsonToGsonObject(String base, String date, Rates rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }
}
