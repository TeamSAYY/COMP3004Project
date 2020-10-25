package com.example.drmednotifier.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

// This class is not being used - YS

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> usersLiveData;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        userDao = db.userDao();
        // usersLiveData = userDao.getUser();
    }

    public void insert(final User user) {
        UserDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    public void update(final User user) {
        UserDatabase.databaseWriteExecutor.execute(() -> {
            userDao.update(user);
        });
    }

    public LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }
}
