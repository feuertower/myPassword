package com.example.mypassword;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "passwords.db";
    // Contacts table name
    private static final String TABLE_PASSWORDS = "passwordList";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DISPLAY_NAME = "displayName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_INFO = "info";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PASSWORD_TABLE = "CREATE TABLE " + TABLE_PASSWORDS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DISPLAY_NAME + "TEXT, " + KEY_USERNAME + " TEXT,"
        + KEY_PASSWORD + " TEXT, " + KEY_INFO + "TEXT" + ")";
        db.execSQL(CREATE_PASSWORD_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORDS);
// Creating tables again
        onCreate(db);
    }

    public void addPasswordEntry(PasswordEntry entry)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, entry.getUsername());
        values.put(KEY_PASSWORD, entry.getPassword());
        values.put(KEY_INFO, entry.getInfoText());
        values.put(KEY_DISPLAY_NAME, entry.getDisplayName());

        db.insert(TABLE_PASSWORDS, null, values);
        db.close();
    }

    public List<PasswordEntry> getPasswordList()
    {
        List<PasswordEntry> passwordList = new ArrayList<PasswordEntry>();
        String selectQuery = "SELECT * FROM " + TABLE_PASSWORDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( selectQuery, null );

        if( cursor.moveToFirst() )
        {
            do {
                PasswordEntry entry = new PasswordEntry();
                entry.setId(Integer.parseInt( cursor.getString( 0 )));
                entry.setDisplayName( cursor.getString( 1 ));
                entry.setUsername( cursor.getString( 2 ));
                entry.setPassword( cursor.getString( 3 ));
                entry.setInfoText( cursor.getString( 4 ));

                passwordList.add( entry );
            }while(cursor.moveToNext());
        }

        return passwordList;
    }

    public int updatePasswordEntry( PasswordEntry entry)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DISPLAY_NAME, entry.getDisplayName());
        values.put(KEY_USERNAME, entry.getUsername());
        values.put(KEY_PASSWORD, entry.getPassword());
        values.put(KEY_INFO, entry.getInfoText());

        return db.update(TABLE_PASSWORDS, values, KEY_ID + "= ?",
                new String[]{String.valueOf( entry.getId() )});
    }

    public void removeEntry( PasswordEntry entry )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PASSWORDS, KEY_ID + "= ?",
                new String[]{String.valueOf( entry.getId() )});
        db.close();
    }
}
