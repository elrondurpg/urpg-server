package com.pokemonurpg.species.models;

import com.pokemonurpg.configuration.v1.pokemon.CosmeticFormInputDto;
import com.pokemonurpg.entities.v1.CosmeticForm;
import org.junit.Test;

import static org.junit.Assert.*;

public class CosmeticFormTest {
    public final static String NAME = "TEST";
    public final static String FORM_NAME = "FORM_NAME";
    public final static Integer SPECIES_DBID = 123;

    @Test
    public void testPojo() {
        CosmeticForm cosmeticForm = new CosmeticForm();
        cosmeticForm.setName(NAME);
        cosmeticForm.setFormName(FORM_NAME);

        assertEquals(NAME, cosmeticForm.getName());
        assertEquals(FORM_NAME, cosmeticForm.getFormName());
    }

    @Test
    public void testConstructor() {
        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);
        input.setFormName(FORM_NAME);

        CosmeticForm cosmeticForm = new CosmeticForm(input, SPECIES_DBID);
        assertEquals(NAME, cosmeticForm.getName());
        assertEquals(FORM_NAME, cosmeticForm.getFormName());
    }

}