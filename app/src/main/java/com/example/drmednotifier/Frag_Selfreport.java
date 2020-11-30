package com.example.drmednotifier;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.data.MedActivity;
import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.medicationslist.MedActivitiesListViewModel;
import com.example.drmednotifier.medicationslist.MedicationsListViewModel;
import com.example.drmednotifier.medicationslist.SelfReportRecyclerViewAdapter;

import java.util.Calendar;
import java.util.List;


public class Frag_Selfreport extends Fragment {

    int year_1, month_1, day_1, weekday_1;
    int year_2, month_2, day_2, weekday_2;
    int year_3, month_3, day_3, weekday_3;
    int year_4, month_4, day_4, weekday_4;
    int year_5, month_5, day_5, weekday_5;
    int year_6, month_6, day_6, weekday_6;
    int year_7, month_7, day_7, weekday_7;

    private SelfReportRecyclerViewAdapter adapter_missed_1;
    private SelfReportRecyclerViewAdapter adapter_missed_2;
    private SelfReportRecyclerViewAdapter adapter_missed_3;
    private SelfReportRecyclerViewAdapter adapter_missed_4;
    private SelfReportRecyclerViewAdapter adapter_missed_5;
    private SelfReportRecyclerViewAdapter adapter_missed_6;
    private SelfReportRecyclerViewAdapter adapter_missed_7;

    private SelfReportRecyclerViewAdapter adapter_taken_1;
    private SelfReportRecyclerViewAdapter adapter_taken_2;
    private SelfReportRecyclerViewAdapter adapter_taken_3;
    private SelfReportRecyclerViewAdapter adapter_taken_4;
    private SelfReportRecyclerViewAdapter adapter_taken_5;
    private SelfReportRecyclerViewAdapter adapter_taken_6;
    private SelfReportRecyclerViewAdapter adapter_taken_7;

    public Frag_Selfreport() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = this;

        Calendar calendar = Calendar.getInstance();

        int DAY = 24 * 60 * 60 * 1000;
        long currentTimeMillis = System.currentTimeMillis() + DAY;

        calendar.setTimeInMillis(currentTimeMillis - DAY);

        year_1 = calendar.get(Calendar.YEAR);
        month_1 = calendar.get(Calendar.MONTH);
        day_1 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_1 = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTimeInMillis(currentTimeMillis - 2*DAY);

        year_2 = calendar.get(Calendar.YEAR);
        month_2 = calendar.get(Calendar.MONTH);
        day_2 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_2 = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTimeInMillis(currentTimeMillis - 3*DAY);

        year_3 = calendar.get(Calendar.YEAR);
        month_3 = calendar.get(Calendar.MONTH);
        day_3 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_3 = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTimeInMillis(currentTimeMillis - 4*DAY);

        year_4 = calendar.get(Calendar.YEAR);
        month_4 = calendar.get(Calendar.MONTH);
        day_4 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_4 = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTimeInMillis(currentTimeMillis - 5*DAY);

        year_5 = calendar.get(Calendar.YEAR);
        month_5 = calendar.get(Calendar.MONTH);
        day_5 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_5 = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTimeInMillis(currentTimeMillis - 6*DAY);

        year_6 = calendar.get(Calendar.YEAR);
        month_6 = calendar.get(Calendar.MONTH);
        day_6 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_6 = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTimeInMillis(currentTimeMillis - 7*DAY);

        year_7 = calendar.get(Calendar.YEAR);
        month_7 = calendar.get(Calendar.MONTH);
        day_7 = calendar.get(Calendar.DAY_OF_MONTH);
        weekday_7 = calendar.get(Calendar.DAY_OF_WEEK);


        adapter_missed_1 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_1.setTimeMillis(currentTimeMillis - DAY);
        adapter_missed_2 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_2.setTimeMillis(currentTimeMillis - 2*DAY);
        adapter_missed_3 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_3.setTimeMillis(currentTimeMillis - 3*DAY);
        adapter_missed_4 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_4.setTimeMillis(currentTimeMillis - 4*DAY);
        adapter_missed_5 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_5.setTimeMillis(currentTimeMillis - 5*DAY);
        adapter_missed_6 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_6.setTimeMillis(currentTimeMillis - 6*DAY);
        adapter_missed_7 = new SelfReportRecyclerViewAdapter(false);
        adapter_missed_7.setTimeMillis(currentTimeMillis - 7*DAY);


        adapter_taken_1 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_1.setTimeMillis(currentTimeMillis - DAY);
        adapter_taken_2 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_2.setTimeMillis(currentTimeMillis - 2*DAY);
        adapter_taken_3 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_3.setTimeMillis(currentTimeMillis - 3*DAY);
        adapter_taken_4 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_4.setTimeMillis(currentTimeMillis - 4*DAY);
        adapter_taken_5 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_5.setTimeMillis(currentTimeMillis - 5*DAY);
        adapter_taken_6 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_6.setTimeMillis(currentTimeMillis - 6*DAY);
        adapter_taken_7 = new SelfReportRecyclerViewAdapter(true);
        adapter_taken_7.setTimeMillis(currentTimeMillis - 7*DAY);

