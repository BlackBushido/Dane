package com.example.dane;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Phone")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long mIndex;

    @NonNull
    private String mProducer;

    @NonNull
    private String mModel;

    @NonNull
    private String mVersion;

    @NonNull
    private String mWeb;

    public Phone(Long mIndex, @NonNull String mProducer, @NonNull String mModel, @NonNull String mVersion, @NonNull String mWeb) {
        this.mIndex = mIndex;
        this.mProducer = mProducer;
        this.mModel = mModel;
        this.mVersion = mVersion;
        this.mWeb = mWeb;
    }

    public long getmIndex() {
        return mIndex;
    }

    public void setmIndex(long mIndex) {
        this.mIndex = mIndex;
    }

    @NonNull
    public String getmProducer() {
        return mProducer;
    }

    public void setmProducer(@NonNull String mProducer) {
        this.mProducer = mProducer;
    }

    @NonNull
    public String getmModel() {
        return mModel;
    }

    public void setmModel(@NonNull String mModel) {
        this.mModel = mModel;
    }

    @NonNull
    public String getmVersion() {
        return mVersion;
    }

    public void setmVersion(@NonNull String mVersion) {
        this.mVersion = mVersion;
    }

    @NonNull
    public String getmWeb() {
        return mWeb;
    }

    public void setmWeb(@NonNull String mWeb) {
        this.mWeb = mWeb;
    }
}
