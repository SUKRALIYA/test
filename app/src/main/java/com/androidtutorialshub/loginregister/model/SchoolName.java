
package com.androidtutorialshub.loginregister.model;


import com.google.gson.annotations.SerializedName;


public class SchoolName {

    private String cat_full_name;
    private String cat_short_name;

    public String getCat_full_name() {
        return cat_full_name;
    }

    public void setCat_full_name(String cat_full_name) {
        this.cat_full_name = cat_full_name;
    }

    public String getCat_short_name() {
        return cat_short_name;
    }

    public void setCat_short_name(String cat_short_name) {
        this.cat_short_name = cat_short_name;
    }
}
