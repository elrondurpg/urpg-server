package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeInputDto;
import com.pokemonurpg.entities.v1.AttackTargetType;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttackTargetTypeTest {
    private final static Integer DBID = 23489;
    private final static String NAME = "TEST";
    private final static String DESCRIPTION = "TEST DESCRIPTION";

    private AttackTargetType attackTargetType = new AttackTargetType();

    @Test
    public void testPojo() {
        attackTargetType.setDbid(DBID);
        assertEquals(DBID, attackTargetType.getDbid());

        attackTargetType.setName(NAME);
        assertEquals(NAME, attackTargetType.getName());

        attackTargetType.setDescription(DESCRIPTION);
        assertEquals(DESCRIPTION, attackTargetType.getDescription());
    }

    @Test
    public void testConstructor() {
        AttackTargetTypeInputDto input = new AttackTargetTypeInputDto();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        AttackTargetType attackTargetType = new AttackTargetType(input);
        assertEquals(NAME, attackTargetType.getName());
        assertEquals(DESCRIPTION, attackTargetType.getDescription());
    }
}