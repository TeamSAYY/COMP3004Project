package com.example.drmednotifier;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class Frag_Add extends Fragment {



    public Frag_Add() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag__add, container, false);

        Spinner dropdown_freq = view.findViewById(R.id.spinner_med_freq);
        String[] items_freq = new String[]{"Every Day", "Specific Day"};
        ArrayAdapter<String> adapter_freq = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_freq);
        dropdown_freq.setAdapter(adapter_freq);

        Spinner dropdown_timesaday = view.findViewById(R.id.spinner_med_timesaday);
        String[] items_timesaday = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> adapter_timesaday = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_timesaday);
        dropdown_timesaday.setAdapter(adapter_timesaday);

        // Inflate the layout for this fragment
        return view;
    }
}