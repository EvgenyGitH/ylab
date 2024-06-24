package io.ylab.service;

import io.ylab.model.BookingRoom;
import io.ylab.model.BookingWorkplace;
import io.ylab.model.Room;
import io.ylab.model.Workplace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.ylab.Main.reservationService;
import static io.ylab.utils.Utils.formatterDate;

public class BookingService {
    private int id = 1;

    List<BookingRoom> bookingRoomList = new ArrayList<>();
    List<BookingWorkplace> bookingWorkplaceList = new ArrayList<>();


    protected int creatId() {
        return id++;
    }

    public List<BookingWorkplace> getListBookingWorkplaceByDate(String bookingDate) {
        LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
        LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
        List<Workplace> workplaceList = reservationService.getListWorkplace();
        List<BookingWorkplace> sortList = new ArrayList<>();
        for (Workplace workplace : workplaceList) {
            int workplaceNumber = workplace.getWorkplaceNumber();
            List<BookingWorkplace> bookingSortList = bookingWorkplaceList.stream()
                    .filter(bookingWorkplace -> bookingWorkplace.getWorkplaceNumber() == workplaceNumber)
                    .filter(bookingWorkplace -> bookingWorkplace.getStartTime().isAfter(bookingDateTime))
                    .filter(bookingWorkplace -> bookingWorkplace.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).toList();
            printSlotBookingWorkplace(workplaceNumber, bookingDateTime, bookingSortList);
            if(!bookingSortList.isEmpty()){
                sortList.addAll(bookingSortList);
            }
        }

        return sortList;
    }

    public void printSlotBookingWorkplace(int workplaceNumber, LocalDateTime bookingDateTime, List<BookingWorkplace> bookingSortList) {
        LocalDateTime startDay = bookingDateTime.plusMinutes(540);
        LocalDateTime endDay = bookingDateTime.plusMinutes(1260);
        while (startDay.isBefore(endDay)) {
            boolean result = false;
            for (BookingWorkplace booking : bookingSortList) {
                if (booking.getStartTime().equals(startDay)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                System.out.println("Рабочее место: " + workplaceNumber + " время: " + startDay + " - " + startDay.plusMinutes(59) + " занято");
            } else {
                System.out.println("Рабочее место: " + workplaceNumber + " время: " + startDay + " - " + startDay.plusMinutes(59) + " свободно");
            }
            startDay = startDay.plusMinutes(60);
        }
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

    public void getListBookingWorkplace(int userId, String bookingDate) {
        List<BookingWorkplace> sortList = null;
        if (userId == 0 && bookingDate == "") {
            sortList = bookingWorkplaceList.stream()
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());
        }
        if (userId != 0 && bookingDate == "") {
            sortList = bookingWorkplaceList.stream()
                    .filter(bookingWorkplace -> bookingWorkplace.getUserId() == userId)
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());
        }
        if (userId == 0 && bookingDate != "") {
            LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
            LocalTime time = LocalTime.of(0, 0);
            LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
            LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
            sortList = bookingWorkplaceList.stream()
                    .filter(bookingWorkplace -> bookingWorkplace.getStartTime().isAfter(bookingDateTime))
                    .filter(bookingWorkplace -> bookingWorkplace.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());
        }

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

    public void deleteBookingWorkplaceById(int bookingId) {
        if (isExistBookingWorkplaceById(bookingId)) {
            BookingWorkplace bookingInList = getBookingWorkplace(bookingId);
            int indexBooking = bookingWorkplaceList.indexOf(bookingInList);
            bookingWorkplaceList.remove(indexBooking);
            System.out.println("Бронирование отменено: " + bookingId);
        } else {
            System.out.println("Бронирование не найдено");
        }
    }

//---

    public List<BookingRoom> getListBookingRoomByDate(String bookingDate) {
        LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
        LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
        List<Room> roomList = reservationService.getListRoom();
        List<BookingRoom> sortList = new ArrayList<>();
        for (Room room : roomList) {
            String roomName = room.getRoomName();
            List<BookingRoom> bookingSortList = bookingRoomList.stream()
                    .filter(booking -> booking.getRoomName().equals(roomName))
                    .filter(booking -> booking.getStartTime().isAfter(bookingDateTime))
                    .filter(booking -> booking.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).toList();
            printSlotBookingRoom(roomName, bookingDateTime, bookingSortList);
            if(!bookingSortList.isEmpty()){
                sortList.addAll(bookingSortList);
            }
        }
        return sortList;
    }

    public void printSlotBookingRoom(String roomName, LocalDateTime bookingDateTime, List<BookingRoom> bookingSortList) {
        LocalDateTime startDay = bookingDateTime.plusMinutes(540);
        LocalDateTime endDay = bookingDateTime.plusMinutes(1260);
        while (startDay.isBefore(endDay)) {
            boolean result = false;
            for (BookingRoom booking : bookingSortList) {
                if (booking.getStartTime().equals(startDay)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                System.out.println("Конференц-Зал: " + roomName + " время: " + startDay + " - " + startDay.plusMinutes(59) + " занято");
            } else {
                System.out.println("Конференц-Зал: " + roomName + " время: " + startDay + " - " + startDay.plusMinutes(59) + " свободно");
            }
            startDay = startDay.plusMinutes(60);
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

    public void getListBookingRoom(int userId, String bookingDate) {
        List<BookingRoom> sortList = null;
        if (userId == 0 && bookingDate == "") {
            sortList = bookingRoomList.stream()
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        }
        if (userId != 0 && bookingDate == "") {
            sortList = bookingRoomList.stream()
                    .filter(bookingRoom -> bookingRoom.getUserId() == userId)
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        }
        if (userId == 0 && bookingDate != "") {
            LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
            LocalTime time = LocalTime.of(0, 0);
            LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
            LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
            sortList = bookingRoomList.stream()
                    .filter(bookingRoom -> bookingRoom.getStartTime().isAfter(bookingDateTime))
                    .filter(bookingRoom -> bookingRoom.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        }
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

    public void deleteBookingRoomById(int bookingId) {
        if (isExistBookingRoomById(bookingId)) {
            BookingRoom bookingInList = getBookingRoom(bookingId);
            int indexBooking = bookingRoomList.indexOf(bookingInList);
            bookingRoomList.remove(indexBooking);
            System.out.println("Бронирование отменено: " + bookingId);
        } else {
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
