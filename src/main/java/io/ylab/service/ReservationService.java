package io.ylab.service;

import io.ylab.model.Room;
import io.ylab.model.Workplace;

import java.util.Map;

/**
 * interface ReservationService работы с рабочими местами и залами.
 * Позволяет создавать, обновлять, удалять и проверять существование рабочих мест и залов.
 */

public interface ReservationService {
    /**
     * Генерирует id рабочих мест и залов
     * @return id рабочих мест и залов
     */
    int createId();

    /**
     * Создает рабочее место
     * @param workplaceNumber номер рабочего места.
     * @param description описание рабочего места.
     */

    void createWorkplace(int workplaceNumber, String description);

    /**
     * Возвращает список(Map) рабочих мест.
     * @return Map, где ключом номер рабочего места, значением -  Workplace.
     */

    Map<Integer, Workplace> getListWorkplace();

    /**
     * Обновляет описание рабочего места.
     * @param workplaceNumber номер рабочего места для обновления.
     * @param description новое описание рабочего места.
     */
    void updateWorkplace(int workplaceNumber, String description);

    /**
     * Удаляет рабочее место с указанным номером.
     * @param numberWorkplace номер рабочего места для удаления.
     */
    void deleteWorkplace(int numberWorkplace);

    void createRoom(String roomName, String description);

    Map<String, Room> getListRoom();

    void updateRoom(String roomName, String description);

    void deleteRoom(String roomName);

    /**
     * Проверяет, существует ли рабочее место с указанным номером.
     * @param numberWorkplace номер рабочего места.
     * @return true-существует, false не существует.
     */

    boolean isExistWorkplace(int numberWorkplace);
    /**
     * Проверяет, существует ли зал с указанным именем.
     * @param roomName имя зала.
     * @return true-существует, false не существует.
     */
    boolean isExistRoom(String roomName);

}
