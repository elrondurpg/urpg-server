package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.pokemon.SpeciesInputDto;
import com.pokemonurpg.entities.v1.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SpeciesTest {
    private final static Double HEIGHT = 0.234;
    private final static Double WEIGHT = 0.2342;
    private final static Boolean MALE_ALLOWED = true;
    private final static Boolean FEMALE_ALLOWED = true;
    private final static Integer POKEMART = 29342;
    private final static StoryRank STORY_RANK = new StoryRank();
    private final static ArtRank ART_RANK = new ArtRank();
    private final static ParkLocation PARK_LOCATION = new ParkLocation();
    private final static ParkRank PARK_RANK = new ParkRank();
    private final static Integer CONTEST_CREDITS = 3242;
    private final static String DISPLAY_NAME = "DISPLAY_NAME";
    private final static String FORM_NAME = "FORM_NAME";
    private final static Integer DBID = 24764;
    private final static String NAME = "NAME";
    private final static Integer HP = 2363;
    private final static Integer ATTACK = 3636;
    private final static Integer DEFENSE = 34673;
    private final static Integer SPECIAL_ATTACK = 643;
    private final static Integer SPECIAL_DEFENSE = 23452;
    private final static Integer SPEED = 5435;
    private final static Integer DEXNO = 23426;
    private final static Type TYPE1 = new Type();
    private final static Type TYPE2 = new Type();
    private final static String CLASSIFICATION = "CLASSIFICATION";
    private final static List<SpeciesAttack> MAPPED_SPECIES_ATTACKS = new ArrayList<>();
    private final static List<SpeciesAbility> MAPPED_SPECIES_ABILITIES = new ArrayList<>();
    private final static Integer LEGENDARY_TIER = 3521;
    private final static String ALTERED_FORM_METHOD = "ALTERED_FORM_METHOD";
    private final static Set<CosmeticForm> COSMETIC_FORMS = new HashSet<>();
    private final static Species PRE_EVOLUTION = new Species();
    private final static String EVOLUTION_METHOD = "EVOLUTION_METHOD";
    private final static Integer EVOLUTION_EXP_REQUIREMENT = 2342562;
    private final static Species PRE_MEGA = new Species();
    private final static String MEGA_STONE = "MEGA_STONE";
    private final static String MEGA_SUFFIX = "MEGA_SUFFIX";

    @Test
    public void testPojo() {
        Species species = new Species();
        species.setHeight(HEIGHT);
        species.setWeight(WEIGHT);
        species.setMaleAllowed(MALE_ALLOWED);
        species.setFemaleAllowed(FEMALE_ALLOWED);
        species.setPokemart(POKEMART);
        species.setStoryRank(STORY_RANK);
        species.setParkLocation(PARK_LOCATION);
        species.setParkRank(PARK_RANK);
        species.setArtRank(ART_RANK);
        species.setContestCredits(CONTEST_CREDITS);
        species.setDisplayName(DISPLAY_NAME);
        species.setFormName(FORM_NAME);
        species.setDbid(DBID);
        species.setName(NAME);
        species.setHp(HP);
        species.setAttack(ATTACK);
        species.setDefense(DEFENSE);
        species.setSpecialAttack(SPECIAL_ATTACK);
        species.setSpecialDefense(SPECIAL_DEFENSE);
        species.setSpeed(SPEED);
        species.setDexno(DEXNO);
        species.setType1(TYPE1);
        species.setType2(TYPE2);
        species.setClassification(CLASSIFICATION);
        species.setAbilities(MAPPED_SPECIES_ABILITIES);
        species.setAttacks(MAPPED_SPECIES_ATTACKS);
        species.setLegendaryTier(LEGENDARY_TIER);
        species.setAlteredFormMethod(ALTERED_FORM_METHOD);
        species.setCosmeticForms(COSMETIC_FORMS);
        species.setPreEvolution(PRE_EVOLUTION);
        species.setEvolutionMethod(EVOLUTION_METHOD);
        species.setEvolutionExpRequirement(EVOLUTION_EXP_REQUIREMENT);
        species.setPreMega(PRE_MEGA);
        species.setMegaStone(MEGA_STONE);
        species.setMegaSuffix(MEGA_SUFFIX);

        assertEquals(HEIGHT, species.getHeight());
        assertEquals(WEIGHT, species.getWeight());
        assertEquals(MALE_ALLOWED, species.getMaleAllowed());
        assertEquals(FEMALE_ALLOWED, species.getFemaleAllowed());
        assertEquals(POKEMART, species.getPokemart());
        assertEquals(STORY_RANK, species.getStoryRank());
        assertEquals(ART_RANK, species.getArtRank());
        assertEquals(PARK_LOCATION, species.getParkLocation());
        assertEquals(PARK_RANK, species.getParkRank());
        assertEquals(CONTEST_CREDITS, species.getContestCredits());
        assertEquals(DISPLAY_NAME, species.getDisplayName());
        assertEquals(FORM_NAME, species.getFormName());
        assertEquals(DBID, species.getDbid());
        assertEquals(NAME, species.getName());
        assertEquals(HP, species.getHp());
        assertEquals(ATTACK, species.getAttack());
        assertEquals(DEFENSE, species.getDefense());
        assertEquals(SPECIAL_ATTACK, species.getSpecialAttack());
        assertEquals(SPECIAL_DEFENSE, species.getSpecialDefense());
        assertEquals(SPEED, species.getSpeed());
        assertEquals(DEXNO, species.getDexno());
        assertEquals(TYPE1, species.getType1());
        assertEquals(TYPE2, species.getType2());
        assertEquals(CLASSIFICATION, species.getClassification());
        assertEquals(MAPPED_SPECIES_ATTACKS, species.getAttacks());
        assertEquals(MAPPED_SPECIES_ABILITIES, species.getAbilities());
        assertEquals(LEGENDARY_TIER, species.getLegendaryTier());
        assertEquals(ALTERED_FORM_METHOD, species.getAlteredFormMethod());
        assertEquals(COSMETIC_FORMS, species.getCosmeticForms());
        assertEquals(PRE_EVOLUTION, species.getPreEvolution());
        assertEquals(EVOLUTION_METHOD, species.getEvolutionMethod());
        assertEquals(EVOLUTION_EXP_REQUIREMENT, species.getEvolutionExpRequirement());
        assertEquals(PRE_MEGA, species.getPreMega());
        assertEquals(MEGA_STONE, species.getMegaStone());
        assertEquals(MEGA_SUFFIX, species.getMegaSuffix());
    }

    @Test
    public void testConstructor() {
        SpeciesInputDto input = new SpeciesInputDto();
        input.setDexno(DEXNO);
        input.setName(NAME);
        input.setClassification(CLASSIFICATION);
        input.setHp(HP);
        input.setAttack(ATTACK);
        input.setDefense(DEFENSE);
        input.setSpecialAttack(SPECIAL_ATTACK);
        input.setSpecialDefense(SPECIAL_DEFENSE);
        input.setSpeed(SPEED);
        input.setHeight(HEIGHT);
        input.setWeight(WEIGHT);
        input.setMaleAllowed(MALE_ALLOWED);
        input.setFemaleAllowed(FEMALE_ALLOWED);
        input.setPokemart(POKEMART);
        input.setContestCredits(CONTEST_CREDITS);
        input.setDisplayName(DISPLAY_NAME);
        input.setFormName(FORM_NAME);
        input.setLegendaryTier(LEGENDARY_TIER);
        input.setAlteredFormMethod(ALTERED_FORM_METHOD);
        input.setEvolutionMethod(EVOLUTION_METHOD);
        input.setEvolutionExpRequirement(EVOLUTION_EXP_REQUIREMENT);
        input.setMegaStone(MEGA_STONE);
        input.setMegaSuffix(MEGA_SUFFIX);

        Species species = new Species(input);

        assertEquals(HEIGHT, species.getHeight());
        assertEquals(WEIGHT, species.getWeight());
        assertEquals(MALE_ALLOWED, species.getMaleAllowed());
        assertEquals(FEMALE_ALLOWED, species.getFemaleAllowed());
        assertEquals(POKEMART, species.getPokemart());
        assertEquals(CONTEST_CREDITS, species.getContestCredits());
        assertEquals(DISPLAY_NAME, species.getDisplayName());
        assertEquals(FORM_NAME, species.getFormName());
        assertEquals(NAME, species.getName());
        assertEquals(HP, species.getHp());
        assertEquals(ATTACK, species.getAttack());
        assertEquals(DEFENSE, species.getDefense());
        assertEquals(SPECIAL_ATTACK, species.getSpecialAttack());
        assertEquals(SPECIAL_DEFENSE, species.getSpecialDefense());
        assertEquals(SPEED, species.getSpeed());
        assertEquals(DEXNO, species.getDexno());
        assertEquals(CLASSIFICATION, species.getClassification());
        assertEquals(LEGENDARY_TIER, species.getLegendaryTier());
        assertEquals(ALTERED_FORM_METHOD, species.getAlteredFormMethod());
        assertEquals(EVOLUTION_METHOD, species.getEvolutionMethod());
        assertEquals(EVOLUTION_EXP_REQUIREMENT, species.getEvolutionExpRequirement());
        assertEquals(MEGA_STONE, species.getMegaStone());
        assertEquals(MEGA_SUFFIX, species.getMegaSuffix());
    }

}