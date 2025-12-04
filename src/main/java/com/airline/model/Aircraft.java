package com.airline.model;

import java.time.LocalDateTime;

public class Aircraft {
    private String registration;
    private String model;
    private int capacity;

    public Aircraft(String registration, String model, int capacity) {
        this.registration = registration;
        this.model = model;
        this.capacity = capacity;
    }

    public String getRegistration() {
        return registration;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void affectFlight(Flight flight) {
        // Logic to link aircraft to flight
        // This might be handled in Flight or Manager, but here for method signature compliance
    }

    public boolean checkAvailability(LocalDateTime departure, LocalDateTime arrival) {
        // Logic to check if aircraft is free during this time
        // Requires access to all flights this aircraft is assigned to
        return true; // Placeholder
    }
}
