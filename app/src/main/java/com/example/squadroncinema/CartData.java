package com.example.squadroncinema;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartData implements Parcelable {

    private Food cardtData;
    private int count;

    public CartData(Food cardtData, int count) {
        this.cardtData = cardtData;
        this.count = count;
    }

    protected CartData(Parcel in) {
        count = in.readInt();
    }

    public static final Creator<CartData> CREATOR = new Creator<CartData>() {
        @Override
        public CartData createFromParcel(Parcel in) {
            return new CartData(in);
        }

        @Override
        public CartData[] newArray(int size) {
            return new CartData[size];
        }
    };

    public Food getCardtData() {
        return cardtData;
    }

    public void setCardtData(Food cardtData) {
        this.cardtData = cardtData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

       // dest.writePersistableBundle(cardtData);
        dest.writeInt(count);
    }
}

