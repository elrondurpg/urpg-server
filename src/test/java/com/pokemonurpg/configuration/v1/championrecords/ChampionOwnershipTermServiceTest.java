package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.configuration.v1.championrecords.ChampionOwnershipTermInputDto;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.championrecords.ChampionOwnershipTermService;
import com.pokemonurpg.configuration.v1.championslots.ChampionService;
import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
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
public class ChampionOwnershipTermServiceTest {
    private final static    Date                DATE                = new Date();
    private       static    Champion            champion;
    private final static    Integer             DBID                = 432;
    private final static    Member              OWNER               = mock(Member.class);
    private final static    String              CHAMPION_NAME       = "CHAMPION_NAME";
    private final static    String              LEAGUE_NAME         = "LEAGUE_NAME";
    private final static    String              OWNER_NAME          = "OWNER_NAME";
    private final static    String              TM_NAME             = "TM_NAME";

    private                 ChampionOwnershipTerm    term        = new ChampionOwnershipTerm();

    @InjectMocks
    private ChampionOwnershipTermService championOwnershipTermService;

    @Mock
    private ChampionOwnershipTermRepository championOwnershipTermRepository;

    @Mock
    private ChampionService championService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private KnownChampionService knownChampionService;

    @Captor
    ArgumentCaptor<ChampionOwnershipTerm> termCaptor;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Before
    public void init () {
        champion = mock(Champion.class);
    }

    @Test
    public void findByDbid() {
        when(championOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, championOwnershipTermService.findByDbid(DBID));
    }

    @Test
    public void findByChampionAndOwnerAndOpenDate() {
        when(championService.findByName(CHAMPION_NAME)).thenReturn(champion);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        when(championOwnershipTermRepository.findBySlotAndOwnerAndOpenDate(champion, OWNER, DATE)).thenReturn(term);
        assertEquals(term, championOwnershipTermService.findBySlotAndOwnerAndOpenDate(CHAMPION_NAME, OWNER_NAME, DATE));
    }

    @Test
    public void create() {
        when(championService.findByName(CHAMPION_NAME)).thenReturn(champion);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);

        ChampionOwnershipTermInputDto input = new ChampionOwnershipTermInputDto();
        input.setOpenDate(DATE);
        input.setSlot(CHAMPION_NAME);
        input.setOwner(OWNER_NAME);
        input.setBecomeCurrentOwner(true);

        ChampionOwnershipTerm term = championOwnershipTermService.create(input);
        verify(championOwnershipTermRepository,
                times(1)).save(termCaptor.capture());

        ChampionOwnershipTerm savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);
        assertEquals(DATE, term.getOpenDate());
        assertEquals(OWNER, term.getOwner());
        assertEquals(champion, term.getSlot());

        verify(championService, times(1)).updateCurrentOwnerRecord(champion, term);
        verify(knownChampionService, times(1)).create(OWNER_NAME);
    }

    @Test
    public void update() {
        term.setSlot(champion);
        when(championOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);

        ChampionOwnershipTermInputDto input = new ChampionOwnershipTermInputDto();
        input.setBecomeCurrentOwner(true);

        ChampionOwnershipTerm term = championOwnershipTermService.update(input, DBID);
        verify(championOwnershipTermRepository,
                times(1)).save(termCaptor.capture());

        ChampionOwnershipTerm savedObject = termCaptor.getValue();
        assertEquals(term, savedObject);

        verify(championService, times(1)).updateCurrentOwnerRecord(champion, term);
    }

    @Test
    public void delete() {
        when(championOwnershipTermRepository.findByDbid(DBID)).thenReturn(term);
        when(championService.findByCurrentOwnerRecord(term)).thenReturn(champion);

        championOwnershipTermService.delete(DBID);

        verify(champion, times(1)).setCurrentOwnerRecord(termCaptor.capture());
        assertNull(termCaptor.getValue());

        verify(championOwnershipTermRepository, times(1)).deleteByDbid(intCaptor.capture());
        assertEquals(DBID, intCaptor.getValue());
    }

}