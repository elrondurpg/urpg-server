package com.pokemonurpg.factory;

import com.pokemonurpg.object.*;

public class TestObjectFactory {

    public static final String TEST_SPECIES_NAME = "Pikachu";
    public static final String TEST_SPECIES_DISPLAY_NAME = "Pikachu Test Display Name";
    public static final int TEST_SPECIES_DEXNO = 25;
    public static final int TEST_SPECIES_DBID = 999;

    public static final String TEST_ALTERNATE_FORM_NAME = "Pikachu-Belle";
    public static final String TEST_ALTERNATE_FORM_DISPLAY_NAME = "Pikachu Belle";
    public static final int TEST_ALTERNATE_FORM_DBID = 777;
    public static final String TEST_ALTERNATE_FORM_METHOD = "Form changes when using Pikachu's special items.";

    public static final String TEST_COSMETIC_FORM_NAME = "Pikachu-Spiky";
    public static final String TEST_COSMETIC_FORM_DISPLAY_NAME = "Spiky Eared Pikachu";
    public static final String TEST_COSMETIC_FORM_METHOD = "Use Spiky Earring";

    public static final int TEST_ATTACK_1_DBID = 123;
    public static final String TEST_ATTACK_1_NAME = "Thundershock";

    public static final int TEST_ATTACK_2_DBID = 234;
    public static final String TEST_ATTACK_2_NAME = "Thunder";

    public static final int TEST_ATTACK_3_DBID = 345;
    public static final String TEST_ATTACK_3_NAME = "Volt Tackle";

    public static final int TEST_ATTACK_4_DBID = 456;
    public static final String TEST_ATTACK_4_NAME = "Icicle Crash";

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

    public static Species createPikachuBelle() {
        Species pikachuBelle = new Species();
        pikachuBelle.setName(TEST_ALTERNATE_FORM_NAME);
        pikachuBelle.setDbid(TEST_ALTERNATE_FORM_DBID);
        pikachuBelle.setDexno(TEST_SPECIES_DEXNO);
        pikachuBelle.setDisplayName(TEST_ALTERNATE_FORM_DISPLAY_NAME);
        return pikachuBelle;
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

    public static Attack createThundershock() {
        Attack thundershock = new Attack();
        thundershock.setDbid(TEST_ATTACK_1_DBID);
        thundershock.setName(TEST_ATTACK_1_NAME);
        return thundershock;
    }

    public static Attack createThunder() {
        Attack thunder = new Attack();
        thunder.setDbid(TEST_ATTACK_2_DBID);
        thunder.setName(TEST_ATTACK_2_NAME);
        return thunder;
    }

    public static Attack createVoltTackle() {
        Attack voltTackle = new Attack();
        voltTackle.setDbid(TEST_ATTACK_3_DBID);
        voltTackle.setName(TEST_ATTACK_3_NAME);
        return voltTackle;
    }

    public static Attack createIcicleCrash() {
        Attack icicleCrash = new Attack();
        icicleCrash.setDbid(TEST_ATTACK_4_DBID);
        icicleCrash.setName(TEST_ATTACK_4_NAME);
        return icicleCrash;
    }

    public static SpeciesAttack buildSpeciesAttack(Attack attack, Species species) {
        SpeciesAttackKey key = new SpeciesAttackKey();
        key.setAttackDbid(attack.getDbid());
        key.setSpeciesDbid(species.getDbid());

        SpeciesAttack speciesAttack = new SpeciesAttack();
        speciesAttack.setId(key);
        speciesAttack.setAttack(attack);

        return speciesAttack;
    }
}
