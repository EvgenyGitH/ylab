package io.ylab.service.impl;

import io.ylab.model.BookingRoom;
import io.ylab.model.BookingWorkplace;
import io.ylab.model.Room;
import io.ylab.model.Workplace;
import io.ylab.out.Printer;
import io.ylab.service.BookingService;
import io.ylab.service.ReservationService;
import io.ylab.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.ylab.utils.Utils.formatter;
import static io.ylab.utils.Utils.formatterDate;

public class BookingServiceImpl implements BookingService {
    private int id = 1;

    private final UserService userService;
    private final ReservationService reservationService;

    public BookingServiceImpl(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }


    List<BookingRoom> bookingRoomList = new ArrayList<>();
    List<BookingWorkplace> bookingWorkplaceList = new ArrayList<>();

    @Override
    public int createId() {
        return id++;
    }

    @Override
    public List<BookingWorkplace> getListBookingWorkplaceByDate(String bookingDate) {
        LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
        LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
        Map<Integer, Workplace> workplaceList = reservationService.getListWorkplace();
        List<BookingWorkplace> sortList = new ArrayList<>();
        for (Integer key : workplaceList.keySet()) {
            int workplaceNumber = key;
            List<BookingWorkplace> bookingSortList = bookingWorkplaceList.stream()
                    .filter(bookingWorkplace -> bookingWorkplace.getWorkplaceNumber() == workplaceNumber)
                    .filter(bookingWorkplace -> bookingWorkplace.getStartTime().isAfter(bookingDateTime))
                    .filter(bookingWorkplace -> bookingWorkplace.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).toList();
            Printer.printSlotBookingWorkplace(workplaceNumber, bookingDateTime, bookingSortList);
            if (!bookingSortList.isEmpty()) {
                sortList.addAll(bookingSortList);
            }
        }
        return sortList;
    }

    @Override
    public void createBookingWorkplace(int workplaceNumber, String login, String startTime) {

        if (userService.isExistsLogin(login) && reservationService.isExistWorkplace(workplaceNumber)) {
            if (!isWorkplaceTimeFree(workplaceNumber, startTime)) {
                System.out.println("Выберите другое время");
            } else {
                BookingWorkplace bookingWorkplace = new BookingWorkplace(workplaceNumber, login, startTime);
                bookingWorkplace.setBookingId(createId());
                bookingWorkplace.setEndTime(bookingWorkplace.getStartTime().plusMinutes(59L));
                bookingWorkplaceList.add(bookingWorkplace);
                Printer.printMessage("Успешно зарегистрировано. Номер бронирования: " + bookingWorkplace.getBookingId());
            }
        } else {
            Printer.printMessage("Не соответствуют вводные данные ");
        }
    }

