package com.example.android.tourguide;


import com.example.android.tourguide.Database.TourDbHelper;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.tourguide.Database.TourContract.tourEntry;


public class Itenary extends AppCompatActivity {


    private String dest,add,inD,inT,outD,outT;
    tourAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenary);
//        destination = (TextView) findViewById(R.id.destination);
//        address = (TextView) findViewById(R.id.address);
//        inDate = (TextView) findViewById(R.id.inDate);
//        outDate = (TextView) findViewById(R.id.outDate);
//        inTime = (TextView) findViewById(R.id.inTime);
//        outTime = (TextView) findViewById(R.id.outTime);
        TourDbHelper tdb = new TourDbHelper(this);

        listView = (ListView) findViewById(R.id.listView);
        Cursor cr = tdb.select(tdb);
        adapter = new tourAdapter(this,R.layout.list_item);
//        destination.setText("");
//        address.setText("");
//        inTime.setText("");
//        outTime.setText("");
//        inDate.setText("");
//        outDate.setText("");
        cr.moveToFirst();
        do {
//            destination.append(cr.getString(0));
//            address.append(cr.getString(1));
//            inDate.append(cr.getString(2));
//            outDate.append(cr.getString(3));
//            inTime.append(cr.getString(4));
//            outTime.append(cr.getString(5));
            dest = cr.getString(cr.getColumnIndex(tourEntry.DESTINATION));
            add = cr.getString(cr.getColumnIndex(tourEntry.ADDRESS));
            inD = cr.getString(cr.getColumnIndex(tourEntry.CHECKIN_DATE));
            inT = cr.getString(cr.getColumnIndex(tourEntry.CHECKIN_TIME));
            outD = cr.getString(cr.getColumnIndex(tourEntry.CHECKOUT_DATE));
            outT = cr.getString(cr.getColumnIndex(tourEntry.CHECKOUT_TIME));
            info Info = new info(dest,add,inD,outD,inT,outT);
            adapter.add(Info);
           // listView.setAdapter(adapter);
        }while (cr.moveToNext());
        cr.close();

        listView.setAdapter(adapter);
    }

}
