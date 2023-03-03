package com.example.simpledictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExplainFragment extends Fragment {
    TextView wordTarget;
    TextView wordPronun;
    TextView wordExplain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explain, container, false);
        wordTarget = (TextView) view.findViewById(R.id.word_target_ex);
        wordPronun = (TextView) view.findViewById(R.id.word_pronun_ex);
        wordExplain = (TextView) view.findViewById(R.id.word_explain_ex);
        Bundle bundle = getArguments();
        Word w = new Word();
        if (bundle != null) {
            w = (Word) bundle.get("word");
        }
        wordTarget.setText(w.getWord_target());
        wordPronun.setText(w.getWord_pronun());
        wordExplain.setText(w.getWord_explain().replace("=", " ").substring(1));
        return view;
    }
}