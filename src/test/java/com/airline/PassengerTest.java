package com.airline;

import com.airline.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class PassengerTest {

    @Test
    public void testBooking() {
        Airport origin = new Airport("JFK", "New York", "Desc");
        Airport dest = new Airport("LHR", "London", "Desc");
        Flight flight = new Flight(101, origin, dest, LocalDateTime.now(), LocalDateTime.now().plusHours(7));
        Passenger passenger = new Passenger(1, "Test User", "Address", "Contact", "P123");

        passenger.bookFlight(flight);

        assertEquals(1, passenger.getReservations().size());
        assertEquals(1, flight.listingPassenger().size());
        assertEquals(flight, passenger.getReservations().get(0).getFlight());
    }

    @Test
    public void testCancelBooking() {
        Airport origin = new Airport("JFK", "New York", "Desc");
        Airport dest = new Airport("LHR", "London", "Desc");
        Flight flight = new Flight(101, origin, dest, LocalDateTime.now(), LocalDateTime.now().plusHours(7));
        Passenger passenger = new Passenger(1, "Test User", "Address", "Contact", "P123");

        passenger.bookFlight(flight);
        int reservationId = passenger.getReservations().get(0).getReservationNumber();
        
        passenger.cancelBook(reservationId);
        
        assertEquals(0, passenger.getReservations().size());
    }
}
