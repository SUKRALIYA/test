
package com.androidtutorialshub.loginregister.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;



public class Employee {

    @SerializedName("data")
    private List<Data> mData;
    @SerializedName("message")
    private String mMessage;

    public List<Data> getData() {
        return mData;
    }

    public void setData(List<Data> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
