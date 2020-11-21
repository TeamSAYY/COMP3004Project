package com.example.drmednotifier;


import android.content.Context;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;
import com.example.drmednotifier.service.DescheduleAlarmsService;
import com.example.drmednotifier.service.RescheduleAlarmsService;


public class Frag_Notification extends Fragment {

    private NotifSettingDao notifSettingDao;
    private NotifSetting notifSetting;

    private Switch switch_noti;
    private View layout_noti;
    private Spinner dropdown_noti_type;
    private Spinner dropdown_noti_remind;
    private EditText edit_noti_msg;

    private Switch switch_renew_pre;
    private View layout_renew_pre;
    private Spinner dropdown_renew_time;
    private EditText edit_noti__renew_msg;


    public Frag_Notification() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotifSettingDatabase notifSettingDatabase = NotifSettingDatabase.getDatabase(getContext());
        notifSettingDao = notifSettingDatabase.notifSettingDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag__notification, container, false);

        switch_noti = view.findViewById(R.id.switch_noti);
        layout_noti = view.findViewById(R.id.layout_noti);
        dropdown_noti_type = view.findViewById(R.id.spinner_noti_type);
        dropdown_noti_remind = view.findViewById(R.id.spinner_noti_remind);
        edit_noti_msg = view.findViewById(R.id.edit_noti_msg);
        switch_renew_pre = view.findViewById(R.id.switch_renew_pre);
        layout_renew_pre = view.findViewById(R.id.layout_renew_pre);
        dropdown_renew_time = view.findViewById(R.id.spinner_renew_time);
        edit_noti__renew_msg = view.findViewById(R.id.edit_noti__renew_msg);

        // Create a list of items for the spinner.
        String[] items_noti_type = new String[]{"Push Notification", "Push Notification & Vibrate", "Push Notification, Vibrate & Alert"};
        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter_noti_type = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_noti_type);
        // Set the spinners adapter to the previously created one.
        dropdown_noti_type.setAdapter(adapter_noti_type);

        // Create a list of items for the spinner.
        String[] items_noti_remind = new String[]{"None", "2 min", "5 min", "10 min", "30 min", "60 min"};
        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter_noti_remind = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_noti_remind);
        // Set the spinners adapter to the previously created one.
        dropdown_noti_remind.setAdapter(adapter_noti_remind);

        // Create a list of items for the spinner.
        String[] items_renew_time = new String[]{"1 day before", "2 days before", "7 days before", "14 days before", "31 days before"};
        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter_renew_time = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items_renew_time);
        // Set the spinners adapter to the previously created one.
        dropdown_renew_time.setAdapter(adapter_renew_time);

        setNotifSettings();

        switch_noti.setOnCheckedChangeListener(notif_switchListener);
        dropdown_noti_type.  setOnItemSelectedListener(dropdownListener);
        dropdown_noti_remind.setOnItemSelectedListener(dropdownListener);
        edit_noti_msg.addTextChangedListener(textWatcher);
        switch_renew_pre.setOnCheckedChangeListener(refill_switchListener);
        dropdown_renew_time.setOnItemSelectedListener(dropdownListener);
        edit_noti__renew_msg.addTextChangedListener(textWatcher);

        return view;
    }

    private final CompoundButton.OnCheckedChangeListener notif_switchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            saveNotifSetting();
            if (isChecked) {
                layout_noti.setVisibility(View.VISIBLE);
                startRescheduleAlarmsService(getContext());
            } else {
                layout_noti.setVisibility(View.GONE);
                startDescheduleAlarmsService(getContext());
            }
        }
    };

    private final CompoundButton.OnCheckedChangeListener refill_switchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            saveNotifSetting();
            if (isChecked) {
                layout_renew_pre.setVisibility(View.VISIBLE);
            } else {
                layout_renew_pre.setVisibility(View.GONE);
            }
        }
    };

    private final AdapterView.OnItemSelectedListener dropdownListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            saveNotifSetting();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            saveNotifSetting();
        }
    };

    private void startDescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, DescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void setNotifSettings() {
        notifSetting = notifSettingDao.getNotifSettings().get(0);

        boolean enableNotif = notifSetting.isEnableNotif();
        int notifTypeId = notifSetting.getNotifTypeId();
        int remindInMinutesId = notifSetting.getRemindInMinutesId();
        String notifMessage = notifSetting.getNotifMessage();
        boolean enableRefillNotif = notifSetting.isEnableRefillNotif();
        int daysBeforeRefillId = notifSetting.getDaysBeforeRefillId();
        String refillNotifMessage = notifSetting.getRefillNotifMessage();

        switch_noti.setChecked(enableNotif);
        dropdown_noti_type.setSelection(notifTypeId);
        dropdown_noti_remind.setSelection(remindInMinutesId);
        edit_noti_msg.setText(notifMessage);
        switch_renew_pre.setChecked(enableRefillNotif);
        dropdown_renew_time.setSelection(daysBeforeRefillId);
        edit_noti__renew_msg.setText(refillNotifMessage);

        if (enableNotif) {
            layout_noti.setVisibility(View.VISIBLE);
        } else {
            layout_noti.setVisibility(View.GONE);
        }

        if (enableRefillNotif) {
            layout_renew_pre.setVisibility(View.VISIBLE);
        } else {
            layout_renew_pre.setVisibility(View.GONE);
        }
    }

    private void saveNotifSetting() {
        notifSetting = notifSettingDao.getNotifSettings().get(0);

        boolean enableNotif = switch_noti.isChecked();
        int notifTypeId = dropdown_noti_type.getSelectedItemPosition();
        int remindInMinutesId = dropdown_noti_remind.getSelectedItemPosition();
        String notifMessage = edit_noti_msg.getText().toString();
        boolean enableRefillNotif = switch_renew_pre.isChecked();
        int daysBeforeRefillId = dropdown_renew_time.getSelectedItemPosition();
        String refillNotifMessage = edit_noti__renew_msg.getText().toString();

        notifSetting.setEnableNotif(enableNotif);
        notifSetting.setNotifTypeId(notifTypeId);
        notifSetting.setRemindInMinutesId(remindInMinutesId);
        notifSetting.setNotifMessage(notifMessage);
        notifSetting.setEnableRefillNotif(enableRefillNotif);
        notifSetting.setDaysBeforeRefillId(daysBeforeRefillId);
        notifSetting.setRefillNotifMessage(refillNotifMessage);

        notifSettingDao.update(notifSetting);
    }
}