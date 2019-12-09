package com.remijonathan.finalexampreperationmobileapplicationdevelopment;

import android.provider.BaseColumns;

/**
 * Use this in order to manage the Tables of the database
 */
public class ProjectContract {

    //Create the constructor in order to call the instances of the class
    private ProjectContract(){}

    //this implementation provides base ID columns for the table
    public static final class HolidaysEntry implements BaseColumns{
        public static final String TABLE_NAME = "holiday";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
    }
}
