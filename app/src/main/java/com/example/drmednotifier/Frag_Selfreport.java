package com.example.drmednotifier;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class Frag_Selfreport extends Fragment {


    TextView name,age;
    ImageView Avatarr;

    public Frag_Selfreport() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag__selfreport, container, false);

/*
        String NNN = ((Nav_page)getActivity()).getNamed();
        name = (TextView) v.findViewById(R.id.s_name) ;
        name.setText("Name: "+NNN);

        String AAA = ((Nav_page)getActivity()).getAge();
        age = (TextView) v.findViewById(R.id.s_age) ;
        age.setText("Age: "+AAA);


        Avatarr  = (ImageView)v.findViewById(R.id.Avatar);
        Avatarr.setImageResource(R.drawable.avatar);
*/

        return v;
    }



}