package com.example.squadroncinema;


import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String Name;
    private String Description;
    private String price;

    public Food() {}

    public Food(String name, String description, String price) {
        Name = name;
        Description = description;
        this.price = price;
    }

    public Food(String name, String price) {
        Name = name;
        this.price = price;
    }

    protected Food(Parcel in) {
        Name = in.readString();
        Description = in.readString();
        price = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(price);
        dest.writeString(Description);
        dest.writeString(Name);
    }
}
