package io.ylab.model;

import java.time.LocalDateTime;

import static io.ylab.utils.Utils.formatter;

public class BookingWorkplace {
    private int bookingId;
    private int workplaceNumber;
    private int userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingWorkplace(int workplaceNumber, int userId, String startTime) {
        this.workplaceNumber = workplaceNumber;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