        MedActivitiesListViewModel medActivitiesListViewModel = ViewModelProviders.of(this).get(MedActivitiesListViewModel.class);
        medActivitiesListViewModel.getMedActivitiesLiveDataLastWeek().observe(this, new Observer<List<MedActivity>>() {
            @Override
            public void onChanged(List<MedActivity> medActivities) {
                Log.d("myTag", "size: " + medActivities.size());
                if (medActivities != null) {
                    adapter_missed_1.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_missed_2.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_missed_3.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_missed_4.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_missed_5.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_missed_6.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_missed_7.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_1.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_2.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_3.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_4.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_5.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_6.setMedActivities(getActivity().getApplication(), medActivities);
                    adapter_taken_7.setMedActivities(getActivity().getApplication(), medActivities);

                    medActivitiesListViewModel.getMedActivitiesLiveData().removeObserver(this);

                    MedicationsListViewModel medicationsListViewModel = ViewModelProviders.of(fragment).get(MedicationsListViewModel.class);
                    medicationsListViewModel.getMedicationsLiveData().observe(fragment , new Observer<List<Medication>>() {
                        @Override
                        public void onChanged(List<Medication> medications) {
                            if (medications != null) {
                                adapter_missed_1.setMedications(medications);
                                adapter_missed_2.setMedications(medications);
                                adapter_missed_3.setMedications(medications);
                                adapter_missed_4.setMedications(medications);
                                adapter_missed_5.setMedications(medications);
                                adapter_missed_6.setMedications(medications);
                                adapter_missed_7.setMedications(medications);
                                adapter_taken_1.setMedications(medications);
                                adapter_taken_2.setMedications(medications);
                                adapter_taken_3.setMedications(medications);
                                adapter_taken_4.setMedications(medications);
                                adapter_taken_5.setMedications(medications);
                                adapter_taken_6.setMedications(medications);
                                adapter_taken_7.setMedications(medications);
                                medicationsListViewModel.getMedicationsLiveData().removeObserver(this);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag__selfreport, container, false);

        TextView textView_dataRange = v.findViewById(R.id.self_report_date_range);
        TextView textView_date_1 = v.findViewById(R.id.self_report_date_1);
        TextView textView_date_2 = v.findViewById(R.id.self_report_date_2);
        TextView textView_date_3 = v.findViewById(R.id.self_report_date_3);
        TextView textView_date_4 = v.findViewById(R.id.self_report_date_4);
        TextView textView_date_5 = v.findViewById(R.id.self_report_date_5);
        TextView textView_date_6 = v.findViewById(R.id.self_report_date_6);
        TextView textView_date_7 = v.findViewById(R.id.self_report_date_7);
        RecyclerView recyclerView_missed_1 = v.findViewById(R.id.self_report_missed_list_1);
        RecyclerView recyclerView_missed_2 = v.findViewById(R.id.self_report_missed_list_2);
        RecyclerView recyclerView_missed_3 = v.findViewById(R.id.self_report_missed_list_3);
        RecyclerView recyclerView_missed_4 = v.findViewById(R.id.self_report_missed_list_4);
        RecyclerView recyclerView_missed_5 = v.findViewById(R.id.self_report_missed_list_5);
        RecyclerView recyclerView_missed_6 = v.findViewById(R.id.self_report_missed_list_6);
        RecyclerView recyclerView_missed_7 = v.findViewById(R.id.self_report_missed_list_7);
        RecyclerView recyclerView_taken_1 = v.findViewById(R.id.self_report_taken_list_1);
        RecyclerView recyclerView_taken_2 = v.findViewById(R.id.self_report_taken_list_2);
        RecyclerView recyclerView_taken_3 = v.findViewById(R.id.self_report_taken_list_3);
        RecyclerView recyclerView_taken_4 = v.findViewById(R.id.self_report_taken_list_4);
        RecyclerView recyclerView_taken_5 = v.findViewById(R.id.self_report_taken_list_5);
        RecyclerView recyclerView_taken_6 = v.findViewById(R.id.self_report_taken_list_6);
        RecyclerView recyclerView_taken_7 = v.findViewById(R.id.self_report_taken_list_7);

        textView_dataRange.setText(String.format("%s %d, %d - %s %d, %d", getStringFromMonth(month_7), day_7, year_7, getStringFromMonth(month_1), day_1, year_1));

        textView_date_1.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_1), getStringFromMonth(month_1), day_1));
        textView_date_2.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_2), getStringFromMonth(month_2), day_2));
        textView_date_3.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_3), getStringFromMonth(month_3), day_3));
        textView_date_4.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_4), getStringFromMonth(month_4), day_4));
        textView_date_5.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_5), getStringFromMonth(month_5), day_5));
        textView_date_6.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_6), getStringFromMonth(month_6), day_6));
        textView_date_7.setText(String.format("%s, %s %d", getStringFromWeekday(weekday_7), getStringFromMonth(month_7), day_7));


        recyclerView_missed_1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_1.setAdapter(adapter_missed_1);
        recyclerView_missed_2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_2.setAdapter(adapter_missed_2);
        recyclerView_missed_3.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_3.setAdapter(adapter_missed_3);
        recyclerView_missed_4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_4.setAdapter(adapter_missed_4);
        recyclerView_missed_5.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_5.setAdapter(adapter_missed_5);
        recyclerView_missed_6.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_6.setAdapter(adapter_missed_6);
        recyclerView_missed_7.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_missed_7.setAdapter(adapter_missed_7);


        recyclerView_taken_1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_1.setAdapter(adapter_taken_1);
        recyclerView_taken_2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_2.setAdapter(adapter_taken_2);
        recyclerView_taken_3.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_3.setAdapter(adapter_taken_3);
        recyclerView_taken_4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_4.setAdapter(adapter_taken_4);
        recyclerView_taken_5.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_5.setAdapter(adapter_taken_5);
        recyclerView_taken_6.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_6.setAdapter(adapter_taken_6);
        recyclerView_taken_7.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_taken_7.setAdapter(adapter_taken_7);

        return v;
    }

    private String getStringFromMonth(int month) {
        switch (month){
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
        }
        return "";
    }

    private String getStringFromWeekday(int weekday) {
        switch (weekday){
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return "";
    }
}