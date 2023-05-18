package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.configuration.v1.championslots.ChampionSlotService;
import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRecordRepository;
import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChampionSlotRecordServiceTest {
    private final static    Date                DATE                = new Date();
    private       static ChampionSlot championSlot;
    private final static    Integer             DBID                = 432;
    private final static    Member              OWNER               = mock(Member.class);
    private final static    String              CHAMPION_NAME       = "CHAMPION_NAME";
    private final static    String              LEAGUE_NAME         = "LEAGUE_NAME";
    private final static    String              OWNER_NAME          = "OWNER_NAME";
    private final static    String              TM_NAME             = "TM_NAME";

    private ChampionRecord term        = new ChampionRecord();

    @InjectMocks
    private ChampionRecordService championRecordService;

    @Mock
    private ChampionRecordRepository championRecordRepository;

    @Mock
    private ChampionSlotService championSlotService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private ChampionService championService;

    @Captor
    ArgumentCaptor<ChampionRecord> termCaptor;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Before
    public void init () {
        championSlot = mock(ChampionSlot.class);
    }

    @Test
    public void findByDbid() {
        when(championRecordRepository.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, championRecordService.findByDbid(DBID));
    }

    @Test
    public void findByChampionAndOwnerAndOpenDate() {
        when(championSlotService.findByName(CHAMPION_NAME)).thenReturn(championSlot);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        when(championRecordRepository.findBySlotAndOwnerAndOpenDate(championSlot, OWNER, DATE)).thenReturn(term);
        assertEquals(term, championRecordService.findBySlotAndOwnerAndOpenDate(CHAMPION_NAME, OWNER_NAME, DATE));
    }

    @Test
    public void create() {
        when(championSlotService.findByName(CHAMPION_NAME)).thenReturn(championSlot);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        ChampionRecordRequest input = new ChampionRecordRequest();
        input.setOpenDate(DATE);
        input.setSlot(CHAMPION_NAME);
        input.setOwner(OWNER_NAME);
        input.setBecomeCurrentOwner(true);

        ChampionRecord term = championRecordService.create(input);
        verify(championRecordRepository,
                times(1)).save(termCaptor.capture());

        ChampionRecord savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(DATE, term.getOpenDate());
        assertEquals(OWNER, term.getOwner());
        assertEquals(championSlot, term.getSlot());

        verify(championSlotService, times(1)).updateCurrentOwnerRecord(championSlot, term);
        verify(championService, times(1)).create(OWNER_NAME);
    }

    @Test
    public void update() {
        term.setSlot(championSlot);
        when(championRecordRepository.findByDbid(DBID)).thenReturn(term);

        ChampionRecordRequest input = new ChampionRecordRequest();
        input.setBecomeCurrentOwner(true);

        ChampionRecord term = championRecordService.update(input, DBID);
        verify(championRecordRepository,
                times(1)).save(termCaptor.capture());

        ChampionRecord savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);

        verify(championSlotService, times(1)).updateCurrentOwnerRecord(championSlot, term);
    }

    @Test
    public void delete() {
        when(championRecordRepository.findByDbid(DBID)).thenReturn(term);
        when(championSlotService.findByCurrentOwnerRecord(term)).thenReturn(championSlot);

        championRecordService.delete(DBID);

        verify(championSlot, times(1)).setCurrentOwnerRecord(termCaptor.capture());
        assertNull(termCaptor.getValue());

        verify(championRecordRepository, times(1)).deleteByDbid(intCaptor.capture());
        assertEquals(DBID, intCaptor.getValue());
    }

}