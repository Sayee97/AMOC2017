package com.example.android.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignOut extends AppCompatActivity {

    public TextView Name;
    public TextView email;
    public Button signOut;
    public ImageView pic;
//    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        signOut = (Button)findViewById(R.id.signOut);
        pic = (ImageView)findViewById(R.id.pic);
    }
}
