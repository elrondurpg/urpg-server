package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.capturemethods.CaptureMethodRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaptureMethodTest {
    private final static Integer DBID = 324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        CaptureMethod captureMethod = new CaptureMethod();
        captureMethod.setDbid(DBID);
        captureMethod.setName(NAME);

        assertEquals(DBID, captureMethod.getDbid());
        assertEquals(NAME, captureMethod.getName());
    }

    @Test
    public void testConstructor() {
        CaptureMethodRequest input = new CaptureMethodRequest();
        input.setName(NAME);

        CaptureMethod captureMethod = new CaptureMethod(input);
        assertEquals(NAME, captureMethod.getName());
    }

}