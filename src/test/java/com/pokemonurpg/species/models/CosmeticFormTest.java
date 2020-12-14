package com.pokemonurpg.species.models;

import com.pokemonurpg.species.input.CosmeticFormInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class CosmeticFormTest {
    public final static String NAME = "TEST";
    public final static String FORM_NAME = "FORM_NAME";

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

        CosmeticForm cosmeticForm = new CosmeticForm(input);
        assertEquals(NAME, cosmeticForm.getName());
        assertEquals(FORM_NAME, cosmeticForm.getFormName());
    }

}