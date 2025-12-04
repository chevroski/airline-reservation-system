package com.airline.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Person {
    private String passportNumber;
    private List<Reservation> reservations;

    public Passenger(int id, String name, String address, String contact, String passportNumber) {
        super(id, name, address, contact);
        this.passportNumber = passportNumber;
        this.reservations = new ArrayList<>();
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void bookFlight(Flight flight) {
        int reservationId = (int) (Math.random() * 10000); // Simple ID generation
        Reservation reservation = new Reservation(reservationId, this, flight);
        this.reservations.add(reservation);
        flight.addPassenger(this);
        System.out.println("Flight booked successfully. Reservation ID: " + reservationId);
    }

    public void cancelBook(int reservationId) {
        Reservation toRemove = null;
        for (Reservation r : reservations) {
            if (r.getReservationNumber() == reservationId) {
                toRemove = r;
                break;
            }
        }
        if (toRemove != null) {
            toRemove.cancelReservation();
            reservations.remove(toRemove);
            System.out.println("Reservation " + reservationId + " cancelled.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Passport Number: " + passportNumber);
    }
    
    // Helper to add reservation internally
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
