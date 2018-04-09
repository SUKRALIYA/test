package com.androidtutorialshub.loginregister.model.categoryregist;

import com.google.gson.annotations.SerializedName;

public class Datanum {

    @SerializedName("login_type_name")
    private String mLoginTypeName;

    public String getmLoginTypeName() {
        return mLoginTypeName;
    }

    public void setmLoginTypeName(String mLoginTypeName) {
        this.mLoginTypeName = mLoginTypeName;
    }
}
