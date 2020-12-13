package com.example.drmednotifier.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "medactivity_table",
        indices = {@Index(value = {"medId", "date"}, unique = true)})
public class MedActivity {
    @PrimaryKey
    @NonNull
    private int medActivityId;

    @NonNull
    private int medId;

    private Date date;

    private boolean firstMed;
    private boolean secondMed;
    private boolean thirdMed;
    private boolean fourthMed;

    public MedActivity(int medId, Date date) {
        this.medActivityId = GenerateRandomInt.get();
        this.medId = medId;
        this.date = date;
        this.firstMed = false;
        this.secondMed = false;
        this.thirdMed = false;
        this.fourthMed = false;
    }

    public int getMedActivityId() {
        return medActivityId;
    }

    public void setMedActivityId(int medActivityId) {
        this.medActivityId = medActivityId;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFirstMed() {
        return firstMed;
    }

    public boolean isSecondMed() {
        return secondMed;
    }

    public boolean isThirdMed() {
        return thirdMed;
    }

    public boolean isFourthMed() {
        return fourthMed;
    }

    public boolean getMedStatus(int num) {
        switch (num) {
            case 1:
                return firstMed;
            case 2:
                return secondMed;
            case 3:
                return thirdMed;
            case 4:
                return fourthMed;
        }
        return false;
    }

    public void setFirstMed(boolean firstMed) {
        this.firstMed = firstMed;
    }

    public void setSecondMed(boolean secondMed) {
        this.secondMed = secondMed;
    }

    public void setThirdMed(boolean thirdMed) {
        this.thirdMed = thirdMed;
    }

    public void setFourthMed(boolean fourthMed) {
        this.fourthMed = fourthMed;
    }

    public void setMedStatus(int num, boolean medStatus) {
        switch (num) {
            case 1:
                firstMed = medStatus;
                break;
            case 2:
                secondMed = medStatus;
                break;
            case 3:
                thirdMed = medStatus;
                break;
            case 4:
                fourthMed = medStatus;
                break;
        }
    }
}