package com.pokemonurpg.configuration.v1.pokemon.species.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesAttackRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpeciesAttackServiceTest {
    private final static String NAME = "NAME";
    private final static Attack ATTACK = new Attack();
    private final static Species SPECIES = new Species();

    @InjectMocks
    private SpeciesAttackService speciesAttackService;

    @Mock
    private SpeciesAttackRepository speciesAttackRepository;

    @Mock
    private AttackRepository attackRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        SpeciesAttack speciesAttack = mock(SpeciesAttack.class);

        SpeciesAttackInputDto input = new SpeciesAttackInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);
        when(speciesAttackRepository.findBySpeciesAndAttack(SPECIES, ATTACK)).thenReturn(speciesAttack);

        speciesAttackService.update(SPECIES, input);

        verify(speciesAttackRepository, times(1)).delete(speciesAttack);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        SpeciesAttack speciesAttack = mock(SpeciesAttack.class);

        SpeciesAttackInputDto input = new SpeciesAttackInputDto();
        input.setName(NAME);
        input.setDelete(false);

        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);
        when(speciesAttackRepository.findBySpeciesAndAttack(SPECIES, ATTACK)).thenReturn(speciesAttack);

        speciesAttackService.update(SPECIES, input);

        verify(speciesAttack, times(1)).update(input);
        verify(speciesAttackRepository, times(1)).save(speciesAttack);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        SpeciesAttackInputDto input = new SpeciesAttackInputDto();
        input.setName(NAME);

        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);

        speciesAttackService.update(SPECIES, input);

        verify(speciesAttackRepository, times(1)).save(Matchers.any());
    }
}