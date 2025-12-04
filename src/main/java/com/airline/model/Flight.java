package com.airline.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int flightNumber;
    private Airport origin;
    private Airport destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalDateTime;
    private String status;
    private Aircraft aircraft;
    private AirplanePilot pilot;
    private List<StaffCabin> crew;
    private List<Passenger> passengers;

    public Flight(int flightNumber, Airport origin, Airport destination, LocalDateTime departureTime, LocalDateTime arrivalDateTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalDateTime = arrivalDateTime;
        this.status = "Scheduled";
        this.crew = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void planFlight() {
        this.status = "Planned";
    }

    public void cancelFlight() {
        this.status = "Cancelled";
    }

    public void modifyFlight(LocalDateTime newDeparture, LocalDateTime newArrival) {
        this.departureTime = newDeparture;
        this.arrivalDateTime = newArrival;
    }

    public List<Passenger> listingPassenger() {
        return passengers;
    }

    public void assignPilot(AirplanePilot pilot) {
        this.pilot = pilot;
    }

    public void addCabinCrew(StaffCabin staff) {
        this.crew.add(staff);
    }
    
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }
    
    public void getFlight() {
         System.out.println("Flight " + flightNumber + " from " + origin.getCity() + " to " + destination.getCity());
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }
}
