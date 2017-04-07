package com.example.android.tourguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tourguide.SignIn;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
//import static com.example.android.tourguide.R.id.pic;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.android.tourguide.R.id.pic11;

public class SignOut extends AppCompatActivity {

    public TextView Name1;
    public TextView email1;
    public Button signOut1;
    public ImageView pic1;
//    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
Bundle b = getIntent().getExtras();
        String name3= b.getString("name");
        String email3= b.getString("email");




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name1 = (TextView)findViewById(R.id.name11);
        email1 = (TextView)findViewById(R.id.email11);
        pic1 = (ImageView)findViewById(pic11);


        //Log.v("s","sayeejhsfidhidhfkdfhisdfhis");

       // Toast.makeText(getApplicationContext(),"ffff",Toast.LENGTH_LONG).show();
        email1.setText(email3);
      //  Bitmap r = Bitmap.createScaledBitmap()
       // Log.v("hh",b.getString("IMAGE"));
        Name1.setText(name3);
        Toast.makeText(getApplicationContext(),b.getString("image"),Toast.LENGTH_LONG).show();
      //  pic.setImageURI( Glide.with(this).load(m.imageUrl).into(m.));
        Glide.with(this).load( b.getString("image")).override(250,250).into(pic1);



    }


}
