package com.airline.model;

public class Airport {
    private String name;
    private String city;
    private String description;

    public Airport(String name, String city, String description) {
        this.name = name;
        this.city = city;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void assignFlight(Flight flight) {
        // Logic to associate flight with airport (departure or arrival)
    }
}
