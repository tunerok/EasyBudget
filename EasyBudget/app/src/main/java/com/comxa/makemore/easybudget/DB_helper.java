package com.comxa.makemore.easybudget;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Artyom on 25.07.2016.
 */
public class DB_helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Easy_b.db";

    public static final String TABLE_NAME = "users";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "pass";

    public static final String BUDGET_NAME = "first";
    public static final String B_NAME = "";
    public static final String B_COMMENT = "";
    public static final Double B_MONEY = 0.0; //
    public static final int B_TYPE = 0; //

    public static final int START_DATE = 0;
    public static final int END_DATE = 0;
    //start date
    //final date

    public static final int B_PERCENT = 0; //0-nothing or..
    public static final int B_PERIOD = 0; // 0-onetime,1-one_year etc



    public DB_helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DB_helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    DB_helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " +
                LOGIN + " text NOT NULL, " +
                 PASSWORD + " text NOT NULL" + ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
