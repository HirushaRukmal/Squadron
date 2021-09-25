package com.example.squadroncinema;

import android.os.Parcel;
import android.os.Parcelable;

public class ShowTicketRVModel implements Parcelable {

    //variables
    private String  bookedDate;
    private String  movieDescription;
    private String  movieDuration;
    private String  movieImageLink;
    private String  movieTitle;
    private String  seatNumbers;
    private String  status;
    private String  ticketCount;
    private String  ticketType;
    private String  tiketsTotal;
    private String  userid;
    private String  username;

    //default empty  constructer (For the fire base it is required)
    public ShowTicketRVModel() {

    }

    public ShowTicketRVModel(String bookedDate, String movieDescription, String movieDuration, String movieImageLink, String movieTitle, String seatNumbers, String status, String ticketCount, String ticketType, String tiketsTotal, String userid, String username) {
        this.bookedDate = bookedDate;
        this.movieDescription = movieDescription;
        this.movieDuration = movieDuration;
        this.movieImageLink = movieImageLink;
        this.movieTitle = movieTitle;
        this.seatNumbers = seatNumbers;
        this.status = status;
        this.ticketCount = ticketCount;
        this.ticketType = ticketType;
        this.tiketsTotal = tiketsTotal;
        this.userid = userid;
        this.username = username;
    }

    protected ShowTicketRVModel(Parcel in) {
        bookedDate = in.readString();
        movieDescription = in.readString();
        movieDuration = in.readString();
        movieImageLink = in.readString();
        movieTitle = in.readString();
        seatNumbers = in.readString();
        status = in.readString();
        ticketCount = in.readString();
        ticketType = in.readString();
        tiketsTotal = in.readString();
        userid = in.readString();
        username = in.readString();
    }

    public static final Creator<ShowTicketRVModel> CREATOR = new Creator<ShowTicketRVModel>() {
        @Override
        public ShowTicketRVModel createFromParcel(Parcel in) {
            return new ShowTicketRVModel(in);
        }

        @Override
        public ShowTicketRVModel[] newArray(int size) {
            return new ShowTicketRVModel[size];
        }
    };

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieImageLink() {
        return movieImageLink;
    }

    public void setMovieImageLink(String movieImageLink) {
        this.movieImageLink = movieImageLink;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
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

    public String getTiketsTotal() {
        return tiketsTotal;
    }

    public void setTiketsTotal(String tiketsTotal) {
        this.tiketsTotal = tiketsTotal;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookedDate);
        parcel.writeString(movieDescription);
        parcel.writeString(movieDuration);
        parcel.writeString(movieImageLink);
        parcel.writeString(movieTitle);
        parcel.writeString(seatNumbers);
        parcel.writeString(status);
        parcel.writeString(ticketCount);
        parcel.writeString(ticketType);
        parcel.writeString(tiketsTotal);
        parcel.writeString(userid);
        parcel.writeString(username);
    }
}


