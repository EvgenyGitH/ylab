package io.ylab.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String roomName;
    private String description;
    List<BookingRoom> reservRoomList = new ArrayList<>();

    public Room(String roomName, String description) {
        this.roomName = roomName;
        this.description = description;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookingRoom> getReservRoomList() {
        return reservRoomList;
    }

    public void setReservRoomList(List<BookingRoom> reservRoomList) {
        this.reservRoomList = reservRoomList;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
