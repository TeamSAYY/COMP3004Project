package com.example.drmednotifier;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


public class Frag_Notification extends Fragment {

    public Frag_Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag__notification, container, false);

        //get the spinner from the xml.
        Spinner dropdown_noti_type = view.findViewById(R.id.spinner_noti_type);
        //create a list of items for the spinner.
        String[] items_noti_type = new String[]{"Push Notification", "Alert", "Popup Window"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter_noti_type = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_noti_type);
        //set the spinners adapter to the previously created one.
        dropdown_noti_type.setAdapter(adapter_noti_type);

        //get the spinner from the xml.
        Spinner dropdown_noti_repeat = view.findViewById(R.id.spinner_noti_repeat);
        //create a list of items for the spinner.
        String[] items_noti_repeat = new String[]{"None", "2 min", "5 min", "10 min", "30 min"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter_noti_repeat = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_noti_repeat);
        //set the spinners adapter to the previously created one.
        dropdown_noti_repeat.setAdapter(adapter_noti_repeat);

        //get the spinner from the xml.
        Spinner dropdown_renew_time = view.findViewById(R.id.spinner_renew_time);
        //create a list of items for the spinner.
        String[] items_renew_time = new String[]{"1 day before", "2 days before", "3 days before", "4 days before", "5 days before"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter_renew_time = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_renew_time);
        //set the spinners adapter to the previously created one.
        dropdown_renew_time.setAdapter(adapter_renew_time);

        // Inflate the layout for this fragment
        return view;
    }
}