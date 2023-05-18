package com.pokemonurpg.configuration.v1.gyms;

import com.pokemonurpg.entities.v1.*;
import fakes.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GymServiceTest {
    private final static Badge              BADGE       = mock(Badge.class);
    private final static GymLeaderRecord TERM        = mock(GymLeaderRecord.class);
    private final static Integer            TERM_DBID   = 32742;
    private final static Integer            DBID        = 32432;
    private final static List<String>       GYMS        = new ArrayList<>();
    private final static String             BADGE_NAME  = "BADGE";
    private final static String             NAME        = "TEST";
    private final static String             TYPE_NAME   = "TYPE_NAME";
    private final static Type               TYPE        = mock(Type.class);

    private GymService gymService;
    private FakeGymRepository gymRepository;
    private FakeBadgeRepository badgeRepository;
    private FakeTypeRepository typeRepository;
    private FakeGymRecordRepository gymRecordRepository;
    private FakeOwnedPokemonRepository ownedPokemonRepository;

    private final Gym gym = new Gym();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Before
    public void setup() {
        gymRepository = new FakeGymRepository();
        badgeRepository = new FakeBadgeRepository();
        ownedPokemonRepository = new FakeOwnedPokemonRepository();
        typeRepository = new FakeTypeRepository();
        gymRecordRepository = new FakeGymRecordRepository();
        gymService = new GymService(gymRepository, badgeRepository, typeRepository, gymRecordRepository, ownedPokemonRepository);
    }

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        Gym gym = new Gym();
        gym.setName(NAME);
        gymRepository.save(gym);

        List<String> names = gymService.findAllNames();

        assertEquals(1, names.size());
        assertEquals(NAME, names.get(0));
    }

    @Test
    public void findByDbid() {
        Gym gym = new Gym();
        gym.setDbid(DBID);
        gymRepository.save(gym);

        Gym response = gymService.findByDbid(DBID);

        assertEquals(gym, response);
    }

    @Test
    public void findByNameExactMatch() {
        Gym gym = new Gym();
        gym.setName(NAME);
        gymRepository.save(gym);

        Gym response = gymService.findByName(NAME);

        assertEquals(gym, response);
    }

    @Test
    public void findByNameNotExactMatch() {
        Gym gym = new Gym();
        gym.setName(NAME + NAME);
        gymRepository.save(gym);

        Gym response = gymService.findByName(NAME);

        assertEquals(gym, response);
    }

    @Test
    public void findByCurrentOwnershipTerm() {
        GymLeaderRecord record = new GymLeaderRecord();
        Gym gym = new Gym();
        gym.setCurrentOwnerRecord(record);
        gymRepository.save(gym);

        Gym response = gymService.findByCurrentOwnerRecord(record);

        assertEquals(gym, response);
    }

    @Test
    public void create() {
        GymRequest input = new GymRequest();
        input.setName(NAME);

        Gym gym = gymService.create(input);

        assertNotNull(gym);
        assertEquals(NAME, gym.getName());
    }

    @Test
    public void shouldUpdateExistingRecord() {
        Gym gym = new Gym();
        gym.setDbid(DBID);
        gymRepository.save(gym);

        GymLeaderRecord record = new GymLeaderRecord();
        record.setDbid(TERM_DBID);
        gymRecordRepository.save(record);

        Badge badge = new Badge();
        badge.setName(BADGE_NAME);
        badgeRepository.save(badge);

        Type type = new Type();
        type.setName(TYPE_NAME);
        typeRepository.save(type);

        GymRequest input = new GymRequest();
        input.setName(NAME);
        input.setBadge(BADGE_NAME);
        input.setType(TYPE_NAME);
        input.setCurrentOwnerRecordDbid(TERM_DBID);

        Gym response = gymService.update(DBID, input);
        assertEquals(gym, response);
        assertEquals(NAME, response.getName());
        assertEquals(badge, response.getBadge());
        assertEquals(type, response.getType());
        assertEquals(record, response.getCurrentOwnerRecord());
    }

    @Test
    public void updateNonExistingRecord() {
        GymRequest input = new GymRequest();

        Gym response = gymService.update(DBID, input);

        assertNull(response);
    }

    @Test
    public void delete() {
        Gym gym = new Gym();
        gym.setDbid(DBID);
        gymRepository.save(gym);

        gymService.delete(DBID);

        assertNull(gymRepository.findByDbid(DBID));
    }

    @Test
    public void updateEmbeddedValues_SetsOwnerRecordToNull_whenInputOwnerRecordIsNullAndRemoveOwnerIsTrue() {
        GymRequest input = new GymRequest();
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