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
        MyDB.execSQL("CREATE TABLE allusers (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT)");
        MyDB.execSQL("CREATE TABLE userdata (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, email TEXT, fecha TEXT, name TEXT, age INTEGER, weight INTEGER, temperature INTEGER, gender TEXT, period INTEGER, event INTEGER, FOREIGN KEY(user_id) REFERENCES allusers(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists allusers");
        MyDB.execSQL("drop Table if exists userdata");
        onCreate(MyDB);
    }

    public long insertUserData(int user_id, String fecha, String name, int age, int weight, int temperature, String gender, int period, int event) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("fecha", fecha);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("weight", weight);
        contentValues.put("temperature", temperature);
        contentValues.put("gender", gender);
        contentValues.put("period", period);
        contentValues.put("event", event);
        long rowId = MyDatabase.insert("userdata", null, contentValues);
        MyDatabase.close();
        return rowId;
    }


    public long insertDate(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result != -1) {
            // Return the ID of the new user
            Cursor cursor = MyDatabase.rawQuery("SELECT last_insert_rowid()", null);
            if (cursor.moveToFirst()) {
                long userId = cursor.getLong(0);
                cursor.close();
                MyDatabase.close();
                return userId;
            }
            cursor.close();
        }

        MyDatabase.close();
        return -1;
    }
    public int getUserIdByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        int userId = -1;

        Cursor cursor = MyDatabase.rawQuery("SELECT id FROM allusers WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        MyDatabase.close();

        return userId;
    }
    public Boolean CheckUser(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        MyDatabase.close();
        return exists;
    }

    public Boolean CheckUserPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        MyDatabase.close();
        return exists;
    }
}

