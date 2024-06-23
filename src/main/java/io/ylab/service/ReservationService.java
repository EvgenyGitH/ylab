package io.ylab.service;

import io.ylab.model.Room;
import io.ylab.model.Workplace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    List<Room> roomList = new ArrayList<>();
    List<Workplace> workplaceList = new ArrayList<>();

    public void createWorkplace(Workplace workplace) {
        if (!isExistWorkplace(workplace.getWorkplaceNumber())) {
            workplaceList.add(workplace);
            System.out.println("Рабочее место добавлено");
        } else {
            System.out.println("Рабочее место не добавлено");
        }
    }
    public List<Workplace> getListWorkplace() {
        List<Workplace> sortList = workplaceList.stream().sorted(Comparator.comparing(Workplace::getWorkplaceNumber)).collect(Collectors.toList());
        System.out.println(sortList);
        return sortList;
    }
    public void updateWorkplace(Workplace workplace) {
        if (isExistWorkplace(workplace.getWorkplaceNumber())) {
            Workplace workplaceToChange = null;
            for (Workplace place : workplaceList) {
                if (place.getWorkplaceNumber() == workplace.getWorkplaceNumber()) {
                    workplaceToChange = place;
                }
            }
            int indexWorkplace = workplaceList.indexOf(workplaceToChange);
            workplaceList.set(indexWorkplace, workplace);
            System.out.println("Изменение добавлено");
        } else {
            System.out.println("Изменение не добавлено");
        }
    }
    public void deleteWorkplace(int numberWorkplace) {
        if (isExistWorkplace(numberWorkplace)) {
            Workplace workplaceToDelete = null;
            for (Workplace place : workplaceList) {
                if (place.getWorkplaceNumber() == numberWorkplace) {
                    workplaceToDelete = place;
                }
            }
            int indexWorkplace = workplaceList.indexOf(workplaceToDelete);
            workplaceList.remove(indexWorkplace);
            System.out.println("Рабочее место удалено");
        } else {
            System.out.println("Рабочее место не удалено");
        }
    }

    public void createRoom(Room room) {
        if (!isExistRoom(room.getRoomName())) {
            roomList.add(room);
            System.out.println("Конференц-зал добавлен");
        } else {
            System.out.println("Конференц-зал не добавлен");
        }
    }
    public void getListRoom() {
        List<Room>sortList = roomList.stream().sorted(Comparator.comparing(Room::getRoomName)).collect(Collectors.toList());
        System.out.println(sortList);
    }
    public void updateRoom(Room room) {
        if(isExistRoom(room.getRoomName())){
            Room roomToUpdate = null;
            for (Room rooInList : roomList) {
                if (rooInList.getRoomName().equals(room.getRoomName())){
                    roomToUpdate = rooInList;
                }
            }
            int indexRoom = roomList.indexOf(roomToUpdate);
            roomList.set(indexRoom,room);
            System.out.println("Изменение добавлено");
        } else {
            System.out.println("Изменение не добавлено");
        }
    }
    public void deleteRoom(String roomName) {
        if(isExistRoom(roomName)){
            Room roomToDelete = null;
            for (Room rooInList : roomList) {
                if (rooInList.getRoomName().equals(roomName)){
                    roomToDelete = rooInList;
                }
            }
            int indexRoom = roomList.indexOf(roomToDelete);
            roomList.remove(indexRoom);
            System.out.println("Конференц-зал удален");
        } else {
            System.out.println("Конференц-зал не удален");
        }
    }

    public boolean isExistWorkplace(int numberWorkplace) {
        boolean result = false;
        for (Workplace place : workplaceList) {
            if (place.getWorkplaceNumber() == numberWorkplace) {
                System.out.println("Рабочее место существует");
                result = true;
            }
        }
        return result;
    }
    public boolean isExistRoom(String roomName) {
        boolean result = false;
        for (Room room : roomList) {
            if(room.getRoomName().equals(roomName)){
                System.out.println("Конференц-зал существует");
                result = true;
            }
        }
        return result;
    }

}
