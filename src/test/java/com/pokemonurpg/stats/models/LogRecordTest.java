package com.pokemonurpg.stats.models;

import com.pokemonurpg.entities.v1.LogRecord;
import com.pokemonurpg.entities.v1.Member;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LogRecordTest {
    private final static Integer DBID = 432;
    private final static Member MEMBER = mock(Member.class);
    private final static Member CREATOR = mock(Member.class);
    private final static String MESSAGE = "MESSAGE";

    @Test
    public void testPojo() {
        LogRecord logRecord = new LogRecord();
        logRecord.setDbid(DBID);
        assertEquals(DBID, logRecord.getDbid());
    }

    @Test
    public void testConstructor() {
        LogRecord logRecord = new LogRecord(MEMBER, CREATOR, MESSAGE);
        assertEquals(MEMBER, logRecord.getMember());
        assertEquals(CREATOR, logRecord.getCreator());
        assertEquals(MESSAGE, logRecord.getMessage());
        assertNotNull(logRecord.getTimestamp());
    }

}