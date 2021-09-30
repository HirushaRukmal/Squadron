package com.example.squadroncinema;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieRVModal implements Parcelable {

    private String mId;
    private String mName;
    private String mDescription;
    private String mType;
    private String mDuration;
    private String mStartDate;
    private String mEndDate;
    private String mImage;

    public MovieRVModal(){

    }

    public MovieRVModal(String mId, String mName, String mDescription, String mType, String mDuration, String mStartDate, String mEndDate, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mType = mType;
        this.mDuration = mDuration;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mImage = mImage;
    }


    protected MovieRVModal(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mType = in.readString();
        mDuration = in.readString();
        mStartDate = in.readString();
        mEndDate = in.readString();
        mImage = in.readString();
    }

    public static final Creator<MovieRVModal> CREATOR = new Creator<MovieRVModal>() {
        @Override
        public MovieRVModal createFromParcel(Parcel in) {
            return new MovieRVModal(in);
        }

        @Override
        public MovieRVModal[] newArray(int size) {
            return new MovieRVModal[size];
        }
    };

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mType);
        dest.writeString(mDuration);
        dest.writeString(mStartDate);
        dest.writeString(mEndDate);
        dest.writeString(mImage);
    }
}


