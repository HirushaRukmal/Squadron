package com.example.squadroncinema;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieTicketBookingRVModal implements Parcelable {

    private String ticketId;
    private String mName;
    private String mDescription;
    private String mDuration;
    private String mImage;
    private String bookedDate;
    private String seatNumber;
    private String status;
    private String ticketCount;
    private String ticketType;
    private String ticketPrice;
    private String userId;

    public MovieTicketBookingRVModal(){

    }

    public MovieTicketBookingRVModal(String ticketId, String mName, String mDescription, String mDuration, String mImage, String bookedDate, String seatNumber, String status, String ticketCount, String ticketType, String ticketPrice, String userId) {
        this.ticketId = ticketId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDuration = mDuration;
        this.mImage = mImage;
        this.bookedDate = bookedDate;
        this.seatNumber = seatNumber;
        this.status = status;
        this.ticketCount = ticketCount;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.userId = userId;
    }

    protected MovieTicketBookingRVModal(Parcel in) {
        ticketId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mDuration = in.readString();
        mImage = in.readString();
        bookedDate = in.readString();
        seatNumber = in.readString();
        status = in.readString();
        ticketCount = in.readString();
        ticketType = in.readString();
        ticketPrice = in.readString();
        userId = in.readString();
    }

    public static final Creator<MovieTicketBookingRVModal> CREATOR = new Creator<MovieTicketBookingRVModal>() {
        @Override
        public MovieTicketBookingRVModal createFromParcel(Parcel in) {
            return new MovieTicketBookingRVModal(in);
        }

        @Override
        public MovieTicketBookingRVModal[] newArray(int size) {
            return new MovieTicketBookingRVModal[size];
        }
    };

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
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

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ticketId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mDuration);
        dest.writeString(mImage);
        dest.writeString(bookedDate);
        dest.writeString(seatNumber);
        dest.writeString(status);
        dest.writeString(ticketCount);
        dest.writeString(ticketType);
        dest.writeString(ticketPrice);
        dest.writeString(userId);
    }
}
