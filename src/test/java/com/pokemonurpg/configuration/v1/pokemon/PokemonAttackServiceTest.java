package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAttack;
import com.pokemonurpg.infrastructure.v1.data.jpa.PokemonAttackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PokemonAttackServiceTest {
    private final static String NAME = "NAME";
    private final static Attack ATTACK = new Attack();
    private final static Pokemon POKEMON = new Pokemon();

    @InjectMocks
    private PokemonAttackService pokemonAttackService;

    @Mock
    private PokemonAttackRepository pokemonAttackRepository;

    @Mock
    private AttackRepository attackRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        PokemonAttack pokemonAttack = mock(PokemonAttack.class);

        PokemonAttackRequest input = new PokemonAttackRequest();
        input.setName(NAME);
        input.setDelete(true);

        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);
        when(pokemonAttackRepository.findBySpeciesAndAttack(POKEMON, ATTACK)).thenReturn(pokemonAttack);

        pokemonAttackService.update(POKEMON, input);

        verify(pokemonAttackRepository, times(1)).delete(pokemonAttack);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        PokemonAttack pokemonAttack = mock(PokemonAttack.class);

        PokemonAttackRequest input = new PokemonAttackRequest();
        input.setName(NAME);
        input.setDelete(false);

        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);
        when(pokemonAttackRepository.findBySpeciesAndAttack(POKEMON, ATTACK)).thenReturn(pokemonAttack);

        pokemonAttackService.update(POKEMON, input);

        verify(pokemonAttack, times(1)).update(input);
        verify(pokemonAttackRepository, times(1)).save(pokemonAttack);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        PokemonAttackRequest input = new PokemonAttackRequest();
        input.setName(NAME);

        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);

        pokemonAttackService.update(POKEMON, input);
    }
}