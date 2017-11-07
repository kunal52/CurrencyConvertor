package com.example.kunal.currencyconvertor.Extras;

/**
 * Created by Kunal on 02-11-2017.
 */

public class CountryModel {
    String name;
    String code;


    public CountryModel(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }


    public String getCode() {
        return code;
    }

}
