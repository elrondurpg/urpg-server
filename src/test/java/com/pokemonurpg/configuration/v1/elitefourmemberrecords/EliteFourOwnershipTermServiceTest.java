package com.pokemonurpg.configuration.v1.elitefourmemberrecords;

import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourOwnershipTermService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
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
public class EliteFourOwnershipTermServiceTest {
    private final static    Date                DATE                = new Date();
    private       static    EliteFour           eliteFour;
    private final static    Integer             DBID                = 432;
    private final static    Member              OWNER               = mock(Member.class);
    private final static    String              ELITE_FOUR_NAME     = "ELITE_FOUR_NAME";
    private final static    String              LEAGUE_NAME         = "LEAGUE_NAME";
    private final static    String              OWNER_NAME          = "OWNER_NAME";
    private final static    String              TM_NAME             = "TM_NAME";

    private                 EliteFourOwnershipTerm    term        = new EliteFourOwnershipTerm();

    @InjectMocks
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @Mock
    private EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository;

    @Mock
    private EliteFourService eliteFourService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Captor
    ArgumentCaptor<EliteFourOwnershipTerm> termCaptor;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Before
    public void init () {
        eliteFour = mock(EliteFour.class);
    }

    @Test
    public void findByDbid() {
        when(eliteFourOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, eliteFourOwnershipTermService.findByDbid(DBID));
    }

    @Test
    public void findByEliteFourAndOwnerAndOpenDate() {
        when(eliteFourService.findByName(ELITE_FOUR_NAME)).thenReturn(eliteFour);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        when(eliteFourOwnershipTermRepository.findBySlotAndOwnerAndOpenDate(eliteFour, OWNER, DATE)).thenReturn(term);
        assertEquals(term, eliteFourOwnershipTermService.findBySlotAndOwnerAndOpenDate(ELITE_FOUR_NAME, OWNER_NAME, DATE));
    }

    @Test
    public void create() {
        when(eliteFourService.findByName(ELITE_FOUR_NAME)).thenReturn(eliteFour);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        EliteFourOwnershipTermInputDto input = new EliteFourOwnershipTermInputDto();
        input.setOpenDate(DATE);
        input.setSlot(ELITE_FOUR_NAME);
        input.setOwner(OWNER_NAME);
        input.setBecomeCurrentOwner(true);

        EliteFourOwnershipTerm term = eliteFourOwnershipTermService.create(input);
        verify(eliteFourOwnershipTermRepository,
                times(1)).save(termCaptor.capture());

        EliteFourOwnershipTerm savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(DATE, term.getOpenDate());
        assertEquals(OWNER, term.getOwner());
        assertEquals(eliteFour, term.getSlot());

        verify(eliteFourService, times(1)).updateCurrentOwnerRecord(eliteFour, term);
        verify(knownEliteFourMemberService, times(1)).create(OWNER_NAME);
    }

    @Test
    public void update() {
        term.setSlot(eliteFour);
        when(eliteFourOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);

        EliteFourOwnershipTermInputDto input = new EliteFourOwnershipTermInputDto();
        input.setBecomeCurrentOwner(true);

        EliteFourOwnershipTerm term = eliteFourOwnershipTermService.update(input, DBID);
        verify(eliteFourOwnershipTermRepository,
                times(1)).save(termCaptor.capture());

        EliteFourOwnershipTerm savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);

        verify(eliteFourService, times(1)).updateCurrentOwnerRecord(eliteFour, term);
    }

    @Test
    public void delete() {
        when(eliteFourOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        when(eliteFourService.findByCurrentOwnerRecord(term)).thenReturn(eliteFour);

        eliteFourOwnershipTermService.delete(DBID);

        verify(eliteFour, times(1)).setCurrentOwnerRecord(termCaptor.capture());
        assertNull(termCaptor.getValue());

        verify(eliteFourOwnershipTermRepository, times(1)).deleteByDbid(intCaptor.capture());
        assertEquals(DBID, intCaptor.getValue());
    }

}