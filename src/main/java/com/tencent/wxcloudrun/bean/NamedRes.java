package com.tencent.wxcloudrun.bean;

import java.util.List;

/**
 * @author xp.chen
 */
public class NamedRes {
    /**
     * 姓氏
     */
    private String surname;
    /**
     * 阳历生日
     */
    private String solar;
    /**
     * 农历历生日
     */
    private String lunar;
    /**
     * 民俗生日
     */
    private String folklore;
    /**
     * 详细分析
     */
    private String analyse;
    /**
     * 名字集合
     */
    private List<NamedItem> namedList;
    /**
     * 详细情况
     */
    private String particular;
    /**
     * 金
     */
    private int metal;
    /**
     * 木
     */
    private int wood;
    /**
     * 水
     */
    private int water;
    /**
     * 火
     */
    private int fire;
    /**
     * 土
     */
    private int earth;
    /**
     * 命主生辰八字从弱
     */
    private String contents1;
    /**
     * 八字喜用神
     */
    private String contents2;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSolar() {
        return solar;
    }

    public void setSolar(String solar) {
        this.solar = solar;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getFolklore() {
        return folklore;
    }

    public void setFolklore(String folklore) {
        this.folklore = folklore;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public List<NamedItem> getNamedList() {
        return namedList;
    }

    public void setNamedList(List<NamedItem> namedList) {
        this.namedList = namedList;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public int getEarth() {
        return earth;
    }

    public void setEarth(int earth) {
        this.earth = earth;
    }

    public String getContents1() {
        return contents1;
    }

    public void setContents1(String contents1) {
        this.contents1 = contents1;
    }

    public String getContents2() {
        return contents2;
    }

    public void setContents2(String contents2) {
        this.contents2 = contents2;
    }

    @Override
    public String toString() {
        return "NamedRes{" +
                "surname='" + surname + '\'' +
                ", solar='" + solar + '\'' +
                ", lunar='" + lunar + '\'' +
                ", folklore='" + folklore + '\'' +
                ", analyse='" + analyse + '\'' +
                ", namedList=" + namedList +
                ", particular='" + particular + '\'' +
                ", metal=" + metal +
                ", wood=" + wood +
                ", water=" + water +
                ", fire=" + fire +
                ", earth=" + earth +
                ", contents1='" + contents1 + '\'' +
                ", contents2='" + contents2 + '\'' +
                '}';
    }
}
