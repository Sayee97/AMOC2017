package com.example.android.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.text.Text;

import static android.os.Build.VERSION_CODES.M;
import static com.example.android.tourguide.SignIn.REQ_CODE;

public class Place_Picker extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private TextView name ;
    private TextView address1,phoneNo;
    private final int REQ_CODE = 1 ;
    private TextView website;
    private RatingBar rate;

    String address,name13;
    TextView txt;
    GoogleApiClient mGoogleApiClient;
    ImageView mImageView,imageView;
    Button trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPlacePickerActivity();
        setContentView(R.layout.activity_place__picker);
        address1 = (TextView) findViewById(R.id.place);
        rate = (RatingBar) findViewById(R.id.ratingBar);
        //type = (TextView) findViewById(R.id.type);
        website = (TextView) findViewById(R.id.website);
        name = (TextView) findViewById(R.id.name12);
        phoneNo = (TextView) findViewById(R.id.phone);
        txt = (TextView)findViewById(R.id.attri);
        mImageView = (ImageView)findViewById(R.id.image3);
        trip = (Button) findViewById(R.id.trip);
        imageView = (ImageView) findViewById(R.id.roadmap);



        mGoogleApiClient  = new GoogleApiClient.Builder(this).enableAutoManage(this,0,this).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API).build();

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
            imageView.setVisibility(View.GONE);
            address = place.getAddress().toString();
            name13 = place.getName().toString();

            name.setText(name13);
            address1.setText(address);
            if (place.getPhoneNumber() != null){
                phoneNo.setText(place.getPhoneNumber());
            }
            else{
                phoneNo.setText("Contact details not available for searched attribute");
            }

            if (place.getRating() >= 0){
                rate.setRating(place.getRating());
                rate.setEnabled(false);
            }
            else{
                rate.setVisibility(View.GONE);
            }

            if (place.getWebsiteUri() != null){

                Uri url = place.getWebsiteUri();

                website.setText(url.toString());
            }
            else{
                website.setVisibility(View.GONE);
            }

            trip.setVisibility(View.VISIBLE);

//            switch (place.getPriceLevel())
//            {
//                case 0 : priceLevel.setText("Cheapest");
//                    break;
//                case 1 :priceLevel.setText("Cheaper");
//                    break;
//                case 2 :priceLevel.setText("Moderate");
//                    break;
//                case 3 :priceLevel.setText("Expensive");
//                    break;
//                case 4 :priceLevel.setText("Most Expensive");
//                    break;
//
//
//     }


            final String placeId = place.getId();
            new PhotoTask(mImageView.getWidth(),mImageView.getHeight()) {

//                @Override
//                protected void onPreExecute() {
//                    super.onPreExecute();
//                }

                @Override
                protected void onPostExecute(AttributedPhoto attributedPhoto) {
                    if (attributedPhoto!=null){
                        mImageView.setImageBitmap(attributedPhoto.bitmap);
                        if (attributedPhoto.attribution == null) {
                            txt.setVisibility(View.GONE);
                        } else {
                            txt.setVisibility(View.VISIBLE);
                            txt.setText(Html.fromHtml(attributedPhoto.attribution.toString()));
                        }
                    }
//                    super.onPostExecute(attributedPhoto);
                }



            }.execute(placeId);
        }


    }

    public void addToTrip(View view){
        Intent i = new Intent(this,CreateTrip.class);
        i.putExtra("name",name13);
        i.putExtra("address",address);
        startActivity(i);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    abstract class PhotoTask extends AsyncTask<String, Void, PhotoTask.AttributedPhoto> {

        private int mHeight;

        private int mWidth;

        public PhotoTask(int width, int height) {
            mHeight = height;
            mWidth = width;
        }


        @Override
        protected AttributedPhoto doInBackground(String... params) {
            if (params.length != 1) {
                return null;
            }
            final String placeId = params[0];
            AttributedPhoto attributedPhoto = null;

            PlacePhotoMetadataResult result = Places.GeoDataApi
                    .getPlacePhotos(mGoogleApiClient, placeId).await();

            if (result.getStatus().isSuccess()) {
                PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();
                if (photoMetadataBuffer.getCount() > 0 && !isCancelled()) {
                    PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                    CharSequence attribution = photo.getAttributions();
                    Bitmap image = photo.getScaledPhoto(mGoogleApiClient, mWidth, mHeight).await()
                            .getBitmap();

                    attributedPhoto = new AttributedPhoto(attribution, image);
                }
                photoMetadataBuffer.release();
            }
            return attributedPhoto;
        }

        class AttributedPhoto {

            public final CharSequence attribution;

            public final Bitmap bitmap;

            public AttributedPhoto(CharSequence attribution, Bitmap bitmap) {
                this.attribution = attribution;
                this.bitmap = bitmap;
            }
        }
    }



}
