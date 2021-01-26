package com.pokemonurpg.contest.models;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.contest.input.ContestComboInputDto;
import com.pokemonurpg.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAttack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestComboTest {
    private final static Attack FIRST_ATTACK = new Attack();
    private final static String SECOND_ATTACK_NAME = "Flamethrower";
    private final static Attack SECOND_ATTACK = new Attack();
    private final static String CONTEST_TYPE = "RSE";
    private final static Boolean OVERPOWERED = true;

    @Test
    public void testPojo() {
        ContestCombo contestCombo = new ContestCombo();
        contestCombo.setFirstAttack(FIRST_ATTACK);
        contestCombo.setSecondAttack(SECOND_ATTACK);
        contestCombo.setOverpowered(OVERPOWERED);

        assertEquals(FIRST_ATTACK, contestCombo.getFirstAttack());
        assertEquals(SECOND_ATTACK, contestCombo.getSecondAttack());
        assertEquals(OVERPOWERED, contestCombo.getOverpowered());
    }

    @Test
    public void testConstructor() {
        ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setOverpowered(OVERPOWERED);
        input.setContestType(CONTEST_TYPE);

        ContestCombo contestCombo = new ContestCombo(input, FIRST_ATTACK, SECOND_ATTACK);
        assertEquals(FIRST_ATTACK, contestCombo.getFirstAttack());
        assertEquals(SECOND_ATTACK, contestCombo.getSecondAttack());
        assertEquals(OVERPOWERED, contestCombo.getOverpowered());
        assertEquals(CONTEST_TYPE, contestCombo.getContestType());
        assertNotNull(contestCombo.id);
    }

}