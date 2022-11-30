package com.pokemonurpg.stats.models;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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