package com.example.android.tourguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static com.example.android.tourguide.R.id.signIn;

public class SignIn extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{


    ViewPager viewPager;
    pagerAdapter adapter;

    //public Context cntxt;
    private SignInButton signIn;
    private GoogleApiClient googleApiClient;
    public static final int REQ_CODE = 9001;
  //  SignOut m ;
    public String name,email;
    public String imageUrl;

//    Async a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

       // a= new Async();


        viewPager = (ViewPager)findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabDot);
        adapter = new pagerAdapter(this);
        tabLayout.setupWithViewPager(viewPager,true);
        viewPager.setAdapter(adapter);
      //  m=new SignOut();
        //Log.v("SignIn","1223654");

        signIn = (SignInButton)findViewById(R.id.signIn);
        signIn.setOnClickListener(this);


      //  m.signOut.setOnClickListener(this);

        //Log.v("SignIn","SECXONH");
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient  = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();



        //Log.v("SignIn","THIRD");


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private class Async extends AsyncTask<GoogleApiClient,Void,GoogleSignInResult> {


        @Override
        protected GoogleSignInResult doInBackground(GoogleApiClient... params) {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,REQ_CODE);
            return null;
        }

        @Override
        protected void onPostExecute(GoogleSignInResult googleSignInResult) {
            super.onPostExecute(googleSignInResult);
        }
    }

    @Override
    public void onClick(View v) {

        Async async = new Async();
        async.execute(googleApiClient);
    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }

//    public void signIn(){
//        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//        startActivityForResult(intent,REQ_CODE);
//    }
//   public void signOut11(){
//       Toast.makeText(this,"mani",Toast.LENGTH_LONG).show();
//      Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
//        @Override
//          public void onResult(@NonNull Status status) {
//               updateUI(false);
//            }
//        });
//    }
    public void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
             name = account.getDisplayName();
            email = account.getEmail();
             imageUrl = account.getPhotoUrl().toString();
            updateUI(true);
        }
        else {
            updateUI(false);
        }
    }
    public void updateUI(boolean isLogin){
        if (isLogin){
            Intent i = new Intent(this,SignOut.class);
            i.putExtra("EMAIL",email);
            i.putExtra("IMAGE",imageUrl);
            i.putExtra("NAME",name);
            startActivity(i);

        }
        else {
            Toast.makeText(this,"Invalid Mail id",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            handleResult(result);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
