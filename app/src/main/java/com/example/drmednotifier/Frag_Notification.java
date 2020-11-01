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

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag__notification, container, false);

        // Get the spinner from the xml.
        Spinner dropdown_noti_type = view.findViewById(R.id.spinner_noti_type);
        // Create a list of items for the spinner.
        String[] items_noti_type = new String[]{"Push Notification", "Alert", "Popup Window"};
        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter_noti_type = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_noti_type);
        // Set the spinners adapter to the previously created one.
        dropdown_noti_type.setAdapter(adapter_noti_type);

        // Get the spinner from the xml.
        Spinner dropdown_noti_repeat = view.findViewById(R.id.spinner_noti_repeat);
        // Create a list of items for the spinner.
        String[] items_noti_repeat = new String[]{"None", "2 min", "5 min", "10 min", "30 min", "60 min"};
        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter_noti_repeat = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_noti_repeat);
        // Set the spinners adapter to the previously created one.
        dropdown_noti_repeat.setAdapter(adapter_noti_repeat);

        // Get the spinner from the xml.
        Spinner dropdown_renew_time = view.findViewById(R.id.spinner_renew_time);
        // Create a list of items for the spinner.
        String[] items_renew_time = new String[]{"1 day before", "2 days before", "7 days before", "14 days before", "31 days before"};
        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter_renew_time = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_renew_time);
        // Set the spinners adapter to the previously created one.
        dropdown_renew_time.setAdapter(adapter_renew_time);

        return view;
    }
}