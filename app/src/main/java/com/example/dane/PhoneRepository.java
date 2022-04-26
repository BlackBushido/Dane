package com.example.dane;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private PhoneDao mPhoneDao;
    private LiveData<List<Phone>> mAllPhones;

    PhoneRepository(Application application){
        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);

        mPhoneDao = phoneRoomDatabase.phoneDao();
        mAllPhones = mPhoneDao.getAlpabetizedPhones();
    }

    LiveData<List<Phone>> getAllPhones(){
        return mAllPhones;
    }

    void deleteAll(){
        PhoneRoomDatabase.databaseWriterExecutor.execute(() -> {
            mPhoneDao.deleteAll();
        });
    }

    public void insert(Phone v) {
        PhoneRoomDatabase.databaseWriterExecutor.execute(() -> {
            mPhoneDao.insert(v);
        });
    }

    public void update(Phone v) {
        PhoneRoomDatabase.databaseWriterExecutor.execute(() -> {
            mPhoneDao.update(v);
        });
    }
}
