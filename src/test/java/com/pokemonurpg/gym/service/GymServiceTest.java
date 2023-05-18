package com.pokemonurpg.gym.service;

import com.pokemonurpg.configuration.v1.gyms.GymInputDto;
import com.pokemonurpg.entities.v1.Badge;
import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymOwnershipTerm;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymRepository;
import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;
import com.pokemonurpg.configuration.v1.gyms.GymPokemonService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GymServiceTest {
    private final static Badge              BADGE       = mock(Badge.class);
    private final static GymOwnershipTerm   TERM        = mock(GymOwnershipTerm.class);
    private final static Integer            TERM_DBID   = 32742;
    private final static Integer            DBID        = 32432;
    private final static List<String>       GYMS        = new ArrayList<>();
    private final static String             BADGE_NAME  = "BADGE";
    private final static String             NAME        = "TEST";
    private final static String             TYPE_NAME   = "TYPE_NAME";
    private final static Type               TYPE        = mock(Type.class);

    @InjectMocks
    private GymService gymService;

    @Mock
    private GymRepository gymRepository;

    @Mock
    private BadgeService badgeService;

    @Mock
    private GymOwnershipTermService gymOwnershipTermService;

    @Mock
    private GymPokemonService gymPokemonService;

    @Mock
    private TypeService typeService;

    private Gym gym = new Gym();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        when(gymRepository.findAllNames()).thenReturn(GYMS);
        assertEquals(GYMS, gymService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(gymRepository.findByDbid(DBID)).thenReturn(gym);
        assertEquals(gym, gymService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(gymRepository.findByName(NAME)).thenReturn(gym);
        assertEquals(gym, gymService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(gymRepository.findByName(NAME)).thenReturn(null);
        when(gymRepository.findFirstByNameStartingWith(NAME)).thenReturn(gym);
        assertEquals(gym, gymService.findByName(NAME));
    }

    @Test
    public void findByCurrentOwnershipTerm() {
        when(gymRepository.findByCurrentOwnerRecord(TERM)).thenReturn(gym);
        assertEquals(gym, gymService.findByCurrentOwnerRecord(TERM));
    }

    @Test
    public void create() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);

        Gym gym = gymService.create(input);
        assertEquals(NAME, gym.getName());
        verify(gymRepository, times(1)).save(gym);
        verify(gymPokemonService, times(1)).updateAll(input, gym);
    }

    @Test
    public void updateExistingRecord() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);
        input.setBadge(BADGE_NAME);
        input.setType(TYPE_NAME);
        input.setCurrentOwnerRecordDbid(TERM_DBID);

        when(gymRepository.findByDbid(DBID)).thenReturn(gym);
        when(badgeService.findByName(BADGE_NAME)).thenReturn(BADGE);
        when(typeService.findByName(TYPE_NAME)).thenReturn(TYPE);
        when(gymOwnershipTermService.findByDbid(TERM_DBID)).thenReturn(TERM);

        Gym gym1 = gymService.update(input, DBID);
        assertEquals(gym, gym1);
        assertEquals(NAME, gym1.getName());
        assertEquals(BADGE, gym1.getBadge());
        assertEquals(TYPE, gym1.getType());
        assertEquals(TERM, gym1.getCurrentOwnerRecord());
        verify(gymRepository, times(1)).save(gym1);
        verify(gymPokemonService, times(1)).updateAll(input, gym1);
    }

    @Test
    public void updateNonExistingRecord() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);

        when(gymRepository.findByDbid(DBID)).thenReturn(null);

        Gym gym1 = gymService.update(input, DBID);
        assertNull(gym1);
        verify(gymRepository, times(0)).save(Matchers.any());
    }

    @Test
    public void updateCurrentOwnershipRecord() {
        gymService.updateCurrentOwnerRecord(gym, TERM);
        assertEquals(TERM, gym.getCurrentOwnerRecord());
        verify(gymRepository, times(1)).save(gym);
    }

    @Test
    public void delete() {
        gymService.delete(DBID);
        verify(gymRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void updateEmbeddedValues_SetsOwnerRecordToNull_whenInputOwnerRecordIsNullAndRemoveOwnerIsTrue() {
        GymInputDto input = new GymInputDto();
        input.setRemoveOwner(true);

        Gym gym = new Gym();
        gym.setCurrentOwnerRecord(TERM);
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        gym.setPokemon(pokemon);

        gymService.updateEmbeddedValues(input, gym);
        assertNull(gym.getCurrentOwnerRecord());
        assertTrue(gym.getPokemon().isEmpty());
    }

}