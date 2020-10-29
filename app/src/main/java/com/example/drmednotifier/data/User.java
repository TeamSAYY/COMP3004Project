package com.example.drmednotifier.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    private int userId;

    private String firstName;
    private String lastName;
    private int age;




    // 0 - Male, 1 - Female, 2 - Other
    private int gender;

    /*private String avatar;*/

    private long created;



    public User(int userId, String firstName, String lastName, int age, int gender, long created) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.created = created;
    }




    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public boolean isMale() {
        return gender == 0;
    }

    public boolean isFemale() {
        return gender == 1;
    }

    public boolean isOthers() {
        return gender == 2;
    }

    public long getCreated() {
        return created;
    }

    public void  setFirstName(String fname) { this.firstName=fname; }

    public void  setLastName(String lname) { this.lastName=lname; }

    public void  setAge(int age) { this.age=age; }

   /* public void  setAvatar(String avatar) { this.avatar=avatar; }

    public String getAvatar() {
        return avatar;
    }*/

    public void setCreated(long created) {
        this.created = created;
    }
}
