package com.example.simpledictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "evdict";
    private SQLiteDatabase dbObj;
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context,  DB_NAME , null, 3);
        this.context  = context;
        this.DB_PATH = "data/data/" + context.getPackageName() + "/databases/";
    }

    public void createDB() throws IOException {
        this.getReadableDatabase();
        try {
            copyDB();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    private boolean checkDB(){
        SQLiteDatabase checkDB = null;
        try{
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if (checkDB!=null)
            {
                Cursor c= checkDB.rawQuery("SELECT * FROM evdict", null);
                c.moveToFirst();
                String contents[]=new String[80];
                int flag=0;

                while(! c.isAfterLast())
                {
                    String temp = "Word[" +
                            "word_target='" + c.getString(0) + '\'' +
                            ", word_pronun='" + c.getString(1) + '\'' +
                            ", word_explain='" + c.getString(2) + '\'' +
                            ']';

                    Log.i("DB values.........",temp);
                    c.moveToNext();

                }
            }
            else
            {
                return false;
            }

        }catch(SQLiteException e){
            e.printStackTrace();
        }

        if(checkDB != null){

            checkDB.close();

        }
        return checkDB != null ? true : false;
    }

    public void copyDB() throws IOException{
        try {
            Log.i("inside copyDB....................","start");

            InputStream ip =  context.getAssets().open(DB_NAME+".db");
            Log.i("Input Stream....",ip+"");
            String op=  DB_PATH  +  DB_NAME ;
            OutputStream output = new FileOutputStream( op);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = ip.read(buffer))>0){
                output.write(buffer, 0, length);
                Log.i("Content.... ",length+"");
            }
            output.flush();
            output.close();
            ip.close();
        }
        catch (IOException e) {
            Log.v("error", e.toString());
        }
    }

    public void openDB() throws SQLException {

        String myPath = DB_PATH + DB_NAME;
        dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.i("open DB......",dbObj.toString());
    }

    @Override
    public synchronized void close() {

        if(dbObj != null)
            dbObj.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
