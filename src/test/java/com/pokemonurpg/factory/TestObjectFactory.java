package com.pokemonurpg.factory;

import com.pokemonurpg.dto.species.response.EvolutionFamilyMemberDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.EvolutionRepository;
import com.pokemonurpg.service.EvolutionService;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class TestObjectFactory {

    public static final String TEST_SPECIES_NAME = "Pikachu";
    public static final String TEST_SPECIES_DISPLAY_NAME = "Pikachu Test Display Name";
    public static final int TEST_SPECIES_DEXNO = 25;
    public static final int TEST_SPECIES_DBID = 999;

    public static final String TEST_PRE_EVOLUTION_NAME = "Pichu";
    public static final int TEST_PRE_EVOLUTION_DBID = 998;

    public static final String TEST_EVOLUTION_NAME = "Raichu";
    public static final int TEST_EVOLUTION_DBID = 1000;

    public static final String TEST_ALTERNATE_FORM_NAME = "Pikachu-Belle";
    public static final String TEST_ALTERNATE_FORM_DISPLAY_NAME = "Pikachu Belle";
    public static final int TEST_ALTERNATE_FORM_DBID = 777;
    public static final String TEST_ALTERNATE_FORM_METHOD = "Form changes when using Pikachu's special items.";

    public static final String TEST_COSMETIC_FORM_NAME = "Pikachu-Spiky";
    public static final String TEST_COSMETIC_FORM_DISPLAY_NAME = "Spiky Eared Pikachu";
    public static final String TEST_COSMETIC_FORM_METHOD = "Use Spiky Earring";

    public static final String TEST_EVOLUTION_METHOD = "Use Soothe Bell";

    public static final int TEST_ATTACK_1_DBID = 123;
    public static final String TEST_ATTACK_1_NAME = "Thundershock";

    public static final int TEST_ATTACK_2_DBID = 234;
    public static final String TEST_ATTACK_2_NAME = "Thunder";

    public static final int TEST_ATTACK_3_DBID = 345;
    public static final String TEST_ATTACK_3_NAME = "Volt Tackle";

    public static final int TEST_ATTACK_4_DBID = 456;
    public static final String TEST_ATTACK_4_NAME = "Icicle Crash";

    public static final int TEST_MEGA_EVOLUTION_ORIGINAL_DBID = 567;
    public static final int TEST_MEGA_EVOLUTION_DBID = 765;
    public static final String TEST_MEGA_STONE = "Charizardite X";

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

    public static Species createPichu() {
        Species pichu = new Species();
        pichu.setName(TEST_PRE_EVOLUTION_NAME);
        pichu.setDbid(TEST_PRE_EVOLUTION_DBID);
        return pichu;
    }

    public static Species createRaichu() {
        Species raichu = new Species();
        raichu.setName(TEST_EVOLUTION_NAME);
        raichu.setDbid(TEST_EVOLUTION_DBID);
        return raichu;
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

    public static void buildEvolutionRelation(EvolutionService service, Species prevo, Species evo) {
        if (prevo != null) {
            EvolutionKey evolutionKey = new EvolutionKey();
            evolutionKey.setEvolutionDbid(evo.getDbid());
            evolutionKey.setPreEvolutionDbid(prevo.getDbid());

            Evolution evolution = new Evolution();
            evolution.setId(evolutionKey);
            evolution.setMethod(TestObjectFactory.TEST_EVOLUTION_METHOD);

            when(service.getPreEvolutionDbid(evo.getDbid())).thenReturn(prevo.getDbid());
            when(service.findEvolutionsByPreEvolutionDbid(prevo.getDbid())).thenReturn(Arrays.asList(new EvolutionFamilyMemberDto(evo, null)));

        }
        else {
            when(service.getPreEvolutionDbid(evo.getDbid())).thenReturn(-1);
        }
    }

    public static void buildEvolutionRelation(EvolutionRepository repository, Species prevo, Species evo) {
        if (prevo != null) {
            EvolutionKey evolutionKey = new EvolutionKey();
            evolutionKey.setEvolutionDbid(evo.getDbid());
            evolutionKey.setPreEvolutionDbid(prevo.getDbid());

            Evolution evolution = new Evolution();
            evolution.setId(evolutionKey);
            evolution.setMethod(TestObjectFactory.TEST_EVOLUTION_METHOD);

            when(repository.findByIdEvolutionDbid(evo.getDbid())).thenReturn(evolution);
            when(repository.findByIdPreEvolutionDbid(prevo.getDbid())).thenReturn(Arrays.asList(evolution));
        }
        /*else {
            when(repository.findByIdEvolutionDbid(evo.getDbid())).thenReturn(evolution);
            when(repository.findByIdPreEvolutionDbid(prevo.getDbid())).thenReturn(Arrays.asList(evolution));
        }*/
    }

    public static MegaEvolution createMegaCharizardXRecord() {
        MegaEvolution megaCharizardXRecord = new MegaEvolution();

        MegaEvolutionKey key = new MegaEvolutionKey();
        key.setOriginalDbid(TEST_MEGA_EVOLUTION_ORIGINAL_DBID);
        key.setMegaEvolutionDbid(TEST_MEGA_EVOLUTION_DBID);

        megaCharizardXRecord.setId(key);
        megaCharizardXRecord.setMegaStone(TEST_MEGA_STONE);

        return megaCharizardXRecord;
    }

    public static Species createMegaCharizardX() {
        Species megaCharizardX = new Species();
        megaCharizardX.setDbid(TEST_MEGA_EVOLUTION_DBID);
        megaCharizardX.setDexno(6);
        megaCharizardX.setName("Charizard-Mega-X");
        megaCharizardX.setType1(new Type("Fire"));
        megaCharizardX.setType2(new Type("Dragon"));
        megaCharizardX.setClassification("Cool Dragon");
        megaCharizardX.setHp(300);
        megaCharizardX.setAttack(350);
        megaCharizardX.setDefense(250);
        megaCharizardX.setSpecialAttack(300);
        megaCharizardX.setSpecialDefense(250);
        megaCharizardX.setSpeed(300);
        megaCharizardX.setHeight(2);
        megaCharizardX.setWeight(100);
        megaCharizardX.setMaleAllowed(true);
        megaCharizardX.setFemaleAllowed(false);
        megaCharizardX.setDisplayName("Mega Charizard X");
        megaCharizardX.setFormName("Mega X Form");
        return megaCharizardX;
    }

    public static Species createCharizard() {
        Species charizard = new Species();
        charizard.setDbid(TEST_MEGA_EVOLUTION_ORIGINAL_DBID);
        return charizard;
    }
}
