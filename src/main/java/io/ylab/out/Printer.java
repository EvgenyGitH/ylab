package io.ylab.out;

import io.ylab.model.BookingRoom;
import io.ylab.model.BookingWorkplace;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Printer {

    public static void printMessage (String text){
        System.out.println(text);
    }
    public static <K,V> void printMap (Map<K,V> map){
       map.forEach((key,value)-> System.out.println("Номер: " + key + " Описание: " + value));
    }

    public static void printList(List<?> list){
        for (Object element : list) {
            System.out.println(element);
        }
    }

    public static void printSlotBookingWorkplace(int workplaceNumber, LocalDateTime bookingDateTime, List<BookingWorkplace> bookingSortList) {
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
    public static void printSlotBookingRoom(String roomName, LocalDateTime bookingDateTime, List<BookingRoom> bookingSortList) {
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

}
