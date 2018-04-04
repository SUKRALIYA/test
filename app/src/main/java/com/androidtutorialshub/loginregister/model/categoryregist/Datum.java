package com.androidtutorialshub.loginregister.model.categoryregist;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("cat_full_name")
    private String mCatFullName;
    @SerializedName("cat_short_name")
    private String mCatShortName;

    public String getCatFullName() {
        return mCatFullName;
    }

    public void setCatFullName(String catFullName) {
        mCatFullName = catFullName;
    }

    public String getCatShortName() {
        return mCatShortName;
    }

    public void setCatShortName(String catShortName) {
        mCatShortName = catShortName;
    }

}
