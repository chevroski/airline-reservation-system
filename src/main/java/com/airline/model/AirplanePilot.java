package com.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirplanePilot extends Employee {
    private String licence;
    private int flightHours;
    private List<Flight> assignedFlights;

    public AirplanePilot(int id, String name, String address, String contact, int numberEmp, LocalDate hiringDate, String licence, int flightHours) {
        super(id, name, address, contact, numberEmp, hiringDate);
        this.licence = licence;
        this.flightHours = flightHours;
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
        System.out.println("Licence: " + licence);
        System.out.println("Flight Hours: " + flightHours);
    }
}
