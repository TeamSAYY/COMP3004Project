package com.example.drmednotifier;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.ArrayList;

public class Frag_Setting extends Fragment {


    ListView listView;

    public Frag_Setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag__setting, container, false);

        final ArrayList<String>  listitem = new ArrayList<String>();
        ListView listView = v.findViewById(R.id.listing);
        ArrayAdapter<String> listviewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitem);
        listView.setAdapter(listviewAdapter);
        listitem.add("a");
        listitem.add("b");
        listitem.add("c");
        listitem.add("d");
        listitem.add("e");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
/*
                String s =listitem.get(position);
                Nav_page mn1 = (Nav_page) getActivity();

                assert mn1 != null;
                mn1.f1(s);


*/


            }
        });


        return v;



    }



    public void replaceFragment(Fragment someFragment) {
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.Setting, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

