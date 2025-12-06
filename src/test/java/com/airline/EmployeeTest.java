package com.airline;

import com.airline.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testAirplanePilot() {
        AirplanePilot pilot = new AirplanePilot(1, "Maverick", "Top Gun", "123", 101, LocalDate.now(), "LIC-001", 5000);
        
        // Test getters inherited from Person/Employee
        assertEquals("Maverick", pilot.getName());
        assertEquals(101, pilot.getNumberEmp());

        // Test flight assignment
        Flight flight = new Flight(101, new Airport("A", "A", "A"), new Airport("B", "B", "B"), LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        pilot.assignFlight(flight);
        
        List<Flight> flights = pilot.obtainVol();
        assertEquals(1, flights.size());
        assertEquals(flight, flights.get(0));
    }

    @Test
    public void testStaffCabin() {
        StaffCabin staff = new StaffCabin(2, "Penny", "Big Bang", "456", 102, LocalDate.now(), "Senior");
        
        // Test getters
        assertEquals("Penny", staff.getName());

        // Test flight assignment
        Flight flight = new Flight(102, new Airport("C", "C", "C"), new Airport("D", "D", "D"), LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        staff.assignFlight(flight);
        
        List<Flight> flights = staff.obtainVol();
        assertEquals(1, flights.size());
        assertEquals(flight, flights.get(0));
    }
}
