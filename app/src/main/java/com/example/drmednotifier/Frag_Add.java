package com.example.drmednotifier;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.drmednotifier.createmedication.CreateMedicationViewModel;
import com.example.drmednotifier.data.Medication;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Frag_Add extends Fragment {
    @BindView(R.id.edit_med_name) EditText name;
    @BindView(R.id.edit_med_desc) EditText description;
    @BindView(R.id.edit_med_stock) EditText quantity;
    @BindView(R.id.radio_group_1) RadioGroup shape_radioBtnGroup;
    @BindView(R.id.edit_med_dose_1) EditText dose_1;
    @BindView(R.id.edit_med_dose_2) EditText dose_2;
    @BindView(R.id.edit_med_dose_3) EditText dose_3;
    @BindView(R.id.edit_med_dose_4) EditText dose_4;
    @BindView(R.id.button_hide_med_info) Button button_hide_med_info;
    @BindView(R.id.llayout_medinfo) View med_info_view;
    @BindView(R.id.button_hide_schedule) Button button_hide_schedule;
    @BindView(R.id.llayout_schedule) View med_schedule_view;
    @BindView(R.id.spinner_med_freq) Spinner dropdown_freq;
    @BindView(R.id.spinner_med_timesaday) Spinner dropdown_timesaday;
    @BindView(R.id.text_time_dose_1) TextView text_time_dose_1;
    @BindView(R.id.llayout_time_dose_1) View time_dose_view_1;
    @BindView(R.id.timePicker_med_time_1) TimePicker timePicker_1;
    @BindView(R.id.text_time_dose_2) TextView text_time_dose_2;
    @BindView(R.id.llayout_time_dose_2) View time_dose_view_2;
    @BindView(R.id.timePicker_med_time_2) TimePicker timePicker_2;
    @BindView(R.id.text_time_dose_3) TextView text_time_dose_3;
    @BindView(R.id.llayout_time_dose_3) View time_dose_view_3;
    @BindView(R.id.timePicker_med_time_3) TimePicker timePicker_3;
    @BindView(R.id.text_time_dose_4) TextView text_time_dose_4;
    @BindView(R.id.llayout_time_dose_4) View time_dose_view_4;
    @BindView(R.id.timePicker_med_time_4) TimePicker timePicker_4;


    private CreateMedicationViewModel createMedicationViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createMedicationViewModel = ViewModelProviders.of(this).get(CreateMedicationViewModel.class);
    }

    public Frag_Add() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag__add, container, false);

        ButterKnife.bind(this, view);

        // Button hide med info
        button_hide_med_info.setTag(1);
        button_hide_med_info.setText("hide");

        button_hide_med_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    button_hide_med_info.setText("show");
                    med_info_view.setVisibility(View.GONE);
                    view.setTag(0);
                } else {
                    button_hide_med_info.setText("hide");
                    med_info_view.setVisibility(View.VISIBLE);
                    view.setTag(1);
                }
            }
        });

        // Button hide med schedule
        button_hide_schedule.setTag(1);
        button_hide_schedule.setText("hide");

        button_hide_schedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    button_hide_schedule.setText("show");
                    med_schedule_view.setVisibility(View.GONE);
                    view.setTag(0);
                } else {
                    button_hide_schedule.setText("hide");
                    med_schedule_view.setVisibility(View.VISIBLE);
                    view.setTag(1);
                }
            }
        });

        // Drop down list #1
        String[] items_freq = new String[]{"Every Day", "Specific Day"};
        ArrayAdapter<String> adapter_freq = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_freq);
        dropdown_freq.setAdapter(adapter_freq);

        // Drop down list #2
        String[] items_timesaday = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> adapter_timesaday = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_timesaday);
        dropdown_timesaday.setAdapter(adapter_timesaday);

        // Text hide time dose 1
        text_time_dose_1.setTag(1);

        time_dose_view_1.setVisibility(View.GONE);

        timePicker_1.setHour(8);
        timePicker_1.setMinute(0);
        text_time_dose_1.setText(String.format("%02d:%02d", 8, 0));

        text_time_dose_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    time_dose_view_1.setVisibility(View.VISIBLE);
                    view.setTag(0);
                } else {
                    time_dose_view_1.setVisibility(View.GONE);
                    view.setTag(1);
                }
            }
        });

        timePicker_1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                text_time_dose_1.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        });

        // Text hide time dose 2
        text_time_dose_2.setTag(1);

        time_dose_view_2.setVisibility(View.GONE);

        timePicker_2.setHour(13);
        timePicker_2.setMinute(0);
        text_time_dose_2.setText(String.format("%02d:%02d", 13, 0));

        text_time_dose_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    time_dose_view_2.setVisibility(View.VISIBLE);
                    view.setTag(0);
                } else {
                    time_dose_view_2.setVisibility(View.GONE);
                    view.setTag(1);
                }
            }
        });

        timePicker_2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                text_time_dose_2.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        });

        // Text hide time dose 3
        text_time_dose_3.setTag(1);

        time_dose_view_3.setVisibility(View.GONE);

        timePicker_3.setHour(18);
        timePicker_3.setMinute(0);
        text_time_dose_3.setText(String.format("%02d:%02d", 18, 0));

        text_time_dose_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    time_dose_view_3.setVisibility(View.VISIBLE);
                    view.setTag(0);
                } else {
                    time_dose_view_3.setVisibility(View.GONE);
                    view.setTag(1);
                }
            }
        });

        timePicker_3.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                text_time_dose_3.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        });

        // Text hide time dose 4
        text_time_dose_4.setTag(1);

        time_dose_view_4.setVisibility(View.GONE);

        timePicker_4.setHour(23);
        timePicker_4.setMinute(0);
        text_time_dose_4.setText(String.format("%02d:%02d", 23, 0));

        text_time_dose_4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    time_dose_view_4.setVisibility(View.VISIBLE);
                    view.setTag(0);
                } else {
                    time_dose_view_4.setVisibility(View.GONE);
                    view.setTag(1);
                }
            }
        });

        timePicker_4.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                text_time_dose_4.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        });

        text_time_dose_1.setVisibility(View.GONE);
        text_time_dose_2.setVisibility(View.GONE);
        text_time_dose_3.setVisibility(View.GONE);
        text_time_dose_4.setVisibility(View.GONE);

        dropdown_timesaday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    text_time_dose_1.setVisibility(View.VISIBLE);
                    text_time_dose_2.setVisibility(View.GONE);
                    text_time_dose_3.setVisibility(View.GONE);
                    text_time_dose_4.setVisibility(View.GONE);
                }
                if (position == 1) {
                    text_time_dose_1.setVisibility(View.VISIBLE);
                    text_time_dose_2.setVisibility(View.VISIBLE);
                    text_time_dose_3.setVisibility(View.GONE);
                    text_time_dose_4.setVisibility(View.GONE);
                }
                if (position == 2) {
                    text_time_dose_1.setVisibility(View.VISIBLE);
                    text_time_dose_2.setVisibility(View.VISIBLE);
                    text_time_dose_3.setVisibility(View.VISIBLE);
                    text_time_dose_4.setVisibility(View.GONE);
                }
                if (position == 3) {
                    text_time_dose_1.setVisibility(View.VISIBLE);
                    text_time_dose_2.setVisibility(View.VISIBLE);
                    text_time_dose_3.setVisibility(View.VISIBLE);
                    text_time_dose_4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Button button_save = view.findViewById(R.id.button_med_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleMedication();
                getFragmentManager().beginTransaction().replace(R.id.frameLayoutView, new Frag_Home()).commit();
                bottomNavigationView.setSelectedItemId(R.id.home);

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scheduleMedication() {
        int medId = new Random().nextInt(Integer.MAX_VALUE);

        int stockNum, dose1, dose2, dose3, dose4;

        try {
            stockNum = Integer.parseInt(quantity.getText().toString());
        } catch (NumberFormatException e) {
            stockNum = 0;
        }

        try {
            dose1 = Integer.parseInt(dose_1.getText().toString());
        } catch (NumberFormatException e) {
            dose1 = 0;
        }

        try {
            dose2 = Integer.parseInt(dose_2.getText().toString());
        } catch (NumberFormatException e) {
            dose2 = 0;
        }

        try {
            dose3 = Integer.parseInt(dose_3.getText().toString());
        } catch (NumberFormatException e) {
            dose3 = 0;
        }

        try {
            dose4 = Integer.parseInt(dose_4.getText().toString());
        } catch (NumberFormatException e) {
            dose4 = 0;
        }

        Medication medication = new Medication(
            medId,
            name.getText().toString(),
            description.getText().toString(),
            stockNum,
            shape_radioBtnGroup.indexOfChild(getView().findViewById(shape_radioBtnGroup.getCheckedRadioButtonId())),
            System.currentTimeMillis(),
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            dropdown_timesaday.getSelectedItemPosition(),
            timePicker_1.getHour(),
            timePicker_1.getMinute(),
            dose1,
            timePicker_2.getHour(),
            timePicker_2.getMinute(),
            dose2,
            timePicker_3.getHour(),
            timePicker_3.getMinute(),
            dose3,
            timePicker_4.getHour(),
            timePicker_4.getMinute(),
            dose4
        );

        createMedicationViewModel.insert(medication);

        medication.schedule(getContext());
    }
}