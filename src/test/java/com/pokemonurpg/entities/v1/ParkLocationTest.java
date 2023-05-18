package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.parklocations.ParkLocationRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkLocationTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;

    @Test
    public void testPojo() {
        ParkLocation parkLocation = new ParkLocation();
        parkLocation.setDbid(DBID);
        parkLocation.setName(NAME);

        assertEquals(DBID, parkLocation.getDbid());
        assertEquals(NAME, parkLocation.getName());
    }

    @Test
    public void testConstructor() {
        ParkLocationRequest input = new ParkLocationRequest();
        input.setName(NAME);

        ParkLocation parkLocation = new ParkLocation(input);

        assertEquals(NAME, parkLocation.getName());
    }
}