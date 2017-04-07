package com.example.android.tourguide;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.tourguide.Database.TourDbHelper;

public class FrontEnd extends AppCompatActivity {
     String name3,email3,imageU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_end);

        Bundle b = getIntent().getExtras();
        name3= b.getString("NAME");
        email3= b.getString("EMAIL");
        imageU = b.getString("IMAGE");

    }

    public void myTrip(View view){
        TourDbHelper tourDbHelper= new TourDbHelper(this);
        Cursor c = tourDbHelper.select(tourDbHelper);
        if (c.getCount() ==0){
            Toast.makeText(getApplicationContext(),"Please start planning your trip ",Toast.LENGTH_LONG).show();
        }
        else {

            Intent i = new Intent(this,Itenary.class);
            startActivity(i);
        }
    }

    public void PlaceSelect(View view){
        Intent i = new Intent(this,Place_Picker.class);
        startActivity(i);
    }

    public void signOut12(View view){
        Intent i = new Intent(this,SignIn.class);
        int req = 100;
        i.putExtra("request",req);
        startActivity(i);
    }
    public void pro(View view){
        Intent i = new Intent(this,SignOut.class);
        i.putExtra("name",name3);
        i.putExtra("email",email3);
        i.putExtra("image",imageU);
        startActivity(i);
    }

    public void pdfView(View view){
        TourDbHelper tourDbHelper= new TourDbHelper(this);
        Cursor c = tourDbHelper.select(tourDbHelper);
        if (c.getCount() ==0){
            Toast.makeText(getApplicationContext(),"Please start planning your trip",Toast.LENGTH_LONG).show();
        }
        else {

            Intent i = new Intent(this,PdfCreate.class);
            startActivity(i);
        }
    }

    public void deleteMyTrip(View view){
        TourDbHelper tourDbHelper = new TourDbHelper(this);
        tourDbHelper.deleteTrip(tourDbHelper);
    }
}
