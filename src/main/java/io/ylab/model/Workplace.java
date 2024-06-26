package io.ylab.model;

import java.util.ArrayList;
import java.util.List;

public class Workplace {
    private int id;
    private int workplaceNumber;
    private String description;
    //List<BookingWorkplace> reservWorkplaceList = new ArrayList<>();

    @Override
    public String toString() {
        return "Workplace{" +
                "workplaceNumber=" + workplaceNumber +
                ", description='" + description + '\'' +
                '}';
    }

    public Workplace(int workplaceNumber, String description) {
        this.workplaceNumber = workplaceNumber;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkplaceNumber() {
        return workplaceNumber;
    }

    public void setWorkplaceNumber(int workplaceNumber) {
        this.workplaceNumber = workplaceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  /*  public List<BookingWorkplace> getReservWorkplaceList() {
        return reservWorkplaceList;
    }

    public void setReservWorkplaceList(List<BookingWorkplace> reservWorkplaceList) {
        this.reservWorkplaceList = reservWorkplaceList;
    }*/
}
