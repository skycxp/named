package com.tencent.wxcloudrun.bean;

/**
 * @author xp.chen
 */
public class NamedReq {
    /**
     * 姓氏
     */
    private String surname;
    /**
     * 0 男，1 女
     */
    private int gender;
    /**
     * 年
     */
    private int year;
    /**
     * 月
     */
    private int month;
    /**
     * 日
     */
    private int day;
    /**
     * 时
     */
    private int hour;
    /**
     * 分
     */
    private int min;
    /**
     * 秒
     */
    private int sec;
    /**
     * 1 小名, 2 两字, 3 三字
     */
    private int type;
    /**
     * 固定字
     */
    private String fixed;
    /**
     * 2 中间字, 3 最后字
     */
    private int fixedType;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public int getFixedType() {
        return fixedType;
    }

    public void setFixedType(int fixedType) {
        this.fixedType = fixedType;
    }
}
