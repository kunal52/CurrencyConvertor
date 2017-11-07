package com.example.kunal.currencyconvertor.Activity;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.kunal.currencyconvertor.Database.ExchangesDatabase;
import com.example.kunal.currencyconvertor.Interface.MainActivityPresenter;
import com.example.kunal.currencyconvertor.Interface.MainActivityView;
import com.example.kunal.currencyconvertor.R;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainActivityView,View.OnClickListener {

    MainActivityPresenter mainActivityPresenter;
    TextView firstcountry;
    TextView secoundcountry;
    TextView firstcode;
    TextView secoundcode;
    EditText first;
    EditText secound;
    String selectedfirstname;
    String selectedsecondname;
    String selectedfirstcode;
    String selectedsecondcode;
    CircleImageView changeUpDown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        selectedfirstcode="USD";
        selectedsecondcode="INR";
        selectedfirstname="US DOLLAR";
        selectedsecondname="INDIAN RUPEE";

        if (savedInstanceState != null) {
            selectedfirstcode=savedInstanceState.getString("firstcode","usd");
            selectedfirstname=savedInstanceState.getString("firstcode","US Dollar");
            selectedsecondcode=savedInstanceState.getString("secondcode","inr");
            selectedsecondname=savedInstanceState.getString("secondname","Indian Rupee");
            firstcountry.setText(selectedfirstname);
            firstcode.setText(selectedfirstcode);
            secoundcountry.setText(selectedsecondname);
            secoundcode.setText(selectedsecondcode);
           // first.setText(String.valueOf(savedInstanceState.getFloat("firstvalue")));
           // secound.setText(String.valueOf(savedInstanceState.getFloat("secondvalue")));
        }




        changeUpDown=findViewById(R.id.changePosition);
        firstcountry=findViewById(R.id.first_countryname);
        secoundcountry=findViewById(R.id.second_countryname);
        firstcode=findViewById(R.id.first_countrycode);
        secoundcode=findViewById(R.id.second_countrycode);
        first=findViewById(R.id.first_input);
        secound=findViewById(R.id.second_input);

        changeUpDown.setOnClickListener(this);
        firstcode.setOnClickListener(this);
        firstcountry.setOnClickListener(this);
        secoundcode.setOnClickListener(this);
        secoundcountry.setOnClickListener(this);
        first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //mainActivityPresenter.onClaculateExchangeRateFromFirst(selectedfirstcode,selectedsecondcode, Float.parseFloat(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        secound.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()!=0)
                mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, Float.parseFloat(charSequence.toString()));
                else mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mainActivityPresenter=new MainActivityPresenterImplemen(this,this);
        mainActivityPresenter.oncheckInternetConnection();
    }

    @Override
    public void updatingExchangeFromInternet() {
        Log.d("Exchange","Updating");
    }

    @Override
    public void updateCompleted() {
        Log.d("Exchange","Updating Completed");
    }

    @Override
    public void updateError() {
        Log.d("Exchange","Updating Error");
    }

    @Override
    public void noInternetConnection() {
        Log.d("Network","No");
        Toast.makeText(this,"No InternetConnection",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void internetConnected() {
        Log.d("Network","Conected");
        Toast.makeText(this,"Internet Connected",Toast.LENGTH_SHORT).show();
        mainActivityPresenter.onupdatingExchangeDataFromInternet();
    }

    @Override
    public void firstRateChange(double rate) {
        secound.setText(String.valueOf(rate));
    }

    @Override
    public void secondRateChange(double rate) {
        first.setText(String.valueOf(rate));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==firstcountry.getId()||view.getId()==firstcode.getId())
        {
            startActivityForResult(new Intent(this,CountryLists.class),11);
        }
        if(view.getId()==secoundcountry.getId()||view.getId()==secoundcode.getId())
        {
            startActivityForResult(new Intent(this,CountryLists.class),12);
        }
        if(view.getId()==changeUpDown.getId())
        {
            String code1temp=selectedfirstcode;
            String name1temp=selectedfirstname;
            selectedfirstcode=selectedsecondcode;
            selectedfirstname=selectedsecondname;
            selectedsecondcode=code1temp;
            selectedsecondname=name1temp;
            if(secound.getText().toString().length()!=0)
                mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, Float.parseFloat(secound.getText().toString()));
            else mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, 0);
            updateUI();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
       if(requestCode==11)
       {
           selectedfirstname=data.getStringExtra("firstname");
           selectedfirstcode=data.getStringExtra("firstcode");
           firstcountry.setText(selectedfirstname);
           firstcode.setText(selectedfirstcode);
           if(secound.getText().toString().length()!=0)
               mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, Float.parseFloat(secound.getText().toString()));
           else mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, 0);
       }
       if(requestCode==12)
       {
           selectedsecondname=data.getStringExtra("secondname");
           selectedsecondcode=data.getStringExtra("secondcode");
           secoundcountry.setText(selectedsecondname);
           secoundcode.setText(selectedsecondcode);
           if(secound.getText().toString().length()!=0)
               mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, Float.parseFloat(secound.getText().toString()));
           else mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, 0);
       }
        }catch (Exception e){}
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.d("saveSATER","STATe");
        outState.putString("firstcode",selectedfirstcode);
        outState.putString("secondcode",selectedsecondcode);
        outState.putString("firstname",selectedfirstname);
        outState.putString("secondname",selectedsecondname);
    //    outState.putFloat("firstvalue", Float.parseFloat(first.getText().toString()));
    //    outState.putFloat("secondvalue", Float.parseFloat(secound.getText().toString()));
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void updateUI()
    {
        if(secound.getText().toString().length()!=0)
            mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, Float.parseFloat(secound.getText().toString()));
        else mainActivityPresenter.onClaculateExchangeRateFromSecond(selectedsecondcode,selectedfirstcode, 0);
        firstcountry.setText(selectedfirstname);
        firstcode.setText(selectedfirstcode);
        secoundcountry.setText(selectedsecondname);
        secoundcode.setText(selectedsecondcode);
    }


}
