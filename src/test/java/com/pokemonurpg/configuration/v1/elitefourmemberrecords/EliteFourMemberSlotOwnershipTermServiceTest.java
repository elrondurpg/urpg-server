package com.pokemonurpg.configuration.v1.elitefourmemberrecords;

import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordRequest;
import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberRecordRepository;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
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
public class EliteFourMemberSlotOwnershipTermServiceTest {
    private final static    Date                DATE                = new Date();
    private       static EliteFourMemberSlot eliteFourMemberSlot;
    private final static    Integer             DBID                = 432;
    private final static    Member              OWNER               = mock(Member.class);
    private final static    String              ELITE_FOUR_NAME     = "ELITE_FOUR_NAME";
    private final static    String              LEAGUE_NAME         = "LEAGUE_NAME";
    private final static    String              OWNER_NAME          = "OWNER_NAME";
    private final static    String              TM_NAME             = "TM_NAME";

    private EliteFourMemberRecord term        = new EliteFourMemberRecord();

    @InjectMocks
    private EliteFourRecordService eliteFourRecordService;

    @Mock
    private EliteFourMemberRecordRepository eliteFourMemberRecordRepository;

    @Mock
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private EliteFourMemberService eliteFourMemberService;

    @Captor
    ArgumentCaptor<EliteFourMemberRecord> termCaptor;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Before
    public void init () {
        eliteFourMemberSlot = mock(EliteFourMemberSlot.class);
    }

    @Test
    public void findByDbid() {
        when(eliteFourMemberRecordRepository.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, eliteFourRecordService.findByDbid(DBID));
    }

    @Test
    public void findByEliteFourAndOwnerAndOpenDate() {
        when(eliteFourMemberSlotService.findByName(ELITE_FOUR_NAME)).thenReturn(eliteFourMemberSlot);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        when(eliteFourMemberRecordRepository.findBySlotAndOwnerAndOpenDate(eliteFourMemberSlot, OWNER, DATE)).thenReturn(term);
        assertEquals(term, eliteFourRecordService.findBySlotAndOwnerAndOpenDate(ELITE_FOUR_NAME, OWNER_NAME, DATE));
    }

    @Test
    public void create() {
        when(eliteFourMemberSlotService.findByName(ELITE_FOUR_NAME)).thenReturn(eliteFourMemberSlot);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        EliteFourRecordRequest input = new EliteFourRecordRequest();
        input.setOpenDate(DATE);
        input.setSlot(ELITE_FOUR_NAME);
        input.setOwner(OWNER_NAME);
        input.setBecomeCurrentOwner(true);

        EliteFourMemberRecord term = eliteFourRecordService.create(input);
        verify(eliteFourMemberRecordRepository,
                times(1)).save(termCaptor.capture());

        EliteFourMemberRecord savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(DATE, term.getOpenDate());
        assertEquals(OWNER, term.getOwner());
        assertEquals(eliteFourMemberSlot, term.getSlot());

        verify(eliteFourMemberSlotService, times(1)).updateCurrentOwnerRecord(eliteFourMemberSlot, term);
        verify(eliteFourMemberService, times(1)).create(OWNER_NAME);
    }

    @Test
    public void update() {
        term.setSlot(eliteFourMemberSlot);
        when(eliteFourMemberRecordRepository.findByDbid(DBID)).thenReturn(term);

        EliteFourRecordRequest input = new EliteFourRecordRequest();
        input.setBecomeCurrentOwner(true);

        EliteFourMemberRecord term = eliteFourRecordService.update(input, DBID);
        verify(eliteFourMemberRecordRepository,
                times(1)).save(termCaptor.capture());

        EliteFourMemberRecord savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);

        verify(eliteFourMemberSlotService, times(1)).updateCurrentOwnerRecord(eliteFourMemberSlot, term);
    }

    @Test
    public void delete() {
        when(eliteFourMemberRecordRepository.findByDbid(DBID)).thenReturn(term);
        when(eliteFourMemberSlotService.findByCurrentOwnerRecord(term)).thenReturn(eliteFourMemberSlot);

        eliteFourRecordService.delete(DBID);

        verify(eliteFourMemberSlot, times(1)).setCurrentOwnerRecord(termCaptor.capture());
        assertNull(termCaptor.getValue());

        verify(eliteFourMemberRecordRepository, times(1)).deleteByDbid(intCaptor.capture());
        assertEquals(DBID, intCaptor.getValue());
    }

}