package com.example.android.tourguide;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.tourguide.Database.TourDbHelper;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTrip extends AppCompatActivity {

    private TextView checkInDate , checkOutDate,name,address,checkInTime,checkOutTime;
    int year,month,day;
    private Calendar calendar;
    private static final int ID=999,REQ=1000,ID_1=1001,REQ_1=1002;
    int year1,day1,month1;
    int hour,minute,hour1,minute1;
    long oldMilli,oldTime;

    private String destination,addressOfplace;
    private StringBuilder inTime,outTime,inDate,outDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_create_trip);
        checkInDate = (TextView) findViewById(R.id.CheckInDate);
        checkInDate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        checkOutDate = (TextView) findViewById(R.id.CheckOutDate);
        checkOutDate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        checkInTime = (TextView) findViewById(R.id.CheckInTime);
        checkInTime.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        checkOutTime = (TextView) findViewById(R.id.CheckOutTime);
        checkOutTime.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        name = (TextView) findViewById(R.id.nameOfPlace);
        name.setText(b.getString("name"));
        destination = b.getString("name");
        addressOfplace = b.getString("address");
        address = (TextView) findViewById(R.id.placeAddress);
        address.setText(b.getString("address"));
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year1 = calendar.get(Calendar.YEAR);
        month1 = calendar.get(Calendar.MONTH);
        day1 = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        hour1 = calendar.get(Calendar.HOUR_OF_DAY);
        minute1 = calendar.get(Calendar.MINUTE);
        showDate(year, month+1, day);
        showDate1(year1,month1+1,day1);
        showTime(hour,minute);
        showTime1(hour1,minute1);
    }

    private void showDate1(int year1, int month1, int day1) {
        if (month1 < 10){
            if (day1 < 10){
                outDate = new StringBuilder().append(year1).append("/0")
                        .append(month1).append("/0").append(day1);
            }
            else{
                outDate = new StringBuilder().append(year1).append("/0")
                        .append(month1).append("/").append(day1);
            }
        }
        else {
            if (day1 < 10){
                outDate = new StringBuilder().append(year1).append("/")
                        .append(month1).append("/0").append(day1);
            }
            else{
                outDate = new StringBuilder().append(year1).append("/")
                        .append(month1).append("/").append(day1);
            }
        }
        checkOutDate.setText(outDate);
    }

    public void setCheckInDate(View view){
        showDialog(ID);
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,myDateListener, year, month, day);

        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,myDateListener1,year,month,day);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,myTimeListener,hour,minute,true);

        TimePickerDialog timePickerDialog1 = new TimePickerDialog(this,myTimeListener1,hour,minute,true);

        if (id == 999) {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return datePickerDialog;
        }
        if (id == REQ){
            datePickerDialog1.getDatePicker().setMinDate(oldMilli);
            return datePickerDialog1;
        }
        if (id == ID_1){
            return timePickerDialog;
        }
        if (id == REQ_1){
            return timePickerDialog1;
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
        if (month < 10){
            if (day < 10){
                inDate = new StringBuilder().append(year).append("/0")
                        .append(month).append("/0").append(day);
            }
            else{
                inDate = new StringBuilder().append(year).append("/0")
                        .append(month).append("/").append(day);
            }
        }
        else {
            if (day < 10){
                inDate = new StringBuilder().append(year).append("/")
                        .append(month).append("/0").append(day);
            }
            else{
                inDate = new StringBuilder().append(year).append("/")
                        .append(month).append("/").append(day);
            }
        }
        String myDate = inDate.toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(myDate);
            oldMilli = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkInDate.setText(inDate);
    }

    public void setCheckOutDate(View view){
        showDialog(REQ);
    }


    public void setCheckInTime(View view){
        showDialog(ID_1);
    }

    public void setCheckOutTime(View view){
        showDialog(REQ_1);
    }




    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            showTime(hourOfDay,minute);
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeListener1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            showTime1(hourOfDay,minute);
        }
    };

    public void showTime(int hour,int minute){
//        String format;
//        if (hour == 00) {
//            hour += 12;
//            format = "AM";
//        } else if (hour == 12) {
//            format = "PM";
//        } else if (hour > 12) {
//            hour -= 12;
//            format = "PM";
//        } else {
//            format = "AM";
//        }
        if (hour < 10){
            if(minute < 10) {
                inTime = new StringBuilder().append(0).append(hour).append(":").append(0)
                        .append(minute);
            }
            else {
                inTime = new StringBuilder().append(0).append(hour).append(":")
                        .append(minute);
            }
        }
        else {
            if(minute < 10) {
                inTime = new StringBuilder().append(hour).append(":").append(0)
                        .append(minute);
            }
            else {
                inTime = new StringBuilder().append(hour).append(":")
                        .append(minute);
            }
        }
        checkInTime.setText(inTime);

//        String InTime = inTime.toString();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
//        Date date = new Date();
//        try {
//            date = simpleDateFormat.parse(InTime);
//            oldTime = date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    public void showTime1(int hour,int minute){
//        String format;
//        if (hour == 00) {
//            hour += 12;
//            format = "AM";
//        } else if (hour == 12) {
//            format = "PM";
//        } else if (hour > 12) {
//            hour -= 12;
//            format = "PM";
//        } else {
//            format = "AM";
//        }

        if (hour < 10){
            if(minute < 10) {
                outTime = new StringBuilder().append(0).append(hour).append(":").append(0)
                        .append(minute);
            }
            else {
                outTime = new StringBuilder().append(0).append(hour).append(":")
                        .append(minute);
            }
        }
        else {
            if(minute < 10) {
                outTime = new StringBuilder().append(hour).append(":").append(0)
                        .append(minute);
            }
            else {
                outTime = new StringBuilder().append(hour).append(":")
                        .append(minute);
            }
        }
        checkOutTime.setText(outTime);
    }

    public void save(View view){
        TourDbHelper tdb = new TourDbHelper(this);
        tdb.insert(tdb,destination,addressOfplace,inDate.toString(),outDate.toString(),inTime.toString(),outTime.toString());
        Intent intent = new Intent(this,Itenary.class);
        startActivity(intent);

    }
}
