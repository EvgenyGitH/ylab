package io.ylab.model;

import java.time.LocalDateTime;

import static io.ylab.utils.Utils.formatter;

public class BookingWorkplace {
    private int bookingId;
    private int workplaceNumber;
    private String userLogin;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingWorkplace(int workplaceNumber, String userLogin, String startTime) {
        this.workplaceNumber = workplaceNumber;
        this.userLogin = userLogin;
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getWorkplaceNumber() {
        return workplaceNumber;
    }

    public void setWorkplaceNumber(int workplaceNumber) {
        this.workplaceNumber = workplaceNumber;
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
        return "BookingWorkplace{" +
                "bookingId=" + bookingId +
                ", workplaceNumber=" + workplaceNumber +
                ", userLogin='" + userLogin + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
