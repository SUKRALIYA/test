package com.androidtutorialshub.loginregister.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EmployeeData implements Parcelable {
    @SerializedName("fa_email")
    private String mFaEmail;
    @SerializedName("fa_eventdate")
    private String mFaEventdate;
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
    @SerializedName("fa_dob")
    private String mFaDob;
    @SerializedName("fa_mariatal_status")
    private String mFaMariatalStatus;
    @SerializedName("fa_join_date")
    private String mFaJoinDate;
    @SerializedName("fa_experience")
    private String mFaExperience;
    @SerializedName("fa_qualification")
    private String mFaQualification;
    @SerializedName("fa_subject")
    private String mFaSubject;
    @SerializedName("fa_cetification")
    private String mFaCertification;
    @SerializedName("fa_previous")
    private String mFaPrevious;
    @SerializedName("fa_address_par")
    private String mFaAddressPar;
    @SerializedName("fa_address_curr")
    private String mFaAddressCurr;
    @SerializedName("fa_lavel")
    private String mFaLevel;

    public String getmFaEmail() {
        return mFaEmail;
    }

    public void setmFaEmail(String mFaEmail) {
        this.mFaEmail = mFaEmail;
    }

    public String getmFaEventdate() {
        return mFaEventdate;
    }

    public void setmFaEventdate(String mFaEventdate) {
        this.mFaEventdate = mFaEventdate;
    }

    public String getmFaName() {
        return mFaName;
    }

    public void setmFaName(String mFaName) {
        this.mFaName = mFaName;
    }

    public String getmFaPhone() {
        return mFaPhone;
    }

    public void setmFaPhone(String mFaPhone) {
        this.mFaPhone = mFaPhone;
    }

    public String getmFaStatus() {
        return mFaStatus;
    }

    public void setmFaStatus(String mFaStatus) {
        this.mFaStatus = mFaStatus;
    }

    public String getmFaUid() {
        return mFaUid;
    }

    public void setmFaUid(String mFaUid) {
        this.mFaUid = mFaUid;
    }

    public String getmLastUpdateDate() {
        return mLastUpdateDate;
    }

    public void setmLastUpdateDate(String mLastUpdateDate) {
        this.mLastUpdateDate = mLastUpdateDate;
    }

    public String getmSchId() {
        return mSchId;
    }

    public void setmSchId(String mSchId) {
        this.mSchId = mSchId;
    }

    public String getmFaDob() {
        return mFaDob;
    }

    public void setmFaDob(String mFaDob) {
        this.mFaDob = mFaDob;
    }

    public String getmFaMariatalStatus() {
        return mFaMariatalStatus;
    }

    public void setmFaMariatalStatus(String mFaMariatalStatus) {
        this.mFaMariatalStatus = mFaMariatalStatus;
    }

    public String getmFaJoinDate() {
        return mFaJoinDate;
    }

    public void setmFaJoinDate(String mFaJoinDate) {
        this.mFaJoinDate = mFaJoinDate;
    }

    public String getmFaExperience() {
        return mFaExperience;
    }

    public void setmFaExperience(String mFaExperience) {
        this.mFaExperience = mFaExperience;
    }

    public String getmFaQualification() {
        return mFaQualification;
    }

    public void setmFaQualification(String mFaQualification) {
        this.mFaQualification = mFaQualification;
    }

    public String getmFaSubject() {
        return mFaSubject;
    }

    public void setmFaSubject(String mFaSubject) {
        this.mFaSubject = mFaSubject;
    }

    public String getmFaCertification() {
        return mFaCertification;
    }

    public void setmFaCertification(String mFaCertification) {
        this.mFaCertification = mFaCertification;
    }

    public String getmFaPrevious() {
        return mFaPrevious;
    }

    public void setmFaPrevious(String mFaPrevious) {
        this.mFaPrevious = mFaPrevious;
    }

    public String getmFaAddressPar() {
        return mFaAddressPar;
    }

    public void setmFaAddressPar(String mFaAddressPar) {
        this.mFaAddressPar = mFaAddressPar;
    }

    public String getmFaAddressCurr() {
        return mFaAddressCurr;
    }

    public void setmFaAddressCurr(String mFaAddressCurr) {
        this.mFaAddressCurr = mFaAddressCurr;
    }

    public String getmFaLevel() {
        return mFaLevel;
    }

    public void setmFaLevel(String mFaLevel) {
        this.mFaLevel = mFaLevel;
    }
    @Override
    public String toString() {
        return "EmployeeData{" +
                "mFaEmail='" + mFaEmail + '\'' +
                ", mFaEventdate='" + mFaEventdate + '\'' +
                ", mFaAddressCurr='" + mFaAddressCurr + '\'' +
                ", mFaAddressPar='" + mFaAddressPar + '\'' +
                ", mFaCertification='" + mFaCertification + '\'' +
                ", mFaDob='" + mFaDob + '\'' +
                ", mFaExperience='" + mFaExperience + '\'' +
                ", mFaJoinDate='" + mFaJoinDate + '\'' +
                ", mFaLevel='" + mFaLevel + '\'' +
                ", mFaMariatalStatus='" + mFaMariatalStatus + '\'' +
                ", mFaSubject='" + mFaSubject + '\'' +
                ", mFaQualification='" + mFaQualification + '\'' +
                ", mFaPrevious='" + mFaPrevious + '\'' +
                ", mFaName='" + mFaName + '\'' +
                ", mFaPhone='" + mFaPhone + '\'' +
                ", mFaStatus='" + mFaStatus + '\'' +
                ", mFaUid='" + mFaUid + '\'' +
                ", mLastUpdateDate='" + mLastUpdateDate + '\'' +
                ", mSchId='" + mSchId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    } public EmployeeData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mFaEmail);
        dest.writeString(this.mFaEventdate);
        dest.writeString(this.mFaAddressCurr);
        dest.writeString(this.mFaAddressPar);
        dest.writeString(this.mFaCertification);
        dest.writeString(this.mFaDob);
        dest.writeString(this.mFaExperience);
        dest.writeString(this.mFaName);
        dest.writeString(this.mFaPhone);
        dest.writeString(this.mFaStatus);
        dest.writeString(this.mFaUid);
        dest.writeString(this.mLastUpdateDate);
        dest.writeString(this.mSchId);
        dest.writeString(this.mFaJoinDate);
        dest.writeString(this.mFaLevel);
        dest.writeString(this.mFaMariatalStatus);
        dest.writeString(this.mFaPrevious);
        dest.writeString(this.mFaSubject);
        dest.writeString(this.mFaQualification);
    }
    protected EmployeeData(Parcel in) {
        this.mFaEmail = in.readString();
        this.mFaEventdate = in.readString();
        this.mFaAddressCurr = in.readString();
        this.mFaAddressCurr = in.readString();
        this.mFaName = in.readString();
        this.mFaPhone = in.readString();
        this.mFaStatus = in.readString();
        this.mFaUid = in.readString();
        this.mLastUpdateDate = in.readString();
        this.mSchId = in.readString();
        this.mFaCertification=in.readString();
        this.mFaDob=in.readString();
        this.mFaExperience=in.readString();
        this.mFaJoinDate=in.readString();
        this.mFaLevel=in.readString();
        this.mFaMariatalStatus=in.readString();
        this.mFaPrevious=in.readString();
        this.mFaSubject=in.readString();
        this.mFaQualification=in.readString();


    }
    public static final Parcelable.Creator<EmployeeData> CREATOR = new Parcelable.Creator<EmployeeData>() {
        @Override
        public EmployeeData createFromParcel(Parcel source) {
            return new EmployeeData(source);
        }

        @Override
        public EmployeeData[] newArray(int size) {
            return new EmployeeData[size];
        }
    };

}
