package com.pokemonurpg.dto.species;

import com.pokemonurpg.dto.species.response.MegaEvolutionDto;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.Species;
import org.junit.Test;

import static org.junit.Assert.*;

public class MegaEvolutionDtoTest {

    Species megaCharizardX = TestObjectFactory.createMegaCharizardX();

    @Test
    public void createEmptyDtoFromNullSpecies() {
        MegaEvolutionDto empty = new MegaEvolutionDto(null, null);
        assertEquals(0, empty.getDbid());
        assertNull(empty.getType1());
    }

    @Test
    public void createCompleteDtoFromSpecies() {
        MegaEvolutionDto dto = new MegaEvolutionDto(megaCharizardX, "Charizardite X");
        assertEquals(dto.getDbid(), megaCharizardX.getDbid());
        assertEquals(dto.getDexno(), megaCharizardX.getDexno());
        assertEquals(dto.getName(), megaCharizardX.getName());
        assertEquals(dto.getType1(), megaCharizardX.getType1().getName());
        assertEquals(dto.getType2(), megaCharizardX.getType2().getName());
        assertEquals(dto.getClassification(), megaCharizardX.getClassification());
        assertEquals(dto.getHp(), megaCharizardX.getHp());
        assertEquals(dto.getAttack(), megaCharizardX.getAttack());
        assertEquals(dto.getDefense(), megaCharizardX.getDefense());
        assertEquals(dto.getSpecialAttack(), megaCharizardX.getSpecialAttack());
        assertEquals(dto.getSpecialDefense(), megaCharizardX.getSpecialDefense());
        assertEquals(dto.getSpeed(), megaCharizardX.getSpeed());
        assertEquals(dto.getHeight(), megaCharizardX.getHeight(), 0);
        assertEquals(dto.getWeight(), megaCharizardX.getWeight(), 0);
        assertEquals(dto.isMaleAllowed(), megaCharizardX.getMaleAllowed());
        assertEquals(dto.isFemaleAllowed(), megaCharizardX.getFemaleAllowed());
        assertEquals(dto.getDisplayName(), megaCharizardX.getDisplayName());
        assertEquals(dto.getFormName(), megaCharizardX.getFormName());
        assertEquals("Charizardite X", dto.getMegaStone());
    }

}