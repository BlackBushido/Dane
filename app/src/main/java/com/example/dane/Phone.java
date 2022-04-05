package com.example.dane;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Phone")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Long mIndex;

    @NonNull
    private String mProducer;

    @NonNull
    private String mModel;

    @NonNull
    private String mVersion;

    @NonNull
    private String mWeb;

    public Phone(Long mIndex, @NonNull String mProducer, @NonNull String mModel, @NonNull String mVersion, @NonNull String mWeb) {
        this.mProducer = mProducer;
        this.mModel = mModel;
        this.mVersion = mVersion;
        this.mWeb = mWeb;
    }

    public Long getMIndex() {
        return mIndex;
    }

    public void setMIndex(Long mIndex) {
        this.mIndex = mIndex;
    }

    @NonNull
    public String getMProducer() {
        return mProducer;
    }

    public void setMProducer(@NonNull String mProducer) {
        this.mProducer = mProducer;
    }

    @NonNull
    public String getMModel() {
        return mModel;
    }

    public void setMModel(@NonNull String mModel) {
        this.mModel = mModel;
    }

    @NonNull
    public String getMVersion() {
        return mVersion;
    }

    public void setMVersion(@NonNull String mVersion) {
        this.mVersion = mVersion;
    }

    @NonNull
    public String getMWeb() {
        return mWeb;
    }

    public void setMWeb(@NonNull String mWeb) {
        this.mWeb = mWeb;
    }
}