    @Override
    public void getListBookingWorkplace(String login, String bookingDate) {
        List<BookingWorkplace> sortList = null;
        if (login.equals("") && bookingDate.equals("")) {
            sortList = bookingWorkplaceList.stream()
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());
        }
        if (!login.equals("") && bookingDate.equals("")) {
            sortList = bookingWorkplaceList.stream()
                    .filter(bookingWorkplace -> bookingWorkplace.getUserLogin().equals(login))
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());
        }
        if (login.equals("") && !bookingDate.equals("")) {
            LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
            LocalTime time = LocalTime.of(0, 0);
            LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
            LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
            sortList = bookingWorkplaceList.stream()
                    .filter(bookingWorkplace -> bookingWorkplace.getStartTime().isAfter(bookingDateTime))
                    .filter(bookingWorkplace -> bookingWorkplace.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingWorkplace::getStartTime)).collect(Collectors.toList());
        }
        Printer.printList(sortList);
    }

    @Override
    public void updateBookingWorkplace(int bookingId, int workplaceNumber, String login, String startTime) {
        if (userService.isExistsLogin(login) && reservationService.isExistWorkplace(workplaceNumber) && isExistBookingWorkplaceById(bookingId)) {
            if (!isWorkplaceTimeFree(workplaceNumber, startTime)) {
                Printer.printMessage("Выберите другое время");
            } else {
                BookingWorkplace bookingInList = getBookingWorkplace(bookingId);
                int indexBooking = bookingWorkplaceList.indexOf(bookingInList);
                BookingWorkplace bookingWorkplace = new BookingWorkplace(workplaceNumber, login, startTime);
                bookingWorkplace.setEndTime(bookingWorkplace.getStartTime().plusMinutes(59L));
                bookingWorkplaceList.set(indexBooking, bookingWorkplace);
                Printer.printMessage("Изменения внесены. Номер бронирования: " + bookingWorkplace.getBookingId());
            }

        } else {
            Printer.printMessage("Не соответствуют вводные данные");
        }
    }

    @Override
    public void deleteBookingWorkplaceById(int bookingId) {
        if (isExistBookingWorkplaceById(bookingId)) {
            BookingWorkplace bookingInList = getBookingWorkplace(bookingId);
            int indexBooking = bookingWorkplaceList.indexOf(bookingInList);
            bookingWorkplaceList.remove(indexBooking);
            Printer.printMessage("Бронирование отменено: " + bookingId);
        } else {
            Printer.printMessage("Бронирование не найдено");
        }
    }

    //---
    @Override
    public List<BookingRoom> getListBookingRoomByDate(String bookingDate) {
        LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
        LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
        Map<String, Room> roomList = reservationService.getListRoom();
        List<BookingRoom> sortList = new ArrayList<>();
        for (String key : roomList.keySet()) {
            String roomName = key;
            List<BookingRoom> bookingSortList = bookingRoomList.stream()
                    .filter(booking -> booking.getRoomName().equals(roomName))
                    .filter(booking -> booking.getStartTime().isAfter(bookingDateTime))
                    .filter(booking -> booking.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).toList();
            Printer.printSlotBookingRoom(roomName, bookingDateTime, bookingSortList);
            if (!bookingSortList.isEmpty()) {
                sortList.addAll(bookingSortList);
            }
        }
        return sortList;
    }

    @Override
    public void createBookingRoom(String roomName, String login, String startTime) {
        if (userService.isExistsLogin(login) && reservationService.isExistRoom(roomName)) {
            if (!isRoomTimeFree(roomName, startTime)) {
                Printer.printMessage("Выберите другое время");
            } else {
                BookingRoom bookingRoom = new BookingRoom(roomName, login, startTime);
                bookingRoom.setBookingId(createId());
                bookingRoom.setEndTime(bookingRoom.getStartTime().plusMinutes(59L));
                bookingRoomList.add(bookingRoom);
                Printer.printMessage("Успешно зарегистрировано. Номер бронирования: " + bookingRoom.getBookingId());
            }
        } else {
            Printer.printMessage("Не соответствуют вводные данные");
        }


    }

    @Override
    public void getListBookingRoom(String login, String bookingDate) {
        List<BookingRoom> sortList = null;
        if (login.equals("") && bookingDate.equals("")) {
            sortList = bookingRoomList.stream()
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        }
        if (!login.equals("") && bookingDate.equals("")) {
            sortList = bookingRoomList.stream()
                    .filter(bookingRoom -> bookingRoom.getUserLogin().equals(login))
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        }
        if (login.equals("") && !bookingDate.equals("")) {
            LocalDate bookingDateTimeStart = LocalDate.parse(bookingDate, formatterDate);
            LocalTime time = LocalTime.of(0, 0);
            LocalDateTime bookingDateTime = bookingDateTimeStart.atTime(time);
            LocalDateTime bookingDateTimeEnd = bookingDateTime.plusMinutes(1439);
            sortList = bookingRoomList.stream()
                    .filter(bookingRoom -> bookingRoom.getStartTime().isAfter(bookingDateTime))
                    .filter(bookingRoom -> bookingRoom.getEndTime().isBefore(bookingDateTimeEnd))
                    .sorted(Comparator.comparing(BookingRoom::getStartTime)).collect(Collectors.toList());
        }
        Printer.printList(sortList);
    }

    @Override
    public void updateBookingRoom(int bookingId, String roomName, String login, String startTime) {
        if (userService.isExistsLogin(login) && reservationService.isExistRoom(roomName) && isExistBookingRoomById(bookingId)) {
            if (!isRoomTimeFree(roomName, startTime)) {
                Printer.printMessage("Выберите другое время");
            } else {
                BookingRoom bookingInList = getBookingRoom(bookingId);
                int indexBooking = bookingRoomList.indexOf(bookingInList);
                BookingRoom bookingRoom = new BookingRoom(roomName, login, startTime);
                bookingRoom.setEndTime(bookingRoom.getStartTime().plusMinutes(59L));
                bookingRoomList.set(indexBooking, bookingRoom);
                Printer.printMessage("Изменения внесены. Номер бронирования: " + bookingRoom.getBookingId());
            }
        } else {
            Printer.printMessage("Не соответствуют вводные данные");
        }
    }

    @Override
    public void deleteBookingRoomById(int bookingId) {
        if (isExistBookingRoomById(bookingId)) {
            BookingRoom bookingInList = getBookingRoom(bookingId);
            int indexBooking = bookingRoomList.indexOf(bookingInList);
            bookingRoomList.remove(indexBooking);
            Printer.printMessage("Бронирование отменено: " + bookingId);
        } else {
            Printer.printMessage("Бронирование не найдено");
        }
    }

    @Override
    public boolean isWorkplaceTimeFree(int workplaceNumber, String startTimeString) {
        boolean result = true;
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        List<BookingWorkplace> sortByWorkplace = bookingWorkplaceList.stream().filter(booking -> booking.getWorkplaceNumber() == workplaceNumber).toList();
        for (BookingWorkplace bookingWorkplaceInList : sortByWorkplace) {
            if (bookingWorkplaceInList.getStartTime().equals(startTime)) {
                result = false;
                Printer.printMessage("Указанное время занято");
                break;
            } else if (bookingWorkplaceInList.getStartTime().isBefore(startTime) && bookingWorkplaceInList.getEndTime().isAfter(startTime)) {
                result = false;
                Printer.printMessage("Указанное время занято");
                break;
            }
        }
        return result;
    }

    @Override
    public boolean isRoomTimeFree(String roomName, String startTimeString) {
        boolean result = true;
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        List<BookingRoom> sortByRoom = bookingRoomList.stream().filter(booking -> booking.getRoomName().equals(roomName)).toList();
        for (BookingRoom booking : sortByRoom) {
            if (booking.getStartTime().equals(startTime)) {
                result = false;
                Printer.printMessage("Указанное время занято");
                break;
            } else if (booking.getStartTime().isBefore(startTime) && booking.getEndTime().isAfter(startTime)) {
                result = false;
                Printer.printMessage("Указанное время занято");
                break;
            }
        }
        return result;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
