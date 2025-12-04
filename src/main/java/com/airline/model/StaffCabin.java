package com.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffCabin extends Employee {
    private String qualification;
    private List<Flight> assignedFlights;

    public StaffCabin(int id, String name, String address, String contact, int numberEmp, LocalDate hiringDate, String qualification) {
        super(id, name, address, contact, numberEmp, hiringDate);
        this.qualification = qualification;
        this.assignedFlights = new ArrayList<>();
    }

    public void assignFlight(Flight flight) {
        assignedFlights.add(flight);
    }

    public List<Flight> obtainVol() {
        return assignedFlights;
    }

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Qualification: " + qualification);
    }
}
