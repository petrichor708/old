package com.lala.yanglao.model;

import java.util.Date;

public class Old {
    Long id;
    String name;
    String age;
    String sex;
    Long rid;
    Long nid;
    int status;
    String number;
    String address;
    String createTime;
    String describe;
    String roomNumber;
    String nurseId;
    String repayDate;
    String updateCostTime;
    double allCharge;
    double charge;
    String nurseName;
    String nurseNumber;

    @Override
    public String toString() {
        return "Old{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", rid=" + rid +
                ", nid=" + nid +
                ", status=" + status +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", createTime='" + createTime + '\'' +
                ", describe='" + describe + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", nurseId='" + nurseId + '\'' +
                ", repayDate='" + repayDate + '\'' +
                ", updateCostTime='" + updateCostTime + '\'' +
                ", allCharge=" + allCharge +
                ", charge=" + charge +
                ", nurseName='" + nurseName + '\'' +
                ", nurseNumber='" + nurseNumber + '\'' +
                '}';
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getUpdateCostTime() {
        return updateCostTime;
    }

    public void setUpdateCostTime(String updateCostTime) {
        this.updateCostTime = updateCostTime;
    }

    public double getAllCharge() {
        return allCharge;
    }

    public void setAllCharge(double allCharge) {
        this.allCharge = allCharge;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public String getNurseNumber() {
        return nurseNumber;
    }

    public void setNurseNumber(String nurseNumber) {
        this.nurseNumber = nurseNumber;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getCondition() {
        return status;
    }

    public void setCondition(int condition) {
        this.status = condition;
    }
}
