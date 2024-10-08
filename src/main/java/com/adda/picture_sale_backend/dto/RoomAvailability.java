package com.adda.picture_sale_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomAvailability {

    @JsonProperty("idRoom")
    private String idRoom;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("qty")
    private int quantity;

    @JsonProperty("type")
    private String type;

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
