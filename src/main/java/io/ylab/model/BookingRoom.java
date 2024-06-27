package io.ylab.model;

import java.time.LocalDateTime;

import static io.ylab.utils.Utils.formatter;

public class BookingRoom {
    private int bookingId;
    private String roomName;
    private String userLogin;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingRoom(String roomName, String userLogin, String startTime) {
        this.roomName = roomName;
        this.userLogin = userLogin;
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BookingRoom{" +
                "bookingId=" + bookingId +
                ", roomName='" + roomName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
