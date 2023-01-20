package com.alex.hollydayappv1.ui;

public class UsersItem {

    String holidayID;
    String holidayName;
    String holidayDestination;
    String holidayCountry;

    public UsersItem() {

    }

    public UsersItem(String holidayID, String holidayName, String holidayDestination, String holidayCountry) {
        this.holidayID = holidayID;
        this.holidayName = holidayName;
        this.holidayDestination = holidayDestination;
        this.holidayCountry = holidayCountry;
    }

    public String getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(String holidayID) {
        this.holidayID = holidayID;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHolidayDestination() {
        return holidayDestination;
    }

    public void setHolidayDestination(String holidayDestination) {
        this.holidayDestination = holidayDestination;
    }

    public String getHolidayCountry() {
        return holidayCountry;
    }

    public void setHolidayCountry(String holidayCountry) {
        this.holidayCountry = holidayCountry;
    }
}
