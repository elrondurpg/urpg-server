package com.pokemonurpg.attack.input;

import com.pokemonurpg.configuration.v1.attacks.AttackInputDto;
import com.pokemonurpg.configuration.v1.attacks.ContestComboInputDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AttackInputDtoTest {
    private final static String NAME = "TEST";
    private final static String TYPE = "TYPE";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer POWER = 8934;
    private final static Integer ACCURACY = 9302;
    private final static Integer PP = 3849;
    private final static String CATEGORY = "CATEGORY";
    private final static String TARGET_TYPE = "TARGET_TYPE";
    private final static Boolean CONTACT = true;
    private final static Boolean SNATCH = true;
    private final static Boolean SUBSTITUTE = true;
    private final static Boolean SHEER_FORCE = true;
    private final static Boolean MAGIC_COAT = true;
    private final static String RSE_ATTR = "RSE_ATTR";
    private final static String RSE_TYPE = "RSE_TYPE";
    private final static String DPP_ATTR = "DPP_ATTR";
    private final static String DPP_TYPE = "DPP_TYPE";
    private final static String ORAS_ATTR = "ORAS_ATTR";
    private final static String ORAS_TYPE = "ORAS_TYPE";
    private final static String TM_NAME = "TM_NAME";
    private final static List<ContestComboInputDto> CONTEST_COMBOS = new ArrayList<>();

    private AttackInputDto input = new AttackInputDto();

    @Test
    public void testPojo() {
        input.setName(NAME);
        input.setType(TYPE);
        input.setDescription(DESCRIPTION);
        input.setPower(POWER);
        input.setAccuracy(ACCURACY);
        input.setPp(PP);
        input.setCategory(CATEGORY);
        input.setTarget(TARGET_TYPE);
        input.setContact(CONTACT);
        input.setSnatch(SNATCH);
        input.setSubstitute(SUBSTITUTE);
        input.setSheerForce(SHEER_FORCE);
        input.setMagicCoat(MAGIC_COAT);
        input.setRseContestAttribute(RSE_ATTR);
        input.setRseContestMoveType(RSE_TYPE);
        input.setOrasContestAttribute(ORAS_ATTR);
        input.setOrasContestMoveType(ORAS_TYPE);
        input.setTm(TM_NAME);
        input.setContestCombos(CONTEST_COMBOS);

        assertEquals(NAME, input.getName());
        assertEquals(TYPE, input.getType());
        assertEquals(DESCRIPTION, input.getDescription());
        assertEquals(POWER, input.getPower());
        assertEquals(ACCURACY, input.getAccuracy());
        assertEquals(PP, input.getPp());
        assertEquals(CATEGORY, input.getCategory());
        assertEquals(TARGET_TYPE, input.getTarget());
        assertEquals(CONTACT, input.getContact());
        assertEquals(SNATCH, input.getSnatch());
        assertEquals(SUBSTITUTE, input.getSubstitute());
        assertEquals(SHEER_FORCE, input.getSheerForce());
        assertEquals(MAGIC_COAT, input.getMagicCoat());
        assertEquals(RSE_ATTR, input.getRseContestAttribute());
        assertEquals(RSE_TYPE, input.getRseContestMoveType());
        assertEquals(ORAS_ATTR, input.getOrasContestAttribute());
        assertEquals(ORAS_TYPE, input.getOrasContestMoveType());
        assertEquals(TM_NAME, input.getTm());
        assertEquals(CONTEST_COMBOS, input.getContestCombos());
    }
}