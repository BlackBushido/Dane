package com.example.dane;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository mRepository;
    private final LiveData<List<Phone>> mAllPhones;

    public PhoneViewModel(@NonNull Application application){
        super(application);
        mRepository = new PhoneRepository(application);
        mAllPhones = mRepository.getAllPhones();
    }

    LiveData<List<Phone>> getAllPhones(){
        return mAllPhones;
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void insert(Phone v) {
        mRepository.insert(v);
    }

    public void update(Phone v) {

        mRepository.update(v);
    }

    public void delete(Phone v) {

        mRepository.delete(v);
    }
}
