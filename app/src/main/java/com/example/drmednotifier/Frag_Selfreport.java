package com.example.drmednotifier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class Frag_Selfreport extends Fragment {


//    private String[] ListElements = { "Fabian", "Carlos", "Alex", "Andrea", "Karla"};


    public Frag_Selfreport() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View v = inflater.inflate(R.layout.fragment_frag__selfreport, container, false);

        /*ListView listView  = (ListView)v.findViewById(R.id.self_report_list_1);

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ListElements



        );


        listView.setAdapter(ListViewAdapter);*/





        return v;
    }



}