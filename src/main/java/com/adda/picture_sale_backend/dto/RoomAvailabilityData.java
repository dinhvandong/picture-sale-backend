package com.adda.picture_sale_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoomAvailabilityData {

    @JsonProperty("day")
    private String day;

    @JsonProperty("roomAvai")
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