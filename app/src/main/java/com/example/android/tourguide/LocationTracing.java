package com.example.android.tourguide;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;






public class LocationTracing extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    protected GoogleApiClient googlAPI;

    private static final int REQUEST = 0;

    private LocationRequest mlocationRequest;
    private TextView lTextview;
    private Context ctx;
    public  String city;
    public TextView addr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tracing);
        googlAPI = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        lTextview=(TextView)findViewById(R.id.location1);
        addr1=(TextView)findViewById(R.id.resultCity);

    }


    @Override
    public void onLocationChanged(Location location) {
        Double lat1=location.getLatitude();
        Double long1=location.getLongitude();
        lTextview.setText("Latitude="+Double.toString(location.getLatitude())+"Longitude="+Double.toString(location.getLongitude()));
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> add = gcd.getFromLocation(lat1,long1,1);
            if(add.size()>0 && add!=null){
                addr1.setText(add.get(0).getLocality());

            }
            else {
                Toast.makeText(getApplicationContext(),"hjh",Toast.LENGTH_LONG);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mlocationRequest = LocationRequest.create();
        mlocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mlocationRequest.setInterval(20*60*1000);
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            Log.v("FAIL","JDBAHGDJHDJADKD");
            ActivityCompat.requestPermissions(this,new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            },REQUEST);



        }
        else{
            LocationServices.FusedLocationApi.requestLocationUpdates(googlAPI,mlocationRequest,this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        googlAPI.connect();
    }

    @Override
    protected void onStop() {
        googlAPI.disconnect();
        super.onStop();

    }
}


