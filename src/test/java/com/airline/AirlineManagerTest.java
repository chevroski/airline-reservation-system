package com.airline;

import com.airline.service.AirlineManager;
import com.airline.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class AirlineManagerTest {

    private AirlineManager manager;

    @BeforeEach
    public void setUp() {
        manager = AirlineManager.getInstance();
        // Since it's a singleton, we might need to clear data if we want isolation,
        // but the class doesn't expose a clear method. 
        // For this test, we will just add unique items.
    }

    @Test
    public void testSingleton() {
        AirlineManager anotherInstance = AirlineManager.getInstance();
        assertSame(manager, anotherInstance);
    }

    @Test
    public void testFlightCRUD() {
        Flight f = new Flight(999, new Airport("X", "X", "X"), new Airport("Y", "Y", "Y"), LocalDateTime.now(), LocalDateTime.now());
        manager.addFlight(f);
        
        assertEquals(f, manager.getFlightByNumber(999));
        
        manager.removeFlight(f);
        assertNull(manager.getFlightByNumber(999));
    }

    @Test
    public void testPassengerCRUD() {
        Passenger p = new Passenger(999, "Test Passenger", "Addr", "Contact", "P999");
        manager.addPassenger(p);
        
        assertTrue(manager.getPassengers().contains(p));
        
        manager.removePassenger(p);
        assertFalse(manager.getPassengers().contains(p));
    }
}
