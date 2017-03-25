package com.example.android.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.vision.barcode.Barcode;

import static android.os.Build.VERSION_CODES.M;
import static com.example.android.tourguide.R.id.imageView;
import static com.example.android.tourguide.SignIn.REQ_CODE;

public class Place_Picker extends AppCompatActivity {

    private TextView txt,name;
    private final int REQ_CODE = 1 ;
    private ImageView imageView1;

    private RatingBar rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place__picker);
        txt = (TextView)findViewById(R.id.place);
        rate = (RatingBar) findViewById(R.id.ratingBar);

        name = (TextView) findViewById(R.id.name12);
        imageView1 = (ImageView) findViewById(R.id.imagedart);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //           new MyT
                startPlacePickerActivity();
            }
        });
    }




    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        try {

            Intent intent = intentBuilder.build(Place_Picker.this);

            Place_Picker.this.startActivityForResult(intent,REQ_CODE);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE && resultCode== Activity.RESULT_OK){
            Place place = PlacePicker.getPlace(this,data);
            String address = place.getAddress().toString();
            String name13 = place.getName().toString();
            imageView1.setVisibility(View.GONE);
            txt.setText(address);
            rate.setRating(place.getRating());
            name.setText(name13);
        }


    }

}
