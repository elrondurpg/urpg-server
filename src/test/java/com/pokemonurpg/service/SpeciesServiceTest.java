package com.pokemonurpg.service;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.repository.SpeciesRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SpeciesServiceTest {
    /* TODO reinstate

    private static final String POKEMON_NAME="Pikachu";
    private static final Integer POKEMON_DBID = 808;

    private static final String ATTACK_NAME="Fire Blast";
    private static final Integer ATTACK_DBID = 777;
    private static final String ATTACK_METHOD="LEVEL-UP";

    private static final String ATTACK_NAME_ALT="Flamethrower";
    private static final Integer ATTACK_DBID_ALT=999;
    private static final String ATTACK_METHOD_ALT="HM";

    private SpeciesService speciesService;

    private SpeciesRepository speciesRepository = mock(SpeciesRepository.class);

    private SpeciesAttackService speciesAttackService = mock(SpeciesAttackService.class);

    private AttackService attackService = mock(AttackService.class);

    private SpeciesAbilityService speciesAbilityService = mock(SpeciesAbilityService.class);
    private AbilityService abilityService = mock(AbilityService.class);

    @Before
    public void initService() {
        speciesService = new SpeciesService(speciesRepository, speciesAttackService, attackService, speciesAbilityService, abilityService);
    }

    public Species createExistingSpeciesObject() {
        Species existingSpecies = mock(Species.class);
        when(existingSpecies.getDbid()).thenReturn(POKEMON_DBID);
        when(existingSpecies.getName()).thenReturn(POKEMON_NAME);
        when(speciesRepository.findByName(POKEMON_NAME)).thenReturn(Optional.of(existingSpecies));
        when(existingSpecies.getAttacks()).thenReturn(new ArrayList<>());

        return existingSpecies;
    }

    public Attack createExistingAttackObject() {
        Attack existingAttack = mock(Attack.class);
        when(existingAttack.getDbid()).thenReturn(ATTACK_DBID);
        when(attackService.findByName(ATTACK_NAME)).thenReturn(Optional.of(existingAttack));

        return existingAttack;
    }

    public Species createRequestedSpeciesObject(SpeciesAttack... speciesAttacks) {
        Species requestedSpecies = mock(Species.class);
        when(requestedSpecies.getAttacks()).thenReturn(new ArrayList<>(Arrays.asList(speciesAttacks)));
        when(requestedSpecies.getName()).thenReturn(POKEMON_NAME);
        return requestedSpecies;
    }

    public SpeciesAttack createGenericSpeciesAttackObject() {
        SpeciesAttack speciesAttack = new SpeciesAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        speciesAttack.setAttack(attack);
        speciesAttack.setMethod(ATTACK_METHOD);

        return speciesAttack;
    }

    @Test
    public void saveExistingSpeciesSavesNewAttacks() {
        Species existingSpecies = createExistingSpeciesObject();
        Attack existingAttack = createExistingAttackObject();

        SpeciesAttack requestedAttack = createGenericSpeciesAttackObject();
        Species requestedSpecies = createRequestedSpeciesObject(requestedAttack);

        speciesService.save(requestedSpecies);
        verify(speciesRepository, times(1)).save(requestedSpecies);
        verify(speciesAttackService, times(1)).save(any(SpeciesAttack.class));
        assertEquals(existingSpecies.getDbid(), requestedAttack.internalGetId().getSpeciesDbid());
        assertEquals(existingAttack.getDbid(), requestedAttack.internalGetId().getAttackDbid());
    }

    @Test
    public void saveExistingSpeciesDoesntSaveAttacksThatDontExist() {
        Species existingSpecies = createExistingSpeciesObject();

        SpeciesAttack requestedAttack = createGenericSpeciesAttackObject();
        Species requestedSpecies = createRequestedSpeciesObject(requestedAttack);

        speciesService.save(requestedSpecies);
        verify(speciesRepository, times(1)).save(requestedSpecies);
        verify(speciesAttackService, times(0)).save(any(SpeciesAttack.class));
        assertEquals(null, requestedAttack.internalGetId());
    }

    @Test
    public void getCurrentAttacksSuccessPath() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        SpeciesAttack speciesAttack = createGenericSpeciesAttackObject();
        speciesAttacks.add(speciesAttack);

        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(existingSpecies);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.containsKey(ATTACK_NAME));
    }

    @Test
    public void getCurrentAttacksReturnsEmptyMapWhenSpeciesIsNull() {
        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(null);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.isEmpty());
    }

    @Test
    public void getCurrentAttacksReturnsEmptyMapWhenSpeciesAttacksIsNull() {
        Species existingSpecies = createExistingSpeciesObject();
        when(existingSpecies.getAttacks()).thenReturn(null);

        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(existingSpecies);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.isEmpty());
    }

    @Test
    public void getCurrentAttacksReturnsEmptyMapWhenSpeciesAttacksIsEmpty() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(existingSpecies);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.isEmpty());
    }

    @Test
    public void getCurrentAttacksReturnsEmptyMapWhenSpeciesIsNew() {
        Species species = new Species();
        String name = "TestName";
        species.setName(name);

        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(species);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.isEmpty());
    }

    @Test
    public void getCurrentAttacksReturnsEmptyMapWhenSpeciesAttackHasNoAttack() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        speciesAttacks.add(speciesAttack);
        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(existingSpecies);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.isEmpty());
    }

    @Test
    public void getCurrentAttacksReturnsEmptyMapWhenAttackHasNoName() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        speciesAttack.setAttack(new Attack());
        speciesAttacks.add(speciesAttack);
        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> currentAttacks = speciesService.getCurrentAttacks(existingSpecies);
        assertNotNull(currentAttacks);
        assertTrue(currentAttacks.isEmpty());
    }

    @Test
    public void getNewAttacksSuccessPath() {
        Species species = mock(Species.class);

        List<SpeciesAttack> speciesAttacks = new ArrayList<>();
        SpeciesAttack speciesAttack = createGenericSpeciesAttackObject();
        speciesAttacks.add(speciesAttack);

        when(species.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> newAttacks = speciesService.getNewAttacks(species);
        assertNotNull(newAttacks);
        assertTrue(newAttacks.containsKey(ATTACK_NAME));
    }

    @Test
    public void getNewAttacksReturnsEmptyMapWhenSpeciesIsNull() {
        Map<String, SpeciesAttack> newAttacks = speciesService.getNewAttacks(null);
        assertNotNull(newAttacks);
        assertTrue(newAttacks.isEmpty());
    }

    @Test
    public void getNewAttacksReturnsEmptyMapWhenSpeciesAttacksIsNull() {
        Species existingSpecies = createExistingSpeciesObject();
        when(existingSpecies.getAttacks()).thenReturn(null);

        Map<String, SpeciesAttack> newAttacks = speciesService.getNewAttacks(existingSpecies);
        assertNotNull(newAttacks);
        assertTrue(newAttacks.isEmpty());
    }

    @Test
    public void getNewAttacksReturnsEmptyMapWhenSpeciesAttacksIsEmpty() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> newAttacks = speciesService.getNewAttacks(existingSpecies);
        assertNotNull(newAttacks);
        assertTrue(newAttacks.isEmpty());
    }

    @Test
    public void getNewAttacksReturnsEmptyMapWhenSpeciesAttackHasNoAttack() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        speciesAttacks.add(speciesAttack);
        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> newAttacks = speciesService.getNewAttacks(existingSpecies);
        assertNotNull(newAttacks);
        assertTrue(newAttacks.isEmpty());
    }

    @Test
    public void getNewAttacksReturnsEmptyMapWhenAttackHasNoName() {
        Species existingSpecies = createExistingSpeciesObject();

        List<SpeciesAttack> speciesAttacks = new ArrayList<> ();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        speciesAttack.setAttack(new Attack());
        speciesAttacks.add(speciesAttack);
        when(existingSpecies.getAttacks()).thenReturn(speciesAttacks);

        Map<String, SpeciesAttack> newAttacks = speciesService.getNewAttacks(existingSpecies);
        assertNotNull(newAttacks);
        assertTrue(newAttacks.isEmpty());
    }
    */

}