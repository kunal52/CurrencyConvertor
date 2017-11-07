package com.example.kunal.currencyconvertor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kunal.currencyconvertor.Gson.JsonToGsonObject;

import static com.example.kunal.currencyconvertor.Extras.Constants.DATABASE_NAME;

/**
 * Created by Kunal on 01-11-2017.
 */

public class ExchangesDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME="exchange";
    public static final String TABLE="create table exchange (\n" +
            "base varchar(3) primary key,\n" +
            "tarik date,\n" +
            "aud float,\n" +
            "bgn float,\n" +
            "brl float,\n" +
            "cad float,\n" +
            "chf float,\n" +
            "cny float,\n" +
            "czk float,\n" +
            "dkk float,\n" +
            "eur floaf,\n" +
            "gbp float,\n" +
            "hkd float,\n" +
            "hrk float,\n" +
            "huf float,\n" +
            "idr float,\n" +
            "ils float,\n" +
            "inr float,\n" +
            "jpy float,\n" +
            "krw float,\n" +
            "mxn float,\n" +
            "myr float,\n" +
            "nok float,\n" +
            "nzd float,\n" +
            "php float,\n" +
            "pln float,\n" +
            "ron float,\n" +
            "rub float,\n" +
            "sek float,\n" +
            "sgd float,\n" +
            "thb float,\n" +
            "try float,\n" +
            "usd float,\n" +
            "zar float\n" +
            ");";
    public ExchangesDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void insert(JsonToGsonObject object)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("base",object.getBase());
       // contentValues.put("date",object.getDate());
        contentValues.put("aud",object.getRates().getAUD());
        contentValues.put("bgn",object.getRates().getBGN());
        contentValues.put("brl",object.getRates().getBRL());
        contentValues.put("cad",object.getRates().getCAD());
        contentValues.put("chf",object.getRates().getCHF());
        contentValues.put("cny",object.getRates().getCNY());
        contentValues.put("czk",object.getRates().getCZK());
        contentValues.put("dkk",object.getRates().getDKK());
        contentValues.put("eur",object.getRates().getEUR());
        contentValues.put("gbp",object.getRates().getGBP());
        contentValues.put("hrk",object.getRates().getHRK());
        contentValues.put("huf",object.getRates().getHUF());
        contentValues.put("idr",object.getRates().getIDR());
        contentValues.put("ils",object.getRates().getILS());
        contentValues.put("inr",object.getRates().getINR());
        contentValues.put("jpy",object.getRates().getJPY());
        contentValues.put("krw",object.getRates().getKRW());
        contentValues.put("mxn",object.getRates().getMXN());
        contentValues.put("myr",object.getRates().getMYR());
        contentValues.put("nok",object.getRates().getNOK());
        contentValues.put("nzd",object.getRates().getNZD());
        contentValues.put("php",object.getRates().getPHP());
        contentValues.put("pln",object.getRates().getPLN());
        contentValues.put("ron",object.getRates().getRON());
        contentValues.put("rub",object.getRates().getRUB());
        contentValues.put("sek",object.getRates().getSEK());
        contentValues.put("sgd",object.getRates().getSGD());
        contentValues.put("thb",object.getRates().getTHB());
        contentValues.put("usd",object.getRates().getUSD());
        contentValues.put("zar",object.getRates().getZAR());
        try {
            sqLiteDatabase.insertOrThrow(TABLE_NAME,null,contentValues);
        }catch (Exception e){

          //  contentValues.put("base",object.getBase());
            // contentValues.put("date",object.getDate());
            contentValues.put("aud",object.getRates().getAUD());
            contentValues.put("bgn",object.getRates().getBGN());
            contentValues.put("brl",object.getRates().getBRL());
            contentValues.put("cad",object.getRates().getCAD());
            contentValues.put("chf",object.getRates().getCHF());
            contentValues.put("cny",object.getRates().getCNY());
            contentValues.put("czk",object.getRates().getCZK());
            contentValues.put("dkk",object.getRates().getDKK());
            contentValues.put("eur",object.getRates().getEUR());
            contentValues.put("gbp",object.getRates().getGBP());
            contentValues.put("hrk",object.getRates().getHRK());
            contentValues.put("huf",object.getRates().getHUF());
            contentValues.put("idr",object.getRates().getIDR());
            contentValues.put("ils",object.getRates().getILS());
            contentValues.put("inr",object.getRates().getINR());
            contentValues.put("jpy",object.getRates().getJPY());
            contentValues.put("krw",object.getRates().getKRW());
            contentValues.put("mxn",object.getRates().getMXN());
            contentValues.put("myr",object.getRates().getMYR());
            contentValues.put("nok",object.getRates().getNOK());
            contentValues.put("nzd",object.getRates().getNZD());
            contentValues.put("php",object.getRates().getPHP());
            contentValues.put("pln",object.getRates().getPLN());
            contentValues.put("ron",object.getRates().getRON());
            contentValues.put("rub",object.getRates().getRUB());
            contentValues.put("sek",object.getRates().getSEK());
            contentValues.put("sgd",object.getRates().getSGD());
            contentValues.put("thb",object.getRates().getTHB());
            contentValues.put("usd",object.getRates().getUSD());
            contentValues.put("zar",object.getRates().getZAR());

            sqLiteDatabase.update(TABLE_NAME,contentValues,"base=?",new String[]{object.getBase()});
        }

    }

    public float getExchange(String from,String to)
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,new String[]{to.toUpperCase()},"base=?",new String[]{from.toUpperCase()},null,null,null);
        cursor.moveToNext();
        return cursor.getFloat(0);
    }

}
