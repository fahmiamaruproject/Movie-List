package com.fahmiamaru.movielist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    public DB_Helper(Context context) {
        super(context, "likemovie.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movie (id integer PRIMARY KEY AUTOINCREMENT, user text,title text," +
                "rilis text, overview text, imgurl text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movie;");
        onCreate(db);
    }

    public Boolean insertLiked(String user, String title, String rilis, String overview, String imgurl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("title", title);
        contentValues.put("rilis", rilis);
        contentValues.put("overview", overview);
        contentValues.put("imgurl", imgurl);
        long insert = db.insert("movie", null, contentValues);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkLiked(String user, String title){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movie WHERE user = ? AND title = ?;",
                new String[]{user, title});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean deleteLiked(String user, String title){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete ("movie", "user =? AND title =?", new String[]{String.valueOf(user),title}) > 0;
    }
}
