package com.airline;

import com.airline.model.*;
import com.airline.service.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static AirlineManager manager = AirlineManager.getInstance();
    private static StatisticsService statsService = new StatisticsService();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        initializeData();

        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: listFlights(); break;
                    case 2: addPassenger(); break;
                    case 3: bookFlight(); break;
                    case 4: cancelBooking(); break;
                    case 5: showStatistics(); break;
                    case 6: adminMenu(); break;
                    case 7: running = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
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
        System.out.println("6. Admin Menu (New!)");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");
    }

    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Create Flight");
            System.out.println("2. Cancel Flight");
            System.out.println("3. Register Employee");
            System.out.println("4. Assign Crew to Flight");
            System.out.println("5. Add Aircraft");
            System.out.println("6. Add Airport");
            System.out.println("7. Assign Aircraft to Flight");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: createFlight(); break;
                    case 2: cancelFlight(); break;
                    case 3: registerEmployee(); break;
                    case 4: assignCrewToFlight(); break;
                    case 5: addAircraft(); break;
                    case 6: addAirport(); break;
                    case 7: assignAircraftToFlight(); break;
                    case 8: back = true; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
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
        
        AirplanePilot pilot = new AirplanePilot(1, "Maverick", "USA", "555-TOPGUN", 101, LocalDate.now(), "LIC-001", 5000);
        manager.addEmployee(pilot);
    }

    // --- Main Menu Actions ---

    private static void listFlights() {
        System.out.println("\n--- Available Flights ---");
        if (manager.getFlights().isEmpty()) {
            System.out.println("No flights available.");
        } else {
            for (Flight f : manager.getFlights()) {
                f.getFlight();
            }
        }
    }

    private static void addPassenger() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Passport Number: ");
        String passport = scanner.nextLine();
        Passenger p = new Passenger(manager.getPassengers().size() + 1, name, "Unknown", "Unknown", passport);
        manager.addPassenger(p);
        System.out.println("Passenger added.");
    }

    private static void bookFlight() {
        System.out.print("Enter Passenger ID: ");
        int pId = Integer.parseInt(scanner.nextLine());
        Passenger p = findPassenger(pId);
        if (p == null) return;

        System.out.print("Enter Flight Number: ");
        int fNum = Integer.parseInt(scanner.nextLine());
        Flight f = manager.getFlightByNumber(fNum);
        if (f == null) {
            System.out.println("Flight not found.");
            return;
        }

        p.bookFlight(f);
    }

    private static void cancelBooking() {
        System.out.print("Enter Passenger ID: ");
        int pId = Integer.parseInt(scanner.nextLine());
        Passenger p = findPassenger(pId);
        if (p == null) return;
        
        System.out.println("Your Reservations:");
        for(Reservation r : p.getReservations()) {
            System.out.println(r);
        }

        System.out.print("Enter Reservation Number to cancel: ");
        int rNum = Integer.parseInt(scanner.nextLine());
        p.cancelBook(rNum);
    }

    private static void showStatistics() {
        statsService.generateFlightReport();
        statsService.calculateRevenue();
        statsService.getPopularDestinations();
    }

    // --- Admin Actions ---

    private static void createFlight() {
        System.out.print("Enter Flight Number: ");
        int fNum = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Origin Airport Code (e.g., JFK): ");
        String originCode = scanner.nextLine();
        Airport origin = findAirport(originCode);
        if (origin == null) { System.out.println("Airport not found. Create it first."); return; }

        System.out.print("Enter Destination Airport Code (e.g., LHR): ");
        String destCode = scanner.nextLine();
        Airport dest = findAirport(destCode);
        if (dest == null) { System.out.println("Airport not found. Create it first."); return; }

        System.out.print("Enter Departure Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime dep = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);
        
        System.out.print("Enter Arrival Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime arr = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);

        Flight f = new Flight(fNum, origin, dest, dep, arr);
        f.planFlight();
        manager.addFlight(f);
        System.out.println("Flight created successfully.");
    }

    private static void cancelFlight() {
        System.out.print("Enter Flight Number: ");
        int fNum = Integer.parseInt(scanner.nextLine());
        Flight f = manager.getFlightByNumber(fNum);
        if (f != null) {
            f.cancelFlight();
            manager.removeFlight(f); // Optional: remove or just mark cancelled
            System.out.println("Flight cancelled.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    private static void registerEmployee() {
        System.out.println("1. Pilot");
        System.out.println("2. Cabin Crew");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (type == 1) {
            System.out.print("Enter Licence: ");
            String licence = scanner.nextLine();
            System.out.print("Enter Flight Hours: ");
            int hours = Integer.parseInt(scanner.nextLine());
            AirplanePilot pilot = new AirplanePilot(id, name, "N/A", "N/A", id, LocalDate.now(), licence, hours);
            manager.addEmployee(pilot);
        } else {
            System.out.print("Enter Qualification: ");
            String qual = scanner.nextLine();
            StaffCabin staff = new StaffCabin(id, name, "N/A", "N/A", id, LocalDate.now(), qual);
            manager.addEmployee(staff);
        }
        System.out.println("Employee registered.");
    }

    private static void assignCrewToFlight() {
        System.out.print("Enter Flight Number: ");
        int fNum = Integer.parseInt(scanner.nextLine());
        Flight f = manager.getFlightByNumber(fNum);
        if (f == null) { System.out.println("Flight not found."); return; }

        System.out.print("Enter Employee ID to assign: ");
        int eId = Integer.parseInt(scanner.nextLine());
        
        Employee emp = null;
        for (Employee e : manager.getEmployees()) {
            if (e.getId() == eId) {
                emp = e;
                break;
            }
        }

        if (emp == null) {
            System.out.println("Employee not found.");
        } else if (emp instanceof AirplanePilot) {
            f.assignPilot((AirplanePilot) emp);
            ((AirplanePilot) emp).assignFlight(f);
            System.out.println("Pilot assigned.");
        } else if (emp instanceof StaffCabin) {
            f.addCabinCrew((StaffCabin) emp);
            ((StaffCabin) emp).assignFlight(f);
            System.out.println("Cabin Crew assigned.");
        } else {
            System.out.println("Invalid employee type.");
        }
    }

    private static void addAircraft() {
        System.out.print("Enter Registration: ");
        String reg = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Capacity: ");
        int cap = Integer.parseInt(scanner.nextLine());
        Aircraft a = new Aircraft(reg, model, cap);
        manager.addAircraft(a);
        System.out.println("Aircraft added.");
    }

    private static void addAirport() {
        System.out.print("Enter Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        Airport a = new Airport(code, city, name);
        manager.addAirport(a);
        System.out.println("Airport added.");
    }

    private static void assignAircraftToFlight() {
        System.out.print("Enter Flight Number: ");
        int fNum = Integer.parseInt(scanner.nextLine());
        Flight f = manager.getFlightByNumber(fNum);
        if (f == null) { System.out.println("Flight not found."); return; }

        System.out.print("Enter Aircraft Registration: ");
        String reg = scanner.nextLine();
        
        Aircraft aircraft = null;
        for (Aircraft a : manager.getAircrafts()) {
            if (a.getRegistration().equalsIgnoreCase(reg)) {
                aircraft = a;
                break;
            }
        }

        if (aircraft == null) {
            System.out.println("Aircraft not found.");
            return;
        }

        if (aircraft.checkAvailability(f.getDepartureTime(), f.getArrivalDateTime())) {
            f.setAircraft(aircraft);
            aircraft.affectFlight(f);
            System.out.println("Aircraft assigned successfully.");
        } else {
            System.out.println("Aircraft is not available at this time.");
        }
    }

    // --- Helpers ---

    private static Passenger findPassenger(int id) {
        for (Passenger p : manager.getPassengers()) {
            if (p.getId() == id) return p;
        }
        System.out.println("Passenger not found.");
        return null;
    }

    private static Airport findAirport(String code) {
        for (Airport a : manager.getAirports()) {
            if (a.getCode().equalsIgnoreCase(code)) return a;
        }
        return null; 
    }
}
