package com.lala.yanglao.model;

import java.util.Date;

public class Cost {
    Long id;
    Long oldId;
    String name;
    double nCost;
    double rCost;
    double extraCharges;
    double allCost;
    int days;
    String day;
    String createTime;
    double allCharge;

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", oldId=" + oldId +
                ", name='" + name + '\'' +
                ", nCost=" + nCost +
                ", rCost=" + rCost +
                ", extraCharges=" + extraCharges +
                ", allCost=" + allCost +
                ", days=" + days +
                ", day='" + day + '\'' +
                ", createTime='" + createTime + '\'' +
                ", allCharge=" + allCharge +
                ", charge=" + charge +
                '}';
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    double charge;



    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getnCost() {
        return nCost;
    }

    public void setnCost(double nCost) {
        this.nCost = nCost;
    }

    public double getrCost() {
        return rCost;
    }

    public void setrCost(double rCost) {
        this.rCost = rCost;
    }

    public double getExtraCharges() {
        return extraCharges;
    }

    public void setExtraCharges(double extraCharges) {
        this.extraCharges = extraCharges;
    }

    public double getAllCost() {
        return allCost;
    }

    public void setAllCost(double allCost) {
        this.allCost = allCost;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getAllCharge() {
        return allCharge;
    }

    public void setAllCharge(double allCharge) {
        this.allCharge = allCharge;
    }
}
