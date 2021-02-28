package com.lala.yanglao.vo;

public class RoomQuery {
    String roomType;
    boolean checkbox;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public RoomQuery() {
    }

    public RoomQuery(String roomTypes, boolean checkbox) {
        this.roomType = roomTypes;
        this.checkbox = checkbox;
    }
}
