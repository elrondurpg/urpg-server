package com.pokemonurpg.stats.service;

import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.attack.attack.service.AttackService;
import com.pokemonurpg.stats.input.OwnedExtraMoveInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OwnedExtraMoveServiceTest {
    private final static Attack ATTACK1 = mock(Attack.class);
    private final static Attack ATTACK2 = mock(Attack.class);
    private final static String ATTACK_NAME1 = "ATTACK_NAME1";
    private final static String ATTACK_NAME2 = "ATTACK_NAME2";

    @InjectMocks
    private OwnedExtraMoveService ownedExtraMoveService;

    @Mock
    private AttackService attackService;

    @Test
    public void updateAll() {
        // Given an OwnedExtraMoveInputDto with attack AttackName and Delete = false;
        OwnedExtraMoveInputDto input1 = new OwnedExtraMoveInputDto();
        input1.setAttack(ATTACK_NAME1);
        input1.setDelete(false);

        // Given an OwnedExtraMoveInputDto with attack AttackName2 and Delete = true;
        OwnedExtraMoveInputDto input2 = new OwnedExtraMoveInputDto();
        input2.setAttack(ATTACK_NAME2);
        input2.setDelete(true);

        // Given an OwnedPokemonInputDto that contains those extra move inputs
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        input.setOwnedExtraMoves(Arrays.asList(input1, input2));

        // Given an OwnedPokemon with 'attacks' containing attack2
        OwnedPokemon ownedPokemon = new OwnedPokemon();
        Set<Attack> attacks = new HashSet<>();
        attacks.add(ATTACK2);
        ownedPokemon.setOwnedExtraMoves(attacks);

        // Given an attack service with ATTACK_NAME1 => ATTACK1 and ATTACK_NAME2 => ATTACK2
        when(attackService.findByName(ATTACK_NAME1)).thenReturn(ATTACK1);
        when(attackService.findByName(ATTACK_NAME2)).thenReturn(ATTACK2);

        // when I call updateAll
        ownedExtraMoveService.updateAll(input, ownedPokemon);

        // Then the pokemon's owned extra moves set will contain attack1 and not attack2
        assertTrue(ownedPokemon.getOwnedExtraMoves().contains(ATTACK1));
        assertFalse(ownedPokemon.getOwnedExtraMoves().contains(ATTACK2));

    }

}