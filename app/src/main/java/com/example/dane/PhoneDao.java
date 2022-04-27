package com.example.dane;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {

    @Insert
    void insert(Phone phone);

    @Query("DELETE FROM Phone")
    void deleteAll();

    @Update
    void update(Phone phone);

    @Delete
    void delete(Phone phone);

    @Query("SELECT * FROM Phone ORDER BY mProducer ASC")
    LiveData<List<Phone>> getAlpabetizedPhones();
}
