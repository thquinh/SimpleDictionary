package com.example.simpledictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Word w = new Word();
        w.setWord_target("aback");
        w.setWord_pronun("/ə\'bæk/");
        w.setWord_explain("* phó từ\n- lùi lại, trở lại phía sau\nto stand aback from: đứng lùi lại để tránh\n- (hàng hải) bị thổi ép vào cột buồm (buồm)\nto be taken aback: (hàng hải) bị gió thổi ép vào cột buồm\n- (nghĩa bóng) sửng sốt, ngạc nhiên\nto be taken aback by the news: sửng sốt vì cái tin đó\n");
        Dictionary.wordsAdvanced.add(w);
        w.setWord_target("above");
        w.setWord_pronun("/ə\'bæk/");
        w.setWord_explain("* phó từ\n- lùi lại, trở lại phía sau\nto stand aback from: đứng lùi lại để tránh\n- (hàng hải) bị thổi ép vào cột buồm (buồm)\nto be taken aback: (hàng hải) bị gió thổi ép vào cột buồm\n- (nghĩa bóng) sửng sốt, ngạc nhiên\nto be taken aback by the news: sửng sốt vì cái tin đó\n");
        Dictionary.wordsAdvanced.add(w);
        w.setWord_target("abandon");
        w.setWord_pronun("/ə\'bæk/");
        w.setWord_explain("* phó từ\n- lùi lại, trở lại phía sau\nto stand aback from: đứng lùi lại để tránh\n- (hàng hải) bị thổi ép vào cột buồm (buồm)\nto be taken aback: (hàng hải) bị gió thổi ép vào cột buồm\n- (nghĩa bóng) sửng sốt, ngạc nhiên\nto be taken aback by the news: sửng sốt vì cái tin đó\n");
        Dictionary.wordsAdvanced.add(w);
        w.setWord_target("abase");
        w.setWord_pronun("/ə\'bæk/");
        w.setWord_explain("* phó từ\n- lùi lại, trở lại phía sau\n"
                + "to stand aback from: đứng lùi lại để tránh\n-"
                + "(hàng hải) bị thổi ép vào cột buồm (buồm)\n"
                + "to be taken aback: (hàng hải) bị gió thổi ép vào cột buồm\n-"
                + "(nghĩa bóng) sửng sốt, ngạc nhiên\nto be taken aback by the news: sửng sốt vì cái tin đó\n");
        Dictionary.wordsAdvanced.add(w);
//        DatabaseHelper db;
//        db = new DatabaseHelper(this);
//
//        try {
//
//            db.createDB();
//        } catch (IOException ioe) {
//
//            throw new Error("Database not created....");
//        }
//
//        try {
//            db.openDB();
//
//        }catch(SQLException sqle){
//
//            throw sqle;
//        }
//
//        SQLiteDatabase db1;
//        db1=openOrCreateDatabase("Dictionary", Context.MODE_PRIVATE,null);
//        Cursor c= db1.rawQuery("SELECT * FROM advance",null);
//
//        c.moveToFirst();
//
//        String temp="";
//        while(! c.isAfterLast())
//        {
//            String s2=c.getString(0);
//            String s3=c.getString(1);
//            String s4=c.getString(2);
//            temp=temp+"\n Id:"+s2+"\tType:"+s3+"\tBal:"+s4;
//
//            c.moveToNext();
//        }
//        txt.setText(temp);
    }
}