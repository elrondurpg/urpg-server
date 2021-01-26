package com.pokemonurpg.attack.models;

import com.pokemonurpg.attack.input.AttackInputDto;
import com.pokemonurpg.contest.models.*;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.species.models.SpeciesAttack;
import com.pokemonurpg.species.models.Type;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackTest {
    private final static Integer DBID = 234324;
    private final static String NAME = "NAME";
    private final static Type TYPE = new Type();
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer POWER = 342532;
    private final static Integer ACCURACY = 3297;
    private final static Integer PP = 890234;
    private final static AttackCategory CATEGORY = new AttackCategory();
    private final static AttackTargetType TARGET = new AttackTargetType();
    private final static Boolean CONTACT = true;
    private final static Boolean SNATCH = true;
    private final static Boolean SUBSTITUTE = true;
    private final static Boolean SHEER_FORCE = true;
    private final static Boolean MAGIC_COAT = true;
    private final static RSEContestMoveType RSE_CONTEST_MOVE_TYPE = new RSEContestMoveType();
    private final static ContestAttribute RSE_CONTEST_ATTRIBUTE = new ContestAttribute();
    private final static ORASContestMoveType ORAS_CONTEST_MOVE_TYPE = new ORASContestMoveType();
    private final static ContestAttribute ORAS_CONTEST_ATTRIBUTE = new ContestAttribute();
    private final static DPPContestMoveType DPP_CONTEST_MOVE_TYPE = new DPPContestMoveType();
    private final static ContestAttribute DPP_CONTEST_ATTRIBUTE = new ContestAttribute();
    private final static AdvContestMoveType ADV_CONTEST_MOVE_TYPE = new AdvContestMoveType();
    private final static ContestAttribute ADV_CONTEST_ATTRIBUTE = new ContestAttribute();
    private final static Integer TM_HM_DBID = 32432;
    private final static Set<SpeciesAttack> POKEMON = new HashSet<>();
    private final static Item TM = mock(Item.class);
    private final static Set<ContestCombo> CONTEST_COMBOS = new HashSet<>();

    Attack attack = new Attack();

    @Test
    public void testPojo() {
        attack.setDbid(DBID);
        assertEquals(DBID, attack.getDbid());

        attack.setName(NAME);
        assertEquals(NAME, attack.getName());

        attack.setType(TYPE);
        assertEquals(TYPE, attack.getType());

        attack.setDescription(DESCRIPTION);
        assertEquals(DESCRIPTION, attack.getDescription());

        attack.setPower(POWER);
        assertEquals(POWER, attack.getPower());

        attack.setAccuracy(ACCURACY);
        assertEquals(ACCURACY, attack.getAccuracy());

        attack.setPp(PP);
        assertEquals(PP, attack.getPp());

        attack.setCategory(CATEGORY);
        assertEquals(CATEGORY, attack.getCategory());

        attack.setTarget(TARGET);
        assertEquals(TARGET, attack.getTarget());

        attack.setContact(CONTACT);
        assertEquals(CONTACT, attack.isContact());

        attack.setSnatch(SNATCH);
        assertEquals(SNATCH, attack.isSnatch());

        attack.setSubstitute(SUBSTITUTE);
        assertEquals(SUBSTITUTE, attack.isSubstitute());

        attack.setSheerForce(SHEER_FORCE);
        assertEquals(SHEER_FORCE, attack.isSheerForce());

        attack.setMagicCoat(MAGIC_COAT);
        assertEquals(MAGIC_COAT, attack.isMagicCoat());

        attack.setRseContestAttribute(RSE_CONTEST_ATTRIBUTE);
        assertEquals(RSE_CONTEST_ATTRIBUTE, attack.getRseContestAttribute());

        attack.setRseContestMoveType(RSE_CONTEST_MOVE_TYPE);
        assertEquals(RSE_CONTEST_MOVE_TYPE, attack.getRseContestMoveType());

        attack.setDppContestAttribute(DPP_CONTEST_ATTRIBUTE);
        assertEquals(DPP_CONTEST_ATTRIBUTE, attack.getDppContestAttribute());

        attack.setDppContestMoveType(DPP_CONTEST_MOVE_TYPE);
        assertEquals(DPP_CONTEST_MOVE_TYPE, attack.getDppContestMoveType());

        attack.setOrasContestAttribute(ORAS_CONTEST_ATTRIBUTE);
        assertEquals(ORAS_CONTEST_ATTRIBUTE, attack.getOrasContestAttribute());

        attack.setOrasContestMoveType(ORAS_CONTEST_MOVE_TYPE);
        assertEquals(ORAS_CONTEST_MOVE_TYPE, attack.getOrasContestMoveType());

        attack.setAdvContestAttribute(ADV_CONTEST_ATTRIBUTE);
        assertEquals(ADV_CONTEST_ATTRIBUTE, attack.getAdvContestAttribute());

        attack.setAdvContestMoveType(ADV_CONTEST_MOVE_TYPE);
        assertEquals(ADV_CONTEST_MOVE_TYPE, attack.getAdvContestMoveType());

        attack.setPokemon(POKEMON);
        assertEquals(POKEMON, attack.getPokemon());

        attack.setTm(TM);
        assertEquals(TM, attack.getTm());

        attack.setContestCombos(CONTEST_COMBOS);
        assertEquals(CONTEST_COMBOS, attack.getContestCombos());
    }

    @Test
    public void update() {
        AttackInputDto input = mock(AttackInputDto.class);
        when(input.getName()).thenReturn(NAME);
        when(input.getDescription()).thenReturn(DESCRIPTION);
        when(input.getPower()).thenReturn(POWER);
        when(input.getAccuracy()).thenReturn(ACCURACY);
        when(input.getPp()).thenReturn(PP);
        when(input.getContact()).thenReturn(CONTACT);
        when(input.getSnatch()).thenReturn(SNATCH);
        when(input.getSubstitute()).thenReturn(SUBSTITUTE);
        when(input.getSheerForce()).thenReturn(SHEER_FORCE);
        when(input.getMagicCoat()).thenReturn(MAGIC_COAT);

        Attack attack = new Attack(input);

        assertEquals(NAME, attack.getName());
        assertEquals(DESCRIPTION, attack.getDescription());
        assertEquals(POWER, attack.getPower());
        assertEquals(ACCURACY, attack.getAccuracy());
        assertEquals(PP, attack.getPp());
        assertEquals(CONTACT, attack.isContact());
        assertEquals(SNATCH, attack.isSnatch());
        assertEquals(SUBSTITUTE, attack.isSubstitute());
        assertEquals(SHEER_FORCE, attack.isSheerForce());
        assertEquals(MAGIC_COAT, attack.isMagicCoat());
    }

}