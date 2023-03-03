package com.example.simpledictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ExplainFragment extends Fragment {
    TextView wordTarget;
    TextView wordPronun;
    TextView wordExplain;
    ImageView btnSpeak;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explain, container, false);
        wordTarget = view.findViewById(R.id.word_target_ex);
        wordPronun = view.findViewById(R.id.word_pronun_ex);
        wordExplain = view.findViewById(R.id.word_explain_ex);

        btnSpeak = view.findViewById(R.id.btn_speak);

        Bundle bundle = getArguments();
        Word w = new Word();
        if (bundle != null) {
            w = (Word) bundle.get("word");
        }
        wordTarget.setText(w.getWord_target());
        wordPronun.setText(w.getWord_pronun());
        wordExplain.setText(w.getWord_explain().replace("=", " ").substring(1));

        TextToSpeech mTTS = new TextToSpeech(getActivity(), i -> {
            if (i != TextToSpeech.SUCCESS) {
                btnSpeak.setEnabled(false);
                Toast.makeText(getActivity(), "Speaker isn't supported", Toast.LENGTH_SHORT);
                return;
            }
        });

        btnSpeak.setOnClickListener(view1 -> {
            int result = mTTS.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) btnSpeak.setEnabled(false);
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT);
            mTTS.speak(wordTarget.getText(), TextToSpeech.QUEUE_FLUSH, null, "speaker2");
        });
        return view;
    }
}