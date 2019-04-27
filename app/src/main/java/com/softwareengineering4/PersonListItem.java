package com.softwareengineering4;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.text.WordUtils;

public class PersonListItem implements Parcelable {
    public static final short GENDER_FEMALE = 0;
    public static final short GENDER_MALE = 1;

    String nameTitle;
    String nameFirst;
    String nameLast;
    String username;
    short age;

    short gender = GENDER_MALE;

    String locationStreet = "";
    String locationCity = "";
    String locationState = "";

    String email = "";
    String phone = "";

    String pictureLargeURL = "";
    String pictureMediumURL = "";
    String pictureThumbnailURL = "";

    public PersonListItem(String nameTitle, String nameFirst, String nameLast, String username, short age) {
        this.nameTitle = nameTitle;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.username = username;
        this.age = age;
    }

    protected PersonListItem(Parcel in) {
        nameTitle = in.readString();
        nameFirst = in.readString();
        nameLast = in.readString();
        username = in.readString();
        age = (short) in.readInt();
        gender = (short) in.readInt();
        locationStreet = in.readString();
        locationCity = in.readString();
        locationState = in.readString();
        email = in.readString();
        phone = in.readString();
        pictureLargeURL = in.readString();
        pictureMediumURL = in.readString();
        pictureThumbnailURL = in.readString();
    }

    public static final Creator<PersonListItem> CREATOR = new Creator<PersonListItem>() {
        @Override
        public PersonListItem createFromParcel(Parcel in) {
            return new PersonListItem(in);
        }

        @Override
        public PersonListItem[] newArray(int size) {
            return new PersonListItem[size];
        }
    };

    public String getName(boolean includeTitle) {
        return includeTitle ? String.format("%s %s %s", nameTitle, nameFirst, nameLast) : String.format("%s %s", nameFirst, nameLast);
    }
    public String getName() {
        return getName(false);
    }

    public String getGenderToString() {
        return gender == GENDER_MALE ? "Masculino" : "Feminino";
    }
    public char getGenderToChar() {
        return gender == GENDER_MALE ? 'M' : 'F';
    }

    public String getLocation() {
        return WordUtils.capitalize(String.format("%s - %s - %s", locationStreet, locationCity, locationState));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameTitle);
        parcel.writeString(nameFirst);
        parcel.writeString(nameLast);
        parcel.writeString(username);
        parcel.writeInt((int) age);
        parcel.writeInt((int) gender);
        parcel.writeString(locationStreet);
        parcel.writeString(locationCity);
        parcel.writeString(locationState);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(pictureLargeURL);
        parcel.writeString(pictureMediumURL);
        parcel.writeString(pictureThumbnailURL);
    }
}
