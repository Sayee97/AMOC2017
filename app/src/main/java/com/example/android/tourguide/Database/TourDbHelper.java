package com.example.android.tourguide.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.tourguide.Database.TourContract.tourEntry;

import static android.R.attr.version;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Owner on 4/4/2017.
 */

public class TourDbHelper extends SQLiteOpenHelper{
    public String CREATE_TABLE = "CREATE TABLE " + tourEntry.TABLE_NAME + " (" + tourEntry.DESTINATION +" TEXT,"
            +tourEntry.ADDRESS + " TEXT,"
            +tourEntry.CHECKIN_DATE + " TEXT," +tourEntry.CHECKOUT_DATE + " TEXT," + tourEntry.CHECKIN_TIME + " TEXT," +
            tourEntry.CHECKOUT_TIME + " TEXT);";

    public static final int DATABASE_VERSION = 9;

    public TourDbHelper(Context context) {
        super(context, tourEntry.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(TourDbHelper tdb,String name,String address, String checkInDate,String checkOutDate,String checkInTime,String checkOutTime){

        SQLiteDatabase db = tdb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tourEntry.DESTINATION,name);
        cv.put(tourEntry.ADDRESS,address);
        cv.put(tourEntry.CHECKIN_DATE,checkInDate);
        cv.put(tourEntry.CHECKOUT_DATE,checkOutDate);
        cv.put(tourEntry.CHECKIN_TIME,checkInTime);
        cv.put(tourEntry.CHECKOUT_TIME,checkOutTime);
        db.insert(tourEntry.TABLE_NAME,null,cv);
    }
    public Cursor select(TourDbHelper tdb){
        SQLiteDatabase db = tdb.getReadableDatabase();
        String[] projection = {tourEntry.DESTINATION,tourEntry.ADDRESS,tourEntry.CHECKIN_DATE,tourEntry.CHECKOUT_DATE,tourEntry.CHECKIN_TIME,tourEntry.CHECKOUT_TIME};
        Cursor c=  db.query(tourEntry.TABLE_NAME,projection,null,null,null,null,null);
        return c;
    }


    public void delete(TourDbHelper tdb,String name){
        SQLiteDatabase db = tdb.getWritableDatabase();
        String selection = tourEntry.DESTINATION + "= ?" ;
        String[] args = {name};
        db.delete(tourEntry.TABLE_NAME,selection,args);
    }
    public void update(TourDbHelper tdb, String oName , String nName){

        SQLiteDatabase db = tdb.getWritableDatabase();
        String selection = tourEntry.DESTINATION + " = ?";
        String[] args = {oName};
        ContentValues v = new ContentValues();
        v.put("NAME",nName);
        db.update(tourEntry.TABLE_NAME,v,selection,args);
    }

}
