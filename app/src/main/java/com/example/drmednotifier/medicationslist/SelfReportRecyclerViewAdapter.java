package com.example.drmednotifier.medicationslist;

import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drmednotifier.R;
import com.example.drmednotifier.data.MedActivity;
import com.example.drmednotifier.data.Medication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SelfReportRecyclerViewAdapter extends RecyclerView.Adapter<SelfReportViewHolder> {
    private final boolean taken;
    private List<Medication> doses;
    private List<MedActivity> medActivities;
    private List<Medication> medications;

    private Application application;

    private long timeMillis;
    private int year;
    private int month;
    private int day;

    public SelfReportRecyclerViewAdapter(Boolean taken) {
        this.taken = taken;
        doses = new ArrayList<>();
        medActivities = new ArrayList<>();
    }

    @NonNull
    @Override
    public SelfReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dose, parent, false);
        return new SelfReportViewHolder(itemView, application, timeMillis);
    }

    @Override
    public void onBindViewHolder(@NonNull SelfReportViewHolder holder, int position) {
        Medication dose = doses.get(position);
        int doseMedId = dose.getMedId();
        boolean bound = false;
        for (MedActivity medActivity : medActivities) {
            int medActivityMedId = medActivity.getMedId();
            int numOfAlarm = doseMedId - medActivityMedId;
            if (numOfAlarm >= 1 && numOfAlarm <= 4) {
                for (Medication medication : medications) {
                    if (medication.getMedId() == medActivity.getMedId()) {
                        holder.bind(medication, medActivity, numOfAlarm, taken);
                        bound = true;
                        break;
                    }
                }
                break;
            }
        }
        if (!bound) {
            holder.bind(dose);
        }
    }

    @Override
    public int getItemCount() {
        return doses.size();
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        long millis = calendar.getTimeInMillis();

        doses = new ArrayList<>();
        if (medications == null) return;

        for (Medication medication : medications) {
            if (millis < medication.getCreated()) continue;
            if (dayOfWeek == 1 && !medication.isSunday()) continue;
            if (dayOfWeek == 2 && !medication.isMonday()) continue;
            if (dayOfWeek == 3 && !medication.isTuesday()) continue;
            if (dayOfWeek == 4 && !medication.isWednesday()) continue;
            if (dayOfWeek == 5 && !medication.isThursday()) continue;
            if (dayOfWeek == 6 && !medication.isFriday()) continue;
            if (dayOfWeek == 7 && !medication.isSaturday()) continue;

            MedActivity medActivity = null;
            for (MedActivity m : medActivities) {
                if (medication.getMedId() == m.getMedId()) {
                    medActivity = m;
                    break;
                }
            }

            Log.d("myTag", "med activity is " + (medActivity==null?"null":"not null"));

            int times = medication.getTimes();
            if (times >= 1) {
                if (medActivity==null || medActivity.getMedStatus(1) && !taken || !medActivity.getMedStatus(1) && taken) {
                    doses.add(new Medication(
                            medication.getMedId() + 1,
                            medication.getName(),
                            medication.getDescription(),
                            medication.getQuantity(),
                            medication.getShape_id(),
                            medication.getCreated(),
                            medication.isMonday(),
                            medication.isTuesday(),
                            medication.isWednesday(),
                            medication.isThursday(),
                            medication.isFriday(),
                            medication.isSaturday(),
                            medication.isSunday(),
                            medication.getHour_1(),
                            medication.getMinute_1(),
                            medication.getDose_1()
                    ));
                }
            }
            if (times >= 2) {
                if (medActivity==null || medActivity.getMedStatus(2) && !taken || !medActivity.getMedStatus(2) && taken) {
                    doses.add(new Medication(
                            medication.getMedId() + 2,
                            medication.getName(),
                            medication.getDescription(),
                            medication.getQuantity(),
                            medication.getShape_id(),
                            medication.getCreated(),
                            medication.isMonday(),
                            medication.isTuesday(),
                            medication.isWednesday(),
                            medication.isThursday(),
                            medication.isFriday(),
                            medication.isSaturday(),
                            medication.isSunday(),
                            medication.getHour_2(),
                            medication.getMinute_2(),
                            medication.getDose_2()
                    ));
                }
            }
            if (times >= 3) {
                if (medActivity==null || medActivity.getMedStatus(3) && !taken || !medActivity.getMedStatus(3) && taken) {
                    doses.add(new Medication(
                            medication.getMedId() + 3,
                            medication.getName(),
                            medication.getDescription(),
                            medication.getQuantity(),
                            medication.getShape_id(),
                            medication.getCreated(),
                            medication.isMonday(),
                            medication.isTuesday(),
                            medication.isWednesday(),
                            medication.isThursday(),
                            medication.isFriday(),
                            medication.isSaturday(),
                            medication.isSunday(),
                            medication.getHour_3(),
                            medication.getMinute_3(),
                            medication.getDose_3()
                    ));
                }
            }
            if (times >= 4) {
                if (medActivity==null || medActivity.getMedStatus(4) && !taken || !medActivity.getMedStatus(4) && taken) {
                    doses.add(new Medication(
                            medication.getMedId() + 4,
                            medication.getName(),
                            medication.getDescription(),
                            medication.getQuantity(),
                            medication.getShape_id(),
                            medication.getCreated(),
                            medication.isMonday(),
                            medication.isTuesday(),
                            medication.isWednesday(),
                            medication.isThursday(),
                            medication.isFriday(),
                            medication.isSaturday(),
                            medication.isSunday(),
                            medication.getHour_4(),
                            medication.getMinute_4(),
                            medication.getDose_4()
                    ));
                }
            }
        }

        Collections.sort(doses, new Comparator<Medication>() {
            @Override
            public int compare(Medication o1, Medication o2) {
                return (o1.getHour_1() - o2.getHour_1()) * 60 + (o1.getMinute_1() - o2.getMinute_1());
            }
        });

        notifyDataSetChanged();
    }

    public void setMedActivities(Application application, List<MedActivity> medActivities) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        if (medActivities == null) {
            Log.d("myTag", "MED ACTIVITIES EMPTY");
            return;
        } else {
            Log.d("myTag", "MED ACTIVITIES NOT EMPTY");
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        for (MedActivity medActivity : medActivities) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(medActivity.getDate());
            if (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                Log.d("myTag", "ACTIVITY ADDED");
                this.medActivities.add(medActivity);

                List<Medication> found = new ArrayList<>();
                for (Medication m : doses) {
                    int numOfAlarm = m.getMedId() - medActivity.getMedId();
                    if (numOfAlarm >= 1 && numOfAlarm <=4) {
                        if (medActivity.getMedStatus(numOfAlarm) && !taken || !medActivity.getMedStatus(numOfAlarm) && taken) {
                            found.add(m);
                        }
                    }
                }
                doses.removeAll(found);
            }
        }

        this.application = application;

        notifyDataSetChanged();
    }
}