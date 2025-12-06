package com.airline.service;

import com.airline.model.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineManager {
    private static AirlineManager instance;
    private List<Flight> flights;
    private List<Passenger> passengers;
    private List<Employee> employees;
    private List<Aircraft> aircrafts;
    private List<Airport> airports;

    private AirlineManager() {
        flights = new ArrayList<>();
        passengers = new ArrayList<>();
        employees = new ArrayList<>();
        aircrafts = new ArrayList<>();
        airports = new ArrayList<>();
    }

    public static AirlineManager getInstance() {
        if (instance == null) {
            instance = new AirlineManager();
        }
        return instance;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    public void addAirport(Airport airport) {
        airports.add(airport);
    }

    public List<Flight> getFlights() {
        return flights;
    }
    
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public Flight getFlightByNumber(int number) {
        for (Flight f : flights) {
            if (f.getFlightNumber() == number) {
                return f;
            }
        }
        return null;
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }
}
