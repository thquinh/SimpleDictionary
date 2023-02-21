package com.example.simpledictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class WordAdapter extends BaseAdapter {
    Context context;
    private ArrayList<Word> words;

    public WordAdapter(Context context, ArrayList<Word> words) {
        this.context = context;
        this.words = words;
    }
    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int i) {
        return words.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.word_list_item, null);
        TextView wt = (TextView) view.findViewById(R.id.word_target);
        wt.setText(words.get(i).getWord_target());
        TextView wp = (TextView) view.findViewById(R.id.word_pronun);
        wp.setText(words.get(i).getWord_pronun());
        TextView we = (TextView) view.findViewById(R.id.word_explain);
        String[] temp = words.get(i).getWord_explain().split("\n");
        we.setText(temp[1]);
        return view;
    }

    public void filterSearch(String inputWord) {
        if (inputWord.length() == 0) {
            words.clear();
        } else {
            ArrayList<Word> result = new ArrayList<>();
            for (int i = 0; i < Dictionary.words.size(); i++) {
                String strTarget = Dictionary.words.get(i).getWord_target().toLowerCase(Locale.ROOT);
                if (strTarget.length() >= inputWord.length()) {
                    if (inputWord.equals(strTarget.substring(0, inputWord.length()))) {
                        Word w = new Word();
                        w.setWord_target(strTarget);
                        w.setWord_pronun(Dictionary.words.get(i).getWord_pronun());
                        w.setWord_explain(Dictionary.words.get(i).getWord_explain());
                        result.add(w);
                    }
                }
            }
            words = result;
        }
        notifyDataSetChanged();
    }
}
