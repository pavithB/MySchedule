package com.pavithbuddhima.myschedule;



/**
 * Created by Pavith Buddhima on 4/7/2017.
 */

public class Appoinment {

    private int id;

    private String date;
    private String time;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private String title;
    private String discription;


    private double mathTime;


    public Appoinment(String title, String discription, String date, String time, double mathTime) {

        this.title = title;
        this.discription = discription;
        this.date = date;
        this.time = time;
        this.mathTime = mathTime;

    }


    public double getMathTime() {
        return mathTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
