package com.example.lactato_udec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table userdata(name TEXT, date TEXT, age INTEGER, weight REAL, temperature REAL, period INTEGER, event INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists allusers");
        MyDB.execSQL("drop Table if exists userdata");
        onCreate(MyDB);
    }
    public Boolean insertUserData(String name, String date, int age, float weight, float temperature, int period, int event) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("date", date);
        contentValues.put("age", age);
        contentValues.put("weight", weight);
        contentValues.put("temperature", temperature);
        contentValues.put("period", period);
        contentValues.put("event", event);
        long result = MyDatabase.insert("userdata", null, contentValues);

        return result != -1;
    }

    public Boolean insertDate(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean CheckUser(String email){
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }
    public Boolean CheckUserPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }
}
