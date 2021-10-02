package com.example.squadroncinema;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentRVModel implements Parcelable {

    private String pID;
    private String paymentID;
    private String userID;
    private String amount;
    private String date;

    public PaymentRVModel() {
    }

    public PaymentRVModel(String pID, String paymentID, String userID, String amount, String date) {
        this.pID = pID;
        this.paymentID = paymentID;
        this.userID = userID;
        this.amount = amount;
        this.date = date;
    }

    protected PaymentRVModel(Parcel in) {
        pID = in.readString();
        paymentID = in.readString();
        userID = in.readString();
        amount = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pID);
        dest.writeString(paymentID);
        dest.writeString(userID);
        dest.writeString(amount);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PaymentRVModel> CREATOR = new Creator<PaymentRVModel>() {
        @Override
        public PaymentRVModel createFromParcel(Parcel in) {
            return new PaymentRVModel(in);
        }

        @Override
        public PaymentRVModel[] newArray(int size) {
            return new PaymentRVModel[size];
        }
    };

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
