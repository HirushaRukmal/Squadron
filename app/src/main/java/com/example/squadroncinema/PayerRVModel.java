package com.example.squadroncinema;

import android.os.Parcel;
import android.os.Parcelable;

public class PayerRVModel implements Parcelable {
    private String payerID;
    private String pName;
    private String pAddress;
    private String pEmail;
    private String pContact;

    public PayerRVModel() {
    }

    public PayerRVModel(String payerID, String pName, String pAddress, String pEmail, String pContact) {
        this.payerID = payerID;
        this.pName = pName;
        this.pAddress = pAddress;
        this.pEmail = pEmail;
        this.pContact = pContact;
    }

    protected PayerRVModel(Parcel in) {
        payerID = in.readString();
        pName = in.readString();
        pAddress = in.readString();
        pEmail = in.readString();
        pContact = in.readString();
    }

    public static final Creator<PayerRVModel> CREATOR = new Creator<PayerRVModel>() {
        @Override
        public PayerRVModel createFromParcel(Parcel in) {
            return new PayerRVModel(in);
        }

        @Override
        public PayerRVModel[] newArray(int size) {
            return new PayerRVModel[size];
        }
    };

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpContact() {
        return pContact;
    }

    public void setpContact(String pContact) {
        this.pContact = pContact;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(payerID);
        dest.writeString(pName);
        dest.writeString(pAddress);
        dest.writeString(pEmail);
        dest.writeString(pContact);
    }
}
