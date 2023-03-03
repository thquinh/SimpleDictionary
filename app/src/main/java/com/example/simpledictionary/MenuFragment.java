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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View menu = inflater.inflate(R.layout.fragment_menu, container, false);

        transPara = (Button) menu.findViewById(R.id.btn_transpara);
        transPara.setOnClickListener(view -> {
            Log.i("start act", "translatre para");
            getActivity().startActivity(new Intent(getActivity(), TranslateParaActivity.class));
        });

        return menu;
    }
}