package com.airline.service;

import com.airline.model.Flight;
import com.airline.model.Passenger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    private AirlineManager manager;

    public StatisticsService() {
        this.manager = AirlineManager.getInstance();
    }

    public void generateFlightReport() {
        List<Flight> flights = manager.getFlights();
        int totalFlights = flights.size();
        int totalPassengers = 0;
        for (Flight f : flights) {
            totalPassengers += f.listingPassenger().size();
        }
        System.out.println("--- Flight Report ---");
        System.out.println("Total Flights: " + totalFlights);
        System.out.println("Total Passengers Carried: " + totalPassengers);
    }

    public void calculateRevenue() {
        // Assuming a fixed price for simplicity as price wasn't in the diagram
        // Or we could add price to Flight/Reservation. Let's assume $100 per passenger.
        double pricePerTicket = 100.0;
        double totalRevenue = 0;
        for (Flight f : flights()) {
            totalRevenue += f.listingPassenger().size() * pricePerTicket;
        }
        System.out.println("--- Revenue Report ---");
        System.out.println("Total Revenue (Estimated @ $100/ticket): $" + totalRevenue);
    }

    public void getPopularDestinations() {
        Map<String, Integer> destinationCounts = new HashMap<>();
        for (Flight f : flights()) {
            String dest = f.getDestination().getCity(); // Assuming destination is accessible via Flight -> Airport -> City
            // Wait, Flight.destination is private and no getter in my previous code? Let's check.
            // I need to ensure Flight has getDestination()
             destinationCounts.put(dest, destinationCounts.getOrDefault(dest, 0) + f.listingPassenger().size());
        }
        
        System.out.println("--- Popular Destinations ---");
        destinationCounts.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " passengers"));
    }
    
    private List<Flight> flights() {
        return manager.getFlights();
    }
}
