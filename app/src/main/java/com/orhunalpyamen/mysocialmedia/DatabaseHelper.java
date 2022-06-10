package com.orhunalpyamen.mysocialmedia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    String Mymail="alp@gmail.com";

    public static final String DATABASE_NAME = "my_notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOTE_CREATE =
            "CREATE TABLE " + TablesInfo.NoteEntry.TABLE_NAME + " (" +
                    TablesInfo.NoteEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.NoteEntry.COLUMN_MAIL + " TEXT, " +
                    TablesInfo.NoteEntry.COLUMN_NOTE + " TEXT" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_NOTE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TablesInfo.NoteEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void addNote(String s, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.NoteEntry.COLUMN_MAIL, s.trim());
        cv.put(TablesInfo.NoteEntry.COLUMN_NOTE, note.trim());

        long result = db.insert(TablesInfo.NoteEntry.TABLE_NAME, null, cv);

        if (result > -1)
            Log.i("DatabaseHelper", "Not başarıyla kaydedildi");
        else
            Log.i("DatabaseHelper", "Not kaydedilemedi");

        db.close();
    }

    public void deleteNote(String noteID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TablesInfo.NoteEntry.TABLE_NAME, TablesInfo.NoteEntry.COLUMN_ID + "=?", new String[]{noteID});
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Notes> getNoteList(String mail) {
        ArrayList<Notes> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.NoteEntry.COLUMN_ID,
                TablesInfo.NoteEntry.COLUMN_MAIL,
                TablesInfo.NoteEntry.COLUMN_NOTE,

        };

        //BİRİNE DEĞER VER
        String[] columns = {
                TablesInfo.NoteEntry.COLUMN_ID
        };


        // selection criteria
        String selection = TablesInfo.NoteEntry.COLUMN_MAIL + " = ?";

        // selection argument
        //String[] selectionArgs = {"alp@gmail.com"};
        //Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, projection, null, null, null, null, null);

        Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, null, selection, new String[] {mail}, null, null, null);

        while (c.moveToNext()) {
            data.add(new Notes(c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_ID)), c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_MAIL)),
                    c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_NOTE))));
        }

        c.close();
        db.close();

        return data;
    }

    public void duzenle(String mail,String note,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(TablesInfo.NoteEntry.COLUMN_MAIL, mail);
        values.put(TablesInfo.NoteEntry.COLUMN_NOTE, note);

        // updating row
        db.update(TablesInfo.NoteEntry.TABLE_NAME, values, TablesInfo.NoteEntry.COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }



    @SuppressLint("Range")
    public ArrayList<Notes> getNoteListSearch(String text) {
        ArrayList<Notes> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.NoteEntry.COLUMN_ID,
                TablesInfo.NoteEntry.COLUMN_MAIL,
                TablesInfo.NoteEntry.COLUMN_NOTE,

        };

        //BİRİNE DEĞER VER
        String[] columns = {
                TablesInfo.NoteEntry.COLUMN_ID
        };


        // selection criteria
        String selection = TablesInfo.NoteEntry.COLUMN_NOTE + " = ?";

        // selection argument
        //String[] selectionArgs = {"alp@gmail.com"};
        //Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, projection, null, null, null, null, null);

        Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, null, selection, new String[] {text}, null, null, null);

        while (c.moveToNext()) {
            data.add(new Notes(c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_ID)), c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_MAIL)),
                    c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_NOTE))));
        }

        c.close();
        db.close();

        return data;
    }

    @SuppressLint("Range")
    public ArrayList<Notes> getNoteListAll() {
        ArrayList<Notes> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.NoteEntry.COLUMN_ID,
                TablesInfo.NoteEntry.COLUMN_MAIL,
                TablesInfo.NoteEntry.COLUMN_NOTE,

        };


        // selection argument
        //String[] selectionArgs = {"alp@gmail.com"};
        //Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, projection, null, null, null, null, null);

        Cursor c = db.query(TablesInfo.NoteEntry.TABLE_NAME, projection,null, null, null, null, null, null);

        while (c.moveToNext()) {
            data.add(new Notes(c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_ID)), c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_MAIL)),
                    c.getString(c.getColumnIndex(TablesInfo.NoteEntry.COLUMN_NOTE))));
        }

        c.close();
        db.close();

        return data;
    }







}
