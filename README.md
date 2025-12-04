# Airline Reservation System - Project II.1102

## General Information
This project is a comprehensive Airline Reservation System developed in Java, strictly adhering to Object-Oriented Programming (OOP) principles. It manages customers, crew, flights, aircraft, and reservations.

## How to Run the Project

### Prerequisites
- Java JDK 17 or higher
- Maven (optional, but recommended)

### Option 1: Using Maven (Recommended)
1.  **Compile**:
    ```bash
    mvn compile
    ```
2.  **Run the Application**:
    ```bash
    mvn exec:java -Dexec.mainClass="com.airline.Main"
    ```
3.  **Run Unit Tests**:
    ```bash
    mvn test
    ```

### Option 2: Manual Compilation (If Maven is not available)
1.  **Compile**:
    ```bash
    javac -d out -sourcepath src/main/java src/main/java/com/airline/Main.java
    ```
2.  **Run**:
    ```bash
    java -cp out com.airline.Main
    ```

## Project Functionalities
The application provides a Console Interface (CLI) with the following features:

1.  **Flight Management**:
    -   Plan flights (`planFlight`).
    -   Assign aircraft and crew (`assignFlight`, `affectFlight`).
    -   Check availability (`checkAvailability`).
2.  **Passenger & Reservation Management**:
    -   Register passengers.
    -   Book flights (`bookFlight`).
    -   Cancel reservations (`cancelBook`).
    -   View reservation details (`getReservations`).
3.  **Staff Management**:
    -   Manage Pilots and Cabin Crew.
    -   Assign roles and flights (`getRole`, `obtainVol`).

## Bonus Features (Advanced)
We have implemented the **Statistics and Reports** module as requested for the bonus:
-   **Flight Reports**: Total number of flights and passengers.
-   **Revenue Calculation**: Estimated revenue generation based on bookings.
-   **Popular Destinations**: Ranking of destinations by passenger volume.

## Compliance with Requirements
We have strictly followed the project guidelines:

| Requirement | Implementation Details |
| :--- | :--- |
| **OOP Concepts** | Used Inheritance (`Person` -> `Employee`/`Passenger`), Encapsulation (private fields), and Polymorphism. |
| **Class Structure** | Implemented all classes from the diagram: `Person`, `Employee`, `Passenger`, `AirplanePilot`, `StaffCabin`, `Aircraft`, `Airport`, `Flight`, `Reservation` (Book). |
| **Specific Methods** | Implemented mandatory methods: `getInfos()`, `getRole()`, `bookFlight()`, `cancelBook()`, `assignFlight()`, `obtainVol()`, etc. |
| **Data Structures** | Used `ArrayList` and `HashMap` for efficient data management. |
| **Tools** | Project structure is Maven-compatible. |

## Architecture
-   **`com.airline.model`**: Contains all domain entities.
-   **`com.airline.service`**: Contains `AirlineManager` (Singleton) for data persistence and `StatisticsService` for reports.
-   **`com.airline.Main`**: Entry point with an interactive menu.
