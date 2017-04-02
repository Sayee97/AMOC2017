package com.example.android.tourguide;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class CreateTrip extends AppCompatActivity {

    private TextView checkInDate , checkOutDate,name,address;
    int year,month,day;
    private Calendar calendar;
    private static final int ID=999,REQ=1000;
    int year1,day1,month1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_create_trip);
        checkInDate = (TextView) findViewById(R.id.CheckInDate);
        checkInDate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        checkOutDate = (TextView) findViewById(R.id.CheckOutDate);
        checkOutDate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        name = (TextView) findViewById(R.id.nameOfPlace);
        name.setText(b.getString("name"));
        address = (TextView) findViewById(R.id.placeAddress);
        address.setText(b.getString("address"));
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year1 = calendar.get(Calendar.YEAR);
        month1 = calendar.get(Calendar.MONTH);
        day1 = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        showDate1(year1,month1+1,day1);
    }

    private void showDate1(int year1, int month1, int day1) {
        Toast.makeText(getApplicationContext(),"bbbbbbbbbbbbbbbbb",Toast.LENGTH_LONG);
        checkOutDate.setText(new StringBuilder().append(day1).append("/")
                .append(month1).append("/").append(year1));
    }

    public void setCheckInDate(View view){
        showDialog(ID);
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        if (id == REQ){
            Toast.makeText(getApplicationContext(),"cccccccccccccccc",Toast.LENGTH_LONG);
            return new DatePickerDialog(this,myDateListener1,year,month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                    showDate(year,month + 1,dayOfMonth);
                }


            };

    private DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year1, int month1, int dayOfMonth1) {
                    showDate1(year1,month1 + 1,dayOfMonth1);
                }


            };


    private void showDate(int year, int month, int day) {
        checkInDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void setCheckOutDate(View view){
        showDialog(REQ);
        Toast.makeText(getApplicationContext(),"aaaaaaaaaaaaaaa",Toast.LENGTH_LONG);
    }


}
