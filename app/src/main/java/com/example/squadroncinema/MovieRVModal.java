package com.example.squadroncinema;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieRVModal implements Parcelable {

    private String mId;
    private String mName;
    private String mType;
    private String mDuration;
    private String mImage;
    private String mDescription;
    private String mStartDate;
    private String mEndDate;


    public MovieRVModal(){

    }

    public MovieRVModal(String mId, String mName, String mType, String mDuration, String mImage, String mDescription, String mStartDate, String mEndDate) {
        this.mId = mId;
        this.mName = mName;
        this.mType = mType;
        this.mDuration = mDuration;
        this.mImage = mImage;
        this.mDescription = mDescription;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;

    }


    protected MovieRVModal(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mType = in.readString();
        mDuration = in.readString();
        mImage = in.readString();
        mDescription = in.readString();
        mStartDate = in.readString();
        mEndDate = in.readString();

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

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mType);
        dest.writeString(mDuration);
        dest.writeString(mImage);
        dest.writeString(mDescription);
        dest.writeString(mStartDate);
        dest.writeString(mEndDate);

    }
}


