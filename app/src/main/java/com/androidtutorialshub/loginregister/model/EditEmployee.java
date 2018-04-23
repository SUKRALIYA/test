package com.androidtutorialshub.loginregister.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditEmployee implements Parcelable {

    @SerializedName("data")
    private List<EmployeeData> mEmloyeeData;
    @SerializedName("message")
    private String mMessage;

    public List<EmployeeData> getmEmloyeeData() {
        return mEmloyeeData;
    }

    public void setmEmloyeeData(List<EmployeeData> mEmloyeeData) {
        this.mEmloyeeData = mEmloyeeData;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mEmloyeeData);
        dest.writeString(this.mMessage);
    }

    public EditEmployee() {
    }

    protected EditEmployee(Parcel in) {
        this.mEmloyeeData = in.createTypedArrayList(EmployeeData.CREATOR);
        this.mMessage = in.readString();
    }

    public static final Parcelable.Creator<EditEmployee> CREATOR = new Parcelable.Creator<EditEmployee>() {
        @Override
        public EditEmployee createFromParcel(Parcel source) {
            return new EditEmployee(source);
        }

        @Override
        public EditEmployee[] newArray(int size) {
            return new EditEmployee[size];
        }
    };
}