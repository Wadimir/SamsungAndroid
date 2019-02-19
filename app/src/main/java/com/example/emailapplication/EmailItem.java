package com.example.emailapplication;

import android.os.Parcel;
import android.os.Parcelable;


public class EmailItem implements Parcelable {
    public static final Creator<EmailItem> CREATOR = new Creator<EmailItem>() {
        @Override
        public EmailItem createFromParcel(Parcel in) {
            return new EmailItem(in);
        }

        @Override
        public EmailItem[] newArray(int size) {
            return new EmailItem[size];
        }
    };
    private String userImg;
    private String title;
    private String subtitle;
    private String content;
    private String data;

    public EmailItem(String userImg, String title, String subtitle, String content, String data) {
        this.userImg = userImg;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.data = data;
    }

    protected EmailItem(Parcel in) {
        userImg = in.readString();
        title = in.readString();
        subtitle = in.readString();
        content = in.readString();
        data = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userImg);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(content);
        dest.writeString(data);
    }

    public String getUserImg() {
        return userImg;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent() {
        return content;
    }

    public String getData() {
        return data;
    }
}