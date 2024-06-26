package io.ylab.service;

import io.ylab.model.BookingRoom;
import io.ylab.model.BookingWorkplace;

import java.util.List;

/**
 * interface BookingService для работы с бронированием рабочих мест и залов.
 * Позволяет создавать, обновлять, удалять и проверять существование бронирований.
 */
public interface BookingService {
    /**
     * Генерирует id бронирования.
     * @return id бронирования.
     */
    int createId();

    /**
     * Возвращает список бронирований рабочих мест на указанную дату.
     * @param bookingDate дата бронирования.
     * @return список объектов BookingWorkplace.
     */
    List<BookingWorkplace> getListBookingWorkplaceByDate(String bookingDate);

    /**
     * Создает бронирование рабочего места.
     * @param workplaceNumber номер рабочего места.
     * @param login логин пользователя.
     * @param startTime время начала бронирования.
     */
    void createBookingWorkplace(int workplaceNumber, String login, String startTime);

    /**
     * Выводит список бронирований рабочих мест (фильтр все, по пользователю или дате).
     * @param login логин пользователя.
     * @param bookingDate дата бронирования.
     */
    void getListBookingWorkplace(String login, String bookingDate);

    /**
     * Обновляет бронирование рабочего места.
     * @param bookingId id бронирования.
     * @param workplaceNumber номер рабочего места.
     * @param login логин пользователя.
     * @param startTime время начала бронирования.
     */
    void updateBookingWorkplace(int bookingId, int workplaceNumber, String login, String startTime);

    /**
     * Удаляет бронирование рабочего места.
     * @param bookingId id бронирования.
     */
    void deleteBookingWorkplaceById(int bookingId);

    List<BookingRoom> getListBookingRoomByDate(String bookingDate);

    void createBookingRoom(String roomName, String login, String startTime);

    void getListBookingRoom(String login, String bookingDate);

    void updateBookingRoom(int bookingId, String roomName, String login, String startTime);

    void deleteBookingRoomById(int bookingId);

    /**
     * Проверяет, свободно ли рабочее место.
     * @param workplaceNumber номер рабочего места.
     * @param startTimeString время начала бронирования.
     * @return true, свободно, false нет.
     */

    boolean isWorkplaceTimeFree(int workplaceNumber, String startTimeString);

    /**
     * Проверяет, свободен ли зал
     * @param roomName имя зала.
     * @param startTimeString время начала бронирования.
     * @return true, свободен, false нет.
     */
    boolean isRoomTimeFree(String roomName, String startTimeString);

    /**
     * Проверяет, существует ли бронирование рабочего места по id.
     * @param bookingId id бронирования.
     * @return true, существует, false не существует.
     */
    boolean isExistBookingWorkplaceById(int bookingId);

    /**
     * Проверяет, существует ли бронирование зала по id.
     * @param bookingId id бронирования.
     * @return true, существует, false не существует.
     */
    boolean isExistBookingRoomById(int bookingId);

    /**
     * Возвращает бронирование рабочего места по id
     * @param bookingId id бронирования.
     * @return объект BookingWorkplace.
     */
    BookingWorkplace getBookingWorkplace(int bookingId);

    /**
     * Возвращает бронирование зала по id
     * @param bookingId id бронирования.
     * @return объект BookingRoom.
     */
    BookingRoom getBookingRoom(int bookingId);

}
