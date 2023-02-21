package com.example.simpledictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView wordList;
    SearchView searchWord;
    WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frag_layout, new MenuFragment());
        fragmentTransaction.commit();

        getWordFromDB();

        wordList = (ListView) findViewById(R.id.list_word);
        wordAdapter = new WordAdapter(getApplicationContext(), new ArrayList<>());
        wordList.setAdapter(wordAdapter);
        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToExplain((Word) wordAdapter.getItem(i));
                searchWord.setQuery("", false);
                searchWord.clearFocus();
            }
        });

        searchWord = (SearchView) findViewById(R.id.search_word);
        searchWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s.trim().toLowerCase(Locale.ROOT);
                wordAdapter.filterSearch(text);
                wordList.smoothScrollToPosition(0);
                return false;
            }
        });
    }

    private void getWordFromDB() {
        DatabaseHelper db;
        db = new DatabaseHelper(this);

        try {

            db.createDB();
        } catch (IOException ioe) {

            throw new Error("Database not created....");
        }

        try {
            db.openDB();

        }catch(SQLException sqle){

            throw sqle;
        }

        SQLiteDatabase db1;
        db1=openOrCreateDatabase("evdict", Context.MODE_PRIVATE,null);
        Cursor c= db1.rawQuery("SELECT * FROM evdict",null);

        c.moveToFirst();

        while(! c.isAfterLast())
        {
            Word w = new Word();
            w.setWord_target(c.getString(0));
            w.setWord_pronun(c.getString(1));
            w.setWord_explain(c.getString(2));
            Dictionary.words.add(w);
            c.moveToNext();
        }
    }

    public void goToExplain(Word w) {
        ExplainFragment ex = new ExplainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("word", w);
        ex.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_layout, ex);
        fragmentTransaction.commit();
    }
}