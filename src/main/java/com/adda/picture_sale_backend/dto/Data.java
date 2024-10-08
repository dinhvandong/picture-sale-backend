package com.adda.picture_sale_backend.dto;

import java.util.List;

public class Data {

    private String day;
    private List<RoomAvailability> roomAvai;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<RoomAvailability> getRoomAvai() {
        return roomAvai;
    }

    public void setRoomAvai(List<RoomAvailability> roomAvai) {
        this.roomAvai = roomAvai;
    }
}
