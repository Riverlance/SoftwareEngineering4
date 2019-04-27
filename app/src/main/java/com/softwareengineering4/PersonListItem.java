package com.softwareengineering4;

public class PersonListItem {
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
        return String.format("%s - %s - %s", locationStreet, locationCity, locationState);
    }
}
