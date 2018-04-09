package com.androidtutorialshub.loginregister.model.categoryregist;

import com.google.gson.annotations.SerializedName;

import java.util.List;
@SuppressWarnings("unused")
public class CategoryLogin {
    @SerializedName("data")
    private List<Datanum> mData;
    @SerializedName("message")
    private String mMessage;

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public List<Datanum> getData() {
        return mData;
    }

    public void setData(List<Datanum> data) {
        mData = data;
    }
}
