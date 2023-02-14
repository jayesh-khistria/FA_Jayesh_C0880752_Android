package com.example.fa_jayesh_c0880752_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "productDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "myproduct";

    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String DESCRIPTION = "description";

    private static final String PRICE = "price";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "productDB"
                + ID + " (TEXT,"
                + NAME + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + PRICE + " TEXT)";

        db.execSQL(query);
    }

    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID, courseName);
        values.put(NAME, courseDuration);
        values.put(DESCRIPTION, courseDescription);
        values.put(PRICE, courseTracks);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
