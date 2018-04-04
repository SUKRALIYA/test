
package com.androidtutorialshub.loginregister.model.categoryregist;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class CategoryRegistration {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("message")
    private String mMessage;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
