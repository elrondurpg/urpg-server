package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.LogRecord;
import com.pokemonurpg.infrastructure.v1.data.jpa.LogRecordRepository;
import com.pokemonurpg.stats.v1.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {
    private final static List<LogRecord> LOGS = new ArrayList<>();
    private final static Member MEMBER = mock(Member.class);
    private final static Member CREATOR = mock(Member.class);
    private final static String SHORT_MESSAGE = "SHORT_MESSAGE";
    private final static String LONG_MESSAGE = "abcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefghabcdefgh";

    @InjectMocks
    private LogService logService;

    @Mock
    private LogRecordRepository logRecordRepository;

    @Captor
    private ArgumentCaptor<LogRecord> captor;

    @Test
    public void findByMember() {
        when(logRecordRepository.findByMember(MEMBER)).thenReturn(LOGS);
        assertEquals(LOGS, logService.findByMember(MEMBER));
    }

    @Test
    public void findByCreator() {
        when(logRecordRepository.findByCreator(CREATOR)).thenReturn(LOGS);
        assertEquals(LOGS, logService.findByCreator(CREATOR));
    }

    @Test
    public void createShortMessage() {
        logService.create(MEMBER, CREATOR, SHORT_MESSAGE);
        verify(logRecordRepository).save(captor.capture());
        assertEquals(MEMBER, captor.getValue().getMember());
        assertEquals(CREATOR, captor.getValue().getCreator());
        assertEquals(SHORT_MESSAGE, captor.getValue().getMessage());
    }

    @Test
    public void createLongMessage() {
        logService.create(MEMBER, CREATOR, LONG_MESSAGE);

        verify(logRecordRepository, times(2)).save(captor.capture());
        assertEquals(MEMBER, captor.getAllValues().get(0).getMember());
        assertEquals(CREATOR, captor.getAllValues().get(0).getCreator());
        assertEquals(LONG_MESSAGE.substring(0, 253) + "...", captor.getAllValues().get(0).getMessage());

        assertEquals(MEMBER, captor.getAllValues().get(1).getMember());
        assertEquals(CREATOR, captor.getAllValues().get(1).getCreator());
        assertEquals(LONG_MESSAGE.substring(253), captor.getAllValues().get(1).getMessage());

    }
}