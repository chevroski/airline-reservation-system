package com.airline.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationNumber;
    private LocalDateTime dateReservation;
    private String status;
    private Passenger passenger;
    private Flight flight;

    public Reservation(int reservationNumber, Passenger passenger, Flight flight) {
        this.reservationNumber = reservationNumber;
        this.dateReservation = LocalDateTime.now();
        this.status = "Confirmed";
        this.passenger = passenger;
        this.flight = flight;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void confirmReservation() {
        this.status = "Confirmed";
    }

    public void cancelReservation() {
        this.status = "Cancelled";
    }

    public void modifyReservation(Flight newFlight) {
        this.flight = newFlight;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", status='" + status + '\'' +
                ", flight=" + flight.getFlightNumber() +
                '}';
    }
}
