package com.example.drmednotifier;

import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
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
import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;
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

    private NotifSettingDatabase notifSettingDatabase;
    private NotifSettingDao notifSettingDao;
    private NotifSetting notifSetting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createMedicationViewModel = ViewModelProviders.of(this).get(CreateMedicationViewModel.class);

        notifSettingDatabase = NotifSettingDatabase.getDatabase(getContext());
        notifSettingDao = notifSettingDatabase.notifSettingDao();
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

        Boolean editExisting = getActivity().getIntent().hasExtra("MED_ID");

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
                        checkBox_Mon.setChecked(true);
                        checkBox_Tue.setChecked(true);
                        checkBox_Wed.setChecked(true);
                        checkBox_Thu.setChecked(true);
                        checkBox_Fri.setChecked(true);
                        checkBox_Sat.setChecked(true);
                        checkBox_Sun.setChecked(true);
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

        if (editExisting) {
            populateExistingInfo();
            /* Code to update current medication */
            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateMedication();
                    getActivity().finish();
                }
            });
        } else {
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
        }

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

        RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                decideSaveButtonStatus();
            }
        };

        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                decideSaveButtonStatus();
            }
        };

        name.addTextChangedListener(textWatcher);
        quantity.addTextChangedListener(textWatcher);
        shape_radioBtnGroup.setOnCheckedChangeListener(radioGroupListener);
        checkBox_Mon.setOnCheckedChangeListener(checkBoxListener);
        checkBox_Tue.setOnCheckedChangeListener(checkBoxListener);
        checkBox_Wed.setOnCheckedChangeListener(checkBoxListener);
        checkBox_Thu.setOnCheckedChangeListener(checkBoxListener);
        checkBox_Fri.setOnCheckedChangeListener(checkBoxListener);
        checkBox_Sat.setOnCheckedChangeListener(checkBoxListener);
        checkBox_Sun.setOnCheckedChangeListener(checkBoxListener);
        dose_1.addTextChangedListener(textWatcher);
        dose_2.addTextChangedListener(textWatcher);
        dose_3.addTextChangedListener(textWatcher);
        dose_4.addTextChangedListener(textWatcher);
    }

    private void decideSaveButtonStatus() {
        if (name.getText().length() == 0 ||
                quantity.getText().length() == 0 ||
                shape_radioBtnGroup.getCheckedRadioButtonId() == -1 ||
                (dropdown_freq.getSelectedItemPosition() == 1 &&
                        !checkBox_Mon.isChecked() &&
                        !checkBox_Tue.isChecked() &&
                        !checkBox_Wed.isChecked() &&
                        !checkBox_Thu.isChecked() &&
                        !checkBox_Fri.isChecked() &&
                        !checkBox_Sat.isChecked() &&
                        !checkBox_Sun.isChecked()) ||
                dose_1.getText().length() == 0 ||
                dose_2.getText().length() == 0 ||
                dose_3.getText().length() == 0 ||
                dose_4.getText().length() == 0) {
            button_save.setEnabled(false);
        } else {
            button_save.setEnabled(true);
        }
    }

    private void populateExistingInfo() {
        Intent intent = getActivity().getIntent();
        name.setText(intent.getStringExtra("MED_NAME"));
        description.setText(intent.getStringExtra("DESCRIPTION"));
        quantity.setText(String.format("%d", intent.getIntExtra("QUANTITY", 0)));
        ((RadioButton) shape_radioBtnGroup.getChildAt(intent.getIntExtra("SHAPE_ID", -1))).setChecked(true);
        boolean mon, tue, wed, thu, fri, sat, sun;
        mon = intent.getBooleanExtra("MONDAY", true);
        tue = intent.getBooleanExtra("TUESDAY", true);
        wed = intent.getBooleanExtra("WEDNESDAY", true);
        thu = intent.getBooleanExtra("THURSDAY", true);
        fri = intent.getBooleanExtra("FRIDAY", true);
        sat = intent.getBooleanExtra("SATURDAY", true);
        sun = intent.getBooleanExtra("SUNDAY", true);
        if (mon && tue && wed && thu && fri && sat && sun) {
            dropdown_freq.setSelection(0);
        } else {
            dropdown_freq.setSelection(1);
            checkBox_Mon.setChecked(mon);
            checkBox_Tue.setChecked(tue);
            checkBox_Wed.setChecked(wed);
            checkBox_Thu.setChecked(thu);
            checkBox_Fri.setChecked(fri);
            checkBox_Sat.setChecked(sat);
            checkBox_Sun.setChecked(sun);
        }
        int times = intent.getIntExtra("TIMES", 1);
        dropdown_timesPerDay.setSelection(times - 1);
        if (times >= 1) {
            timePicker_1.setHour(intent.getIntExtra("HOUR_1", 8));
            timePicker_1.setMinute(intent.getIntExtra("MINUTE_1", 0));
            dose_1.setText(String.format("%d", intent.getIntExtra("DOSE_1", 1)));
        }
        if (times >= 2) {
            timePicker_2.setHour(intent.getIntExtra("HOUR_2", 13));
            timePicker_2.setMinute(intent.getIntExtra("MINUTE_2", 0));
            dose_2.setText(String.format("%d", intent.getIntExtra("DOSE_2", 1)));
        }
        if (times >= 3) {
            timePicker_3.setHour(intent.getIntExtra("HOUR_3", 18));
            timePicker_3.setMinute(intent.getIntExtra("MINUTE_3", 0));
            dose_3.setText(String.format("%d", intent.getIntExtra("DOSE_3", 1)));
        }
        if (times >= 4) {
            timePicker_4.setHour(intent.getIntExtra("HOUR_4", 23));
            timePicker_4.setMinute(intent.getIntExtra("MINUTE_4", 0));
            dose_4.setText(String.format("%d", intent.getIntExtra("DOSE_4", 1)));
        }
    }

    private void updateMedication() {
        Intent intent = getActivity().getIntent();

        int medId = intent.getIntExtra("MED_ID", new Random().nextInt(Integer.MAX_VALUE));

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
                intent.getLongExtra("CREATED", System.currentTimeMillis()),
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

        createMedicationViewModel.update(medication);

        notifSetting = notifSettingDao.getNotifSettings().get(0);
        boolean enableNotif = notifSetting.isEnableNotif();

        if (enableNotif) {
            medication.schedule(getContext());
        }
    }
}