package com.example.simpledictionary;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {
    Button transPara;
    Button settings;
    Button favWords;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View menu = inflater.inflate(R.layout.fragment_menu, container, false);

        transPara = (Button) menu.findViewById(R.id.btn_transpara);
        transPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("start act", "translatre para");
                ((MainActivity) getActivity()).startActivity(new Intent(getActivity(), TranslateParaActivity.class));
            }
        });
        settings = (Button) menu.findViewById(R.id.btn_setting);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).startActivity(new Intent(getActivity(), TranslateParaActivity.class));
            }
        });
        favWords = (Button) menu.findViewById(R.id.btn_fav);
        favWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).startActivity(new Intent(getActivity(), TranslateParaActivity.class));
            }
        });
        return menu;
    }
}