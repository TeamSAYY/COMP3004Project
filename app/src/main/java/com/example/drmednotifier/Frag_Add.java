package com.example.drmednotifier;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
    @BindView(R.id.llayout_specificDays_1) View specificDays_view_1;
    @BindView(R.id.llayout_specificDays_2) View specificDays_view_2;
    @BindView(R.id.add_med_checkMon) CheckBox checkBox_Mon;
    @BindView(R.id.add_med_checkTue) CheckBox checkBox_Tue;
    @BindView(R.id.add_med_checkWed) CheckBox checkBox_Wed;
    @BindView(R.id.add_med_checkThu) CheckBox checkBox_Thu;
    @BindView(R.id.add_med_checkFri) CheckBox checkBox_Fri;
    @BindView(R.id.add_med_checkSat) CheckBox checkBox_Sat;
    @BindView(R.id.add_med_checkSun) CheckBox checkBox_Sun;
    @BindView(R.id.spinner_med_timesPerDay) Spinner dropdown_timesPerDay;
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
    @BindView(R.id.button_med_save) Button button_save;

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

        // Buttons hide views
        buttonHideView(button_hide_med_info, med_info_view) ;
        buttonHideView(button_hide_schedule, med_schedule_view) ;

        // Drop down list - frequency
        String[] items_freq = new String[]{"Every Day", "Specific Day"};
        ArrayAdapter<String> adapter_freq = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_freq);
        dropdown_freq.setAdapter(adapter_freq);

        checkBox_Mon.setChecked(true);
        checkBox_Tue.setChecked(true);
        checkBox_Wed.setChecked(true);
        checkBox_Thu.setChecked(true);
        checkBox_Fri.setChecked(true);
        checkBox_Sat.setChecked(true);
        checkBox_Sun.setChecked(true);

        specificDays_view_1.setVisibility(View.GONE);
        specificDays_view_2.setVisibility(View.GONE);

        dropdown_freq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        specificDays_view_1.setVisibility(View.GONE);
                        specificDays_view_2.setVisibility(View.GONE);
                        break;
                    case 1:
                        specificDays_view_1.setVisibility(View.VISIBLE);
                        specificDays_view_2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Drop down list - times per day
        String[] items_timesPerDay = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> adapter_timesPerDay = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_timesPerDay);
        dropdown_timesPerDay.setAdapter(adapter_timesPerDay);

        dose_1.setText("1");
        dose_2.setText("1");
        dose_3.setText("1");
        dose_4.setText("1");

        text_time_dose_1.setVisibility(View.GONE);
        time_dose_view_1.setVisibility(View.GONE);
        text_time_dose_2.setVisibility(View.GONE);
        time_dose_view_2.setVisibility(View.GONE);
        text_time_dose_3.setVisibility(View.GONE);
        time_dose_view_3.setVisibility(View.GONE);
        text_time_dose_4.setVisibility(View.GONE);
        time_dose_view_4.setVisibility(View.GONE);

        dropdown_timesPerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text_time_dose_1.setVisibility(View.GONE);
                time_dose_view_1.setVisibility(View.GONE);
                text_time_dose_2.setVisibility(View.GONE);
                time_dose_view_2.setVisibility(View.GONE);
                text_time_dose_3.setVisibility(View.GONE);
                time_dose_view_3.setVisibility(View.GONE);
                text_time_dose_4.setVisibility(View.GONE);
                time_dose_view_4.setVisibility(View.GONE);
                switch (position) {
                    case 3:
                        text_time_dose_4.setVisibility(View.VISIBLE);
                    case 2:
                        text_time_dose_3.setVisibility(View.VISIBLE);
                    case 1:
                        text_time_dose_2.setVisibility(View.VISIBLE);
                    case 0:
                        text_time_dose_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        // Generate times and doses
        generateTimeAndDose(text_time_dose_1, time_dose_view_1, timePicker_1,8,0);
        generateTimeAndDose(text_time_dose_2, time_dose_view_2, timePicker_2,13,0);
        generateTimeAndDose(text_time_dose_3, time_dose_view_3, timePicker_3,18,0);
        generateTimeAndDose(text_time_dose_4, time_dose_view_4, timePicker_4,23,0);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);

        // Save button
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleMedication();
                getFragmentManager().beginTransaction().replace(R.id.frameLayoutView, new Frag_Home()).commit();
                bottomNavigationView.setSelectedItemId(R.id.home);
            }
        });

        decideSaveButtonStatus();
        setMandatoryTextListener();

        // Inflate the layout for this fragment
        return view;
    }

    private void buttonHideView(Button button, View view) {
        button.setTag(1);
        button.setText("hide");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {
                    button.setText("show");
                    view.setVisibility(View.GONE);
                    v.setTag(0);
                } else {
                    button.setText("hide");
                    view.setVisibility(View.VISIBLE);
                    v.setTag(1);
                }
            }
        });
    }

    private void generateTimeAndDose(TextView text_time_dose, View time_dose_view, TimePicker timePicker, int defaultHour, int defaultMinute) {
        text_time_dose.setTag(1);
        time_dose_view.setVisibility(View.GONE);

        timePicker.setHour(defaultHour);
        timePicker.setMinute(defaultMinute);

        text_time_dose.setText(String.format("%02d:%02d", defaultHour, defaultMinute));
        text_time_dose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    time_dose_view_1.setVisibility(View.GONE);
                    text_time_dose_1.setTag(1);
                    time_dose_view_2.setVisibility(View.GONE);
                    text_time_dose_2.setTag(1);
                    time_dose_view_3.setVisibility(View.GONE);
                    text_time_dose_3.setTag(1);
                    time_dose_view_4.setVisibility(View.GONE);
                    text_time_dose_4.setTag(1);
                    time_dose_view.setVisibility(View.VISIBLE);
                    view.setTag(0);
                } else {
                    time_dose_view.setVisibility(View.GONE);
                    view.setTag(1);
                }
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                text_time_dose.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scheduleMedication() {
        int medId = new Random().nextInt(Integer.MAX_VALUE);

        int stockNum, times, dose1, dose2, dose3, dose4;

        try {
            stockNum = Integer.parseInt(quantity.getText().toString());
        } catch (NumberFormatException e) {
            stockNum = 0;
        }

        times = dropdown_timesPerDay.getSelectedItemPosition() + 1;

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

        boolean mon, tue, wed, thu, fri, sat, sun;

        if (dropdown_freq.getSelectedItemPosition() == 0) {
            mon = true;
            tue = true;
            wed = true;
            thu = true;
            fri = true;
            sat = true;
            sun = true;
        } else {
            mon = checkBox_Mon.isChecked();
            tue = checkBox_Tue.isChecked();
            wed = checkBox_Wed.isChecked();
            thu = checkBox_Thu.isChecked();
            fri = checkBox_Fri.isChecked();
            sat = checkBox_Sat.isChecked();
            sun = checkBox_Sun.isChecked();
        }

        Medication medication = new Medication(
            medId,
            name.getText().toString(),
            description.getText().toString(),
            stockNum,
            shape_radioBtnGroup.indexOfChild(getView().findViewById(shape_radioBtnGroup.getCheckedRadioButtonId())),
            System.currentTimeMillis(),
            mon,
            tue,
            wed,
            thu,
            fri,
            sat,
            sun,
            times,
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

    private void setMandatoryTextListener() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                decideSaveButtonStatus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                decideSaveButtonStatus();
            }
        };

        name.addTextChangedListener(textWatcher);
        quantity.addTextChangedListener(textWatcher);
        dose_1.addTextChangedListener(textWatcher);
        dose_2.addTextChangedListener(textWatcher);
        dose_3.addTextChangedListener(textWatcher);
        dose_4.addTextChangedListener(textWatcher);
    }

    private void decideSaveButtonStatus() {
        if (name.getText().length() == 0 ||
                quantity.getText().length() == 0 ||
                dose_1.getText().length() == 0 ||
                dose_2.getText().length() == 0 ||
                dose_3.getText().length() == 0 ||
                dose_4.getText().length() == 0) {
            button_save.setEnabled(false);
        } else {
            button_save.setEnabled(true);
        }
    }
}