package io.ylab.service;

import io.ylab.model.BookingRoom;
import io.ylab.model.BookingWorkplace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private int id = 1;

    List<BookingRoom> bookingRoomList = new ArrayList<>();
    List<BookingWorkplace> bookingWorkplaceList = new ArrayList<>();


    protected int creatId() {
        return id++;
    }

    public void createBookingWorkplace(BookingWorkplace bookingWorkplace) {
        if (!isWorkplaceTimeFree(bookingWorkplace)) {
            System.out.println("Выберите другое время");
        } else {
            bookingWorkplace.setBookingId(creatId());
            bookingWorkplace.setEndTime(bookingWorkplace.getStartTime().plusMinutes(59L));
            bookingWorkplaceList.add(bookingWorkplace);
            System.out.println("Успешно зарегистрировано. Номер бронирования: " + bookingWorkplace.getBookingId());
        }
    }
    public void getListBookingWorkplace() {
        List<BookingWorkplace> sortList = bookingWorkplaceList.stream()
                .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());

        System.out.println(sortList);
    }
    public void updateBookingWorkplace(int bookingId, BookingWorkplace bookingWorkplace) {
        if (!isWorkplaceTimeFree(bookingWorkplace)) {
            System.out.println("Выберите другое время");
        } else {
            BookingWorkplace bookingInList = getBookingWorkplace(bookingId);
            int indexBooking = bookingWorkplaceList.indexOf(bookingInList);
            bookingWorkplace.setEndTime(bookingWorkplace.getStartTime().plusMinutes(59L));
            bookingWorkplaceList.set(indexBooking, bookingWorkplace);
            System.out.println("Изменения внесены. Номер бронирования: " + bookingWorkplace.getBookingId());
        }
    }
    public void deleteBookingWorkplaceById(int bookingId){
        if(isExistBookingWorkplaceById(bookingId)){
            BookingWorkplace bookingInList = getBookingWorkplace(bookingId);
            int indexBooking = bookingWorkplaceList.indexOf(bookingInList);
            bookingWorkplaceList.remove(indexBooking);
            System.out.println("Бронирование отменено: " + bookingId);
        }else {
            System.out.println("Бронирование не найдено");
        }
    }


    public void createBookingRoom(BookingRoom bookingRoom) {
        if (!isRoomTimeFree(bookingRoom)) {
            System.out.println("Выберите другое время");
        } else {
            bookingRoom.setBookingId(creatId());
            bookingRoom.setEndTime(bookingRoom.getStartTime().plusMinutes(59L));
            bookingRoomList.add(bookingRoom);
            System.out.println("Успешно зарегистрировано. Номер бронирования: " + bookingRoom.getBookingId());
        }
    }

    public void getListBookingRoom() {
        List<BookingRoom> sortList = bookingRoomList.stream()
                .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        System.out.println(sortList);
    }
    public void updateBookingRoom(int bookingId, BookingRoom bookingRoom) {
        if (!isRoomTimeFree(bookingRoom)) {
            System.out.println("Выберите другое время");
        } else {
            BookingWorkplace bookingInList = getBookingWorkplace(bookingId);
            int indexBooking = bookingRoomList.indexOf(bookingInList);
            bookingRoom.setEndTime(bookingRoom.getStartTime().plusMinutes(59L));
            bookingRoomList.set(indexBooking, bookingRoom);
            System.out.println("Изменения внесены. Номер бронирования: " + bookingRoom.getBookingId());
        }
    }
    public void deleteBookingRoomById(int bookingId){
        if(isExistBookingRoomById(bookingId)){
            BookingRoom bookingInList = getBookingRoom(bookingId);
            int indexBooking = bookingRoomList.indexOf(bookingInList);
            bookingRoomList.remove(indexBooking);
            System.out.println("Бронирование отменено: " + bookingId);
        }else {
            System.out.println("Бронирование не найдено");
        }
    }

    public boolean isWorkplaceTimeFree(BookingWorkplace bookingWorkplace) {
        boolean result = true;
        LocalDateTime startTime = bookingWorkplace.getStartTime();
        List<BookingWorkplace> sortByWorkplace = bookingWorkplaceList.stream().filter(booking -> booking.getWorkplaceNumber() == bookingWorkplace.getWorkplaceNumber()).toList();
        for (BookingWorkplace bookingWorkplaceInList : sortByWorkplace) {
            if (bookingWorkplaceInList.getStartTime().equals(startTime)) {
                result = false;
                System.out.println("Указанное время занято");
                break;
            } else if (bookingWorkplaceInList.getStartTime().isBefore(startTime) && bookingWorkplaceInList.getEndTime().isAfter(startTime)) {
                result = false;
                System.out.println("Указанное время занято");
                break;
            }
        }
        return result;
    }
    public boolean isRoomTimeFree(BookingRoom bookingRoom) {
        boolean result = true;
        LocalDateTime startTime = bookingRoom.getStartTime();
        List<BookingRoom> sortByRoom = bookingRoomList.stream().filter(booking -> booking.getRoomName().equals(bookingRoom.getRoomName())).toList();
        for (BookingRoom booking : sortByRoom) {
            if (bookingRoom.getStartTime().equals(startTime)) {
                result = false;
                System.out.println("Указанное время занято");
                break;
            } else if (booking.getStartTime().isBefore(startTime) && bookingRoom.getEndTime().isAfter(startTime)) {
                result = false;
                System.out.println("Указанное время занято");
                break;
            }
        }
        return result;
    }


    public boolean isExistBookingWorkplaceById(int bookingId) {
        boolean result = false;
        for (BookingWorkplace booking : bookingWorkplaceList) {
            if (booking.getBookingId() == bookingId) {
                result = true;
                break;
            } else {
                System.out.println("Бронирование не найдено");
            }
        }
        return result;
    }
    public boolean isExistBookingRoomById(int bookingId) {
        boolean result = false;
        for (BookingRoom booking : bookingRoomList) {
            if (booking.getBookingId() == bookingId) {
                result = true;
                break;
            } else {
                System.out.println("Бронирование не найдено");
            }
        }
        return result;
    }

    public BookingWorkplace getBookingWorkplace(int bookingId) {
        BookingWorkplace bookingWorkplace = null;
        for (BookingWorkplace booking : bookingWorkplaceList) {
            if (booking.getBookingId() == bookingId) {
                bookingWorkplace = booking;
            } else {
                System.out.println("Бронирование не найдено");
            }
        }
        return bookingWorkplace;
    }
    public BookingRoom getBookingRoom(int bookingId) {
        BookingRoom bookingRoom = null;
        for (BookingRoom booking : bookingRoomList) {
            if (booking.getBookingId() == bookingId) {
                bookingRoom = booking;
            } else {
                System.out.println("Бронирование не найдено");
            }
        }
        return bookingRoom;
    }

}
