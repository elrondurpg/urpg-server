package com.pokemonurpg.factory;

import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.object.CosmeticFormKey;
import com.pokemonurpg.object.Species;

public class TestObjectFactory {

    public static final String TEST_SPECIES_NAME = "Pikachu";
    public static final String TEST_SPECIES_DISPLAY_NAME = "Pikachu Test Display Name";
    public static final int TEST_SPECIES_DEXNO = 25;
    public static final int TEST_SPECIES_DBID = 999;

    public static final String TEST_COSMETIC_FORM_NAME = "Pikachu-Spiky";
    public static final String TEST_COSMETIC_FORM_DISPLAY_NAME = "Spiky Eared Pikachu";
    public static final String TEST_COSMETIC_FORM_METHOD = "Use Spiky Earring";

    public static Species createNextDex() {
        Species nextDex = new Species();
        nextDex.setDexno(TEST_SPECIES_DEXNO + 1);
        return nextDex;
    }

    public static Species createPrevDex() {
        Species prevDex = new Species();
        prevDex.setDexno(TEST_SPECIES_DEXNO - 1);
        return prevDex;
    }

    public static Species createPikachu() {
        Species pikachu = new Species();
        pikachu.setName(TEST_SPECIES_NAME);
        pikachu.setDbid(TEST_SPECIES_DBID);
        pikachu.setDisplayName(TEST_SPECIES_DISPLAY_NAME);
        pikachu.setDexno(TEST_SPECIES_DEXNO);
        return pikachu;
    }

    public static CosmeticForm createSpikyEaredPikachu() {
        CosmeticFormKey key = new CosmeticFormKey();
        key.setSpeciesDbid(TEST_SPECIES_DBID);
        key.setName(TEST_COSMETIC_FORM_NAME);

        CosmeticForm spikyEaredPikachu = new CosmeticForm();
        spikyEaredPikachu.setId(key);
        spikyEaredPikachu.setDisplayName(TEST_COSMETIC_FORM_DISPLAY_NAME);
        spikyEaredPikachu.setMethod(TEST_COSMETIC_FORM_METHOD);

        return spikyEaredPikachu;
    }
}
