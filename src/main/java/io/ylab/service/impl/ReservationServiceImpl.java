package io.ylab.service.impl;

import io.ylab.model.Room;
import io.ylab.model.Workplace;
import io.ylab.out.Printer;
import io.ylab.service.ReservationService;

import java.util.Map;
import java.util.TreeMap;

public class ReservationServiceImpl implements ReservationService {
    private int id = 1;
    Map<String, Room> roomMap = new TreeMap<>();
    Map<Integer, Workplace> workplaceMap = new TreeMap<>();

    @Override
    public int createId() {
        return id++;
    }

    @Override
    public void createWorkplace(int workplaceNumber, String description) {
        if (!isExistWorkplace(workplaceNumber)) {
            Workplace workplace = new Workplace(workplaceNumber, description);
            workplace.setId(createId());
            workplaceMap.put(workplaceNumber, workplace);
            Printer.printMessage("Рабочее место добавлено");
        } else {
            Printer.printMessage("Рабочее место не добавлено");
        }
    }

    @Override
    public Map<Integer, Workplace> getListWorkplace() {
        Printer.printMap(workplaceMap);
        return workplaceMap;
    }

    @Override
    public void updateWorkplace(int workplaceNumber, String description) {
        if (isExistWorkplace(workplaceNumber)) {
            int workplaceId = workplaceMap.get(workplaceNumber).getId();
            Workplace workplace = new Workplace(workplaceNumber, description);
            workplace.setId(workplaceId);
            workplaceMap.put(workplaceNumber, workplace);
            Printer.printMessage("Изменение добавлено");
        } else {
            Printer.printMessage("Изменение не добавлено");
        }
    }

    @Override
    public void deleteWorkplace(int numberWorkplace) {
        if (isExistWorkplace(numberWorkplace)) {
            workplaceMap.remove(numberWorkplace);
            Printer.printMessage("Рабочее место удалено");
        } else {
            Printer.printMessage("Рабочее место не удалено");
        }
    }

    @Override
    public void createRoom(String roomName, String description) {
        if (!isExistRoom(roomName)) {
            Room room = new Room(roomName, description);
            room.setId(createId());
            roomMap.put(roomName, room);
            Printer.printMessage("Конференц-зал добавлен");
        } else {
            Printer.printMessage("Конференц-зал не добавлен");
        }
    }

    @Override
    public Map<String, Room> getListRoom() {
        Printer.printMap(roomMap);
        return roomMap;
    }

    @Override
    public void updateRoom(String roomName, String description) {
        if (isExistRoom(roomName)) {
            int roomId = roomMap.get(roomName).getId();
            Room room = new Room(roomName, description);
            room.setId(roomId);
            roomMap.put(roomName, room);
            Printer.printMessage("Изменение добавлено");
        } else {
            Printer.printMessage("Изменение не добавлено");
        }
    }

    @Override
    public void deleteRoom(String roomName) {
        if (isExistRoom(roomName)) {
            roomMap.remove(roomName);
            Printer.printMessage("Конференц-зал удален");
        } else {
            Printer.printMessage("Конференц-зал не удален");
        }
    }

    @Override
    public boolean isExistWorkplace(int numberWorkplace) {
        boolean result = false;
        if (workplaceMap.size() == 0) {
            return result;
        }
        Workplace workplace = workplaceMap.get(numberWorkplace);
        if (workplace != null) {
            result = true;
            Printer.printMessage("Рабочее место существует");
        }
        return result;
    }

    @Override
    public boolean isExistRoom(String roomName) {
        boolean result = false;
        if (roomMap.size() == 0) {
            return result;
        }
        Room room = roomMap.get(roomName);
        if (room != null) {
            result = true;
            Printer.printMessage("Конференц-зал существует");
        }
        return result;
    }

}
