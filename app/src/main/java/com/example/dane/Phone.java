package com.example.dane;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.Getter;
import lombok.Setter;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Phone")
@Getter
@Setter
public class Phone implements Parcelable {
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
        this.mIndex = mIndex;
        this.mProducer = mProducer;
        this.mModel = mModel;
        this.mVersion = mVersion;
        this.mWeb = mWeb;
    }


    protected Phone(Parcel in) {
        if (in.readByte() == 0) {
            mIndex = null;
        } else {
            mIndex = in.readLong();
        }
        mProducer = in.readString();
        mModel = in.readString();
        mVersion = in.readString();
        mWeb = in.readString();
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (mIndex == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mIndex);
        }
        parcel.writeString(mProducer);
        parcel.writeString(mModel);
        parcel.writeString(mVersion);
        parcel.writeString(mWeb);
    }
}
