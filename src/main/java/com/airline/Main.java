package com.airline;

import com.airline.model.*;
import com.airline.service.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static AirlineManager manager = AirlineManager.getInstance();
    private static StatisticsService statsService = new StatisticsService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populate some data for testing
        initializeData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listFlights();
                    break;
                case 2:
                    addPassenger();
                    break;
                case 3:
                    bookFlight();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    showStatistics();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Airline Reservation System ---");
        System.out.println("1. List Flights");
        System.out.println("2. Add Passenger");
        System.out.println("3. Book Flight");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Show Statistics (Bonus)");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    private static void initializeData() {
        Airport jfk = new Airport("JFK", "New York", "John F. Kennedy International Airport");
        Airport lhr = new Airport("LHR", "London", "Heathrow Airport");
        manager.addAirport(jfk);
        manager.addAirport(lhr);

        Aircraft boeing = new Aircraft("B747", "Boeing 747", 400);
        manager.addAircraft(boeing);

        Flight f1 = new Flight(101, jfk, lhr, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(7));
        f1.setAircraft(boeing);
        manager.addFlight(f1);

        Passenger p1 = new Passenger(1, "John Doe", "123 Main St", "555-0101", "A1234567");
        manager.addPassenger(p1);
    }

    private static void listFlights() {
        System.out.println("\n--- Available Flights ---");
        for (Flight f : manager.getFlights()) {
            f.getFlight();
        }
    }

    private static void addPassenger() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Passport Number: ");
        String passport = scanner.nextLine();
        // Simplified creation
        Passenger p = new Passenger(manager.getPassengers().size() + 1, name, "Unknown", "Unknown", passport);
        manager.addPassenger(p);
        System.out.println("Passenger added.");
    }

    private static void bookFlight() {
        System.out.print("Enter Passenger ID: ");
        int pId = scanner.nextInt();
        Passenger p = null;
        for (Passenger passenger : manager.getPassengers()) {
            if (passenger.getId() == pId) {
                p = passenger;
                break;
            }
        }
        if (p == null) {
            System.out.println("Passenger not found.");
            return;
        }

        System.out.print("Enter Flight Number: ");
        int fNum = scanner.nextInt();
        Flight f = manager.getFlightByNumber(fNum);
        if (f == null) {
            System.out.println("Flight not found.");
            return;
        }

        p.bookFlight(f);
    }

    private static void cancelBooking() {
        System.out.print("Enter Passenger ID: ");
        int pId = scanner.nextInt();
        Passenger p = null;
        for (Passenger passenger : manager.getPassengers()) {
            if (passenger.getId() == pId) {
                p = passenger;
                break;
            }
        }
        if (p == null) {
            System.out.println("Passenger not found.");
            return;
        }
        
        System.out.println("Your Reservations:");
        for(Reservation r : p.getReservations()) {
            System.out.println(r);
        }

        System.out.print("Enter Reservation Number to cancel: ");
        int rNum = scanner.nextInt();
        p.cancelBook(rNum);
    }

    private static void showStatistics() {
        statsService.generateFlightReport();
        statsService.calculateRevenue();
        statsService.getPopularDestinations();
    }
}
