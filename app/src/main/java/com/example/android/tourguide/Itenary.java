package com.example.android.tourguide;


import com.example.android.tourguide.Database.TourDbHelper;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Itenary extends AppCompatActivity {

    private TextView destination,address,inDate,outDate,inTime,outTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenary);
        destination = (TextView) findViewById(R.id.destination);
        address = (TextView) findViewById(R.id.address);
        inDate = (TextView) findViewById(R.id.inDate);
        outDate = (TextView) findViewById(R.id.outDate);
        inTime = (TextView) findViewById(R.id.inTime);
        outTime = (TextView) findViewById(R.id.outTime);
        TourDbHelper tdb = new TourDbHelper(this);

        Cursor cr = tdb.select(tdb);
        destination.setText("");
        address.setText("");
        inTime.setText("");
        outTime.setText("");
        inDate.setText("");
        outDate.setText("");
        cr.moveToFirst();
        do {
            destination.append(cr.getString(0));
            address.append(cr.getString(1));
            inDate.append(cr.getString(2));
            outDate.append(cr.getString(3));
            inTime.append(cr.getString(4));
            outTime.append(cr.getString(5));
        }while (cr.moveToNext());
        cr.close();

    }

}
