package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.attacks.ContestComboRequest;
import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertEquals(FIRST_ATTACK, contestCombo.getFirstAttack());
        Assert.assertEquals(SECOND_ATTACK, contestCombo.getSecondAttack());
        Assert.assertEquals(OVERPOWERED, contestCombo.getOverpowered());
    }

    @Test
    public void testConstructor() {
        ContestComboRequest input = new ContestComboRequest();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setOverpowered(OVERPOWERED);
       // input.setContestType(CONTEST_TYPE);

        ContestGeneration generation = new ContestGeneration();

        ContestCombo contestCombo = new ContestCombo(input, FIRST_ATTACK, SECOND_ATTACK, generation);
        Assert.assertEquals(FIRST_ATTACK, contestCombo.getFirstAttack());
        Assert.assertEquals(SECOND_ATTACK, contestCombo.getSecondAttack());
        Assert.assertEquals(OVERPOWERED, contestCombo.getOverpowered());
        Assert.assertEquals(generation, contestCombo.getGeneration());
        Assert.assertNotNull(contestCombo.id);
    }

}