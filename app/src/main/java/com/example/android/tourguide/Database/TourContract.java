package com.example.android.tourguide.Database;

import android.provider.BaseColumns;

/**
 * Created by Owner on 4/4/2017.
 */

public class TourContract {

    public static abstract class tourEntry implements BaseColumns {
        public static final String TABLE_NAME = "trip";
        public static final String DATABASE_NAME = "tour";
        public static final String _ID = BaseColumns._ID;
        public static final String DESTINATION = "name";
        public static final String CHECKIN_DATE = "inDate";
        public static final String CHECKOUT_DATE = "outDate";
        public static final String CHECKIN_TIME = "inTime";
        public static final String CHECKOUT_TIME = "outTime";
        public static final String ADDRESS = "address";
    }
}
