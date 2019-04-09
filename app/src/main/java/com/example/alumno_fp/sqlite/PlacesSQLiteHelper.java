package com.example.alumno_fp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlacesSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Places(id VARCHAR(100),name TEXT,country TEXT)";

    public PlacesSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int prevVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Places");
        db.execSQL(sqlCreate);
    }
}
