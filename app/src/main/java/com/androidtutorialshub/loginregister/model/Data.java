
package com.androidtutorialshub.loginregister.model;


import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("fa_email")
    private String mFaEmail;
    @SerializedName("fa_eventdate")
    private String mFaEventdate;
    @SerializedName("fa_map_class")
    private String mFaMapClass;
    @SerializedName("fa_map_section")
    private String mFaMapSection;
    @SerializedName("fa_name")
    private String mFaName;
    @SerializedName("fa_phone")
    private String mFaPhone;
    @SerializedName("fa_status")
    private String mFaStatus;
    @SerializedName("fa_uid")
    private String mFaUid;
    @SerializedName("last_update_date")
    private String mLastUpdateDate;
    @SerializedName("sch_id")
    private String mSchId;

    public String getFaEmail() {
        return mFaEmail;
    }

    public void setFaEmail(String faEmail) {
        mFaEmail = faEmail;
    }

    public String getFaEventdate() {
        return mFaEventdate;
    }

    public void setFaEventdate(String faEventdate) {
        mFaEventdate = faEventdate;
    }

    public String getFaMapClass() {
        return mFaMapClass;
    }

    public void setFaMapClass(String faMapClass) {
        mFaMapClass = faMapClass;
    }

    public String getFaMapSection() {
        return mFaMapSection;
    }

    public void setFaMapSection(String faMapSection) {
        mFaMapSection = faMapSection;
    }

    public String getFaName() {
        return mFaName;
    }

    public void setFaName(String faName) {
        mFaName = faName;
    }

    public String getFaPhone() {
        return mFaPhone;
    }

    public void setFaPhone(String faPhone) {
        mFaPhone = faPhone;
    }

    public String getFaStatus() {
        return mFaStatus;
    }

    public void setFaStatus(String faStatus) {
        mFaStatus = faStatus;
    }

    public String getFaUid() {
        return mFaUid;
    }

    public void setFaUid(String faUid) {
        mFaUid = faUid;
    }

    public String getLastUpdateDate() {
        return mLastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        mLastUpdateDate = lastUpdateDate;
    }

    public String getSchId() {
        return mSchId;
    }

    public void setSchId(String schId) {
        mSchId = schId;
    }


    @Override
    public String toString() {
        return "Data{" +
                "mFaEmail='" + mFaEmail + '\'' +
                ", mFaEventdate='" + mFaEventdate + '\'' +
                ", mFaMapClass='" + mFaMapClass + '\'' +
                ", mFaMapSection='" + mFaMapSection + '\'' +
                ", mFaName='" + mFaName + '\'' +
                ", mFaPhone='" + mFaPhone + '\'' +
                ", mFaStatus='" + mFaStatus + '\'' +
                ", mFaUid='" + mFaUid + '\'' +
                ", mLastUpdateDate='" + mLastUpdateDate + '\'' +
                ", mSchId='" + mSchId + '\'' +
                '}';
    }
}
