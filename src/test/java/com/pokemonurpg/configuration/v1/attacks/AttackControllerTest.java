package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.configuration.v1.attacks.AttackController;
import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.configuration.v1.attacks.AttackInputDto;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AttackControllerTest {
    private static final String ATTACK_NAME = "TEST";
    private static final Integer DBID = 23423;

    @InjectMocks
    private AttackController attackController;

    @Mock
    private AttackService attackService;

    @Mock
    private Attack attack;

    @Test
    public void findReturnsResultFromAttackService() {
        when(attackService.findByName(ATTACK_NAME)).thenReturn(attack);
        Attack attack = attackController.findByName(ATTACK_NAME);
        assertNotNull(attack);
    }

    @Test
    public void createReturnsResultFromAttackService() {
        AttackInputDto input = new AttackInputDto();
        Attack attack = new Attack();

        when (attackService.create(input)).thenReturn(attack);
        assertEquals(attack, attackController.create(input));
    }

    @Test
    public void updateReturnsResultFromAttackService() {
        AttackInputDto input = new AttackInputDto();
        Attack attack = new Attack();

        when (attackService.update(input, DBID)).thenReturn(attack);
        assertEquals(attack, attackController.update(input, DBID));
    }

    @Test
    public void getAllAttacks() {
        List<String> names = new ArrayList<>();
        when(attackService.findAllNames()).thenReturn(names);

        assertEquals(names, attackController.findAllNames());
    }
}