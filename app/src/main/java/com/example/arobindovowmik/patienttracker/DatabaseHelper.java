package com.example.arobindovowmik.patienttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    //public static final String geo = "ID";
    public static final String Col_1 = "PATIENT_NAME";
    public static final String Col_2 = "TELIPHONE_NAME";
    public static final String Col_3 = "EMAIL";
    public static final String Col_4 = "DATE_AT_ARRIVAL";
    public static final String Col_5 = "DISEASE";
    public static final String Col_7 = "COST";
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (PATIENT_NAME TEXT,TELIPHONE_NAME TEXT,EMAIL TEXT,DATE_AT_ARRIVAL TEXT,DISEASE TEXT,COST INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String PN,String TN,String EM,String DAA,String D,String C)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1,PN);
        contentValues.put(Col_2,TN);
        contentValues.put(Col_3,EM);
        contentValues.put(Col_4,DAA);
        contentValues.put(Col_5,D);
        contentValues.put(Col_7,C);
        long re = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(re==-1)
            return false;
        else
            return true;
    }
    public Cursor getdata()
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}