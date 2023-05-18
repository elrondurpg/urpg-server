package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.pokemon.PokemonRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PokemonTest {
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
    private final static List<PokemonAttack> MAPPED_SPECIES_ATTACKS = new ArrayList<>();
    private final static List<PokemonAbility> MAPPED_SPECIES_ABILITIES = new ArrayList<>();
    private final static Integer LEGENDARY_TIER = 3521;
    private final static String ALTERED_FORM_METHOD = "ALTERED_FORM_METHOD";
    private final static Set<CosmeticForm> COSMETIC_FORMS = new HashSet<>();
    private final static Pokemon PRE_EVOLUTION = new Pokemon();
    private final static String EVOLUTION_METHOD = "EVOLUTION_METHOD";
    private final static Integer EVOLUTION_EXP_REQUIREMENT = 2342562;
    private final static Pokemon PRE_MEGA = new Pokemon();
    private final static String MEGA_STONE = "MEGA_STONE";
    private final static String MEGA_SUFFIX = "MEGA_SUFFIX";

    @Test
    public void testPojo() {
        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(HEIGHT);
        pokemon.setWeight(WEIGHT);
        pokemon.setMaleAllowed(MALE_ALLOWED);
        pokemon.setFemaleAllowed(FEMALE_ALLOWED);
        pokemon.setPokemart(POKEMART);
        pokemon.setStoryRank(STORY_RANK);
        pokemon.setParkLocation(PARK_LOCATION);
        pokemon.setParkRank(PARK_RANK);
        pokemon.setArtRank(ART_RANK);
        pokemon.setContestCredits(CONTEST_CREDITS);
        pokemon.setDisplayName(DISPLAY_NAME);
        pokemon.setFormName(FORM_NAME);
        pokemon.setDbid(DBID);
        pokemon.setName(NAME);
        pokemon.setHp(HP);
        pokemon.setAttack(ATTACK);
        pokemon.setDefense(DEFENSE);
        pokemon.setSpecialAttack(SPECIAL_ATTACK);
        pokemon.setSpecialDefense(SPECIAL_DEFENSE);
        pokemon.setSpeed(SPEED);
        pokemon.setDexno(DEXNO);
        pokemon.setType1(TYPE1);
        pokemon.setType2(TYPE2);
        pokemon.setClassification(CLASSIFICATION);
        pokemon.setAbilities(MAPPED_SPECIES_ABILITIES);
        pokemon.setAttacks(MAPPED_SPECIES_ATTACKS);
        pokemon.setLegendaryTier(LEGENDARY_TIER);
        pokemon.setAlteredFormMethod(ALTERED_FORM_METHOD);
        pokemon.setCosmeticForms(COSMETIC_FORMS);
        pokemon.setPreEvolution(PRE_EVOLUTION);
        pokemon.setEvolutionMethod(EVOLUTION_METHOD);
        pokemon.setEvolutionExpRequirement(EVOLUTION_EXP_REQUIREMENT);
        pokemon.setPreMega(PRE_MEGA);
        pokemon.setMegaStone(MEGA_STONE);
        pokemon.setMegaSuffix(MEGA_SUFFIX);

        assertEquals(HEIGHT, pokemon.getHeight());
        assertEquals(WEIGHT, pokemon.getWeight());
        assertEquals(MALE_ALLOWED, pokemon.getMaleAllowed());
        assertEquals(FEMALE_ALLOWED, pokemon.getFemaleAllowed());
        assertEquals(POKEMART, pokemon.getPokemart());
        assertEquals(STORY_RANK, pokemon.getStoryRank());
        assertEquals(ART_RANK, pokemon.getArtRank());
        assertEquals(PARK_LOCATION, pokemon.getParkLocation());
        assertEquals(PARK_RANK, pokemon.getParkRank());
        assertEquals(CONTEST_CREDITS, pokemon.getContestCredits());
        assertEquals(DISPLAY_NAME, pokemon.getDisplayName());
        assertEquals(FORM_NAME, pokemon.getFormName());
        assertEquals(DBID, pokemon.getDbid());
        assertEquals(NAME, pokemon.getName());
        assertEquals(HP, pokemon.getHp());
        assertEquals(ATTACK, pokemon.getAttack());
        assertEquals(DEFENSE, pokemon.getDefense());
        assertEquals(SPECIAL_ATTACK, pokemon.getSpecialAttack());
        assertEquals(SPECIAL_DEFENSE, pokemon.getSpecialDefense());
        assertEquals(SPEED, pokemon.getSpeed());
        assertEquals(DEXNO, pokemon.getDexno());
        assertEquals(TYPE1, pokemon.getType1());
        assertEquals(TYPE2, pokemon.getType2());
        assertEquals(CLASSIFICATION, pokemon.getClassification());
        assertEquals(MAPPED_SPECIES_ATTACKS, pokemon.getAttacks());
        assertEquals(MAPPED_SPECIES_ABILITIES, pokemon.getAbilities());
        assertEquals(LEGENDARY_TIER, pokemon.getLegendaryTier());
        assertEquals(ALTERED_FORM_METHOD, pokemon.getAlteredFormMethod());
        assertEquals(COSMETIC_FORMS, pokemon.getCosmeticForms());
        assertEquals(PRE_EVOLUTION, pokemon.getPreEvolution());
        assertEquals(EVOLUTION_METHOD, pokemon.getEvolutionMethod());
        assertEquals(EVOLUTION_EXP_REQUIREMENT, pokemon.getEvolutionExpRequirement());
        assertEquals(PRE_MEGA, pokemon.getPreMega());
        assertEquals(MEGA_STONE, pokemon.getMegaStone());
        assertEquals(MEGA_SUFFIX, pokemon.getMegaSuffix());
    }

    @Test
    public void testConstructor() {
        PokemonRequest input = new PokemonRequest();
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

        Pokemon pokemon = new Pokemon(input);

        assertEquals(HEIGHT, pokemon.getHeight());
        assertEquals(WEIGHT, pokemon.getWeight());
        assertEquals(MALE_ALLOWED, pokemon.getMaleAllowed());
        assertEquals(FEMALE_ALLOWED, pokemon.getFemaleAllowed());
        assertEquals(POKEMART, pokemon.getPokemart());
        assertEquals(CONTEST_CREDITS, pokemon.getContestCredits());
        assertEquals(DISPLAY_NAME, pokemon.getDisplayName());
        assertEquals(FORM_NAME, pokemon.getFormName());
        assertEquals(NAME, pokemon.getName());
        assertEquals(HP, pokemon.getHp());
        assertEquals(ATTACK, pokemon.getAttack());
        assertEquals(DEFENSE, pokemon.getDefense());
        assertEquals(SPECIAL_ATTACK, pokemon.getSpecialAttack());
        assertEquals(SPECIAL_DEFENSE, pokemon.getSpecialDefense());
        assertEquals(SPEED, pokemon.getSpeed());
        assertEquals(DEXNO, pokemon.getDexno());
        assertEquals(CLASSIFICATION, pokemon.getClassification());
        assertEquals(LEGENDARY_TIER, pokemon.getLegendaryTier());
        assertEquals(ALTERED_FORM_METHOD, pokemon.getAlteredFormMethod());
        assertEquals(EVOLUTION_METHOD, pokemon.getEvolutionMethod());
        assertEquals(EVOLUTION_EXP_REQUIREMENT, pokemon.getEvolutionExpRequirement());
        assertEquals(MEGA_STONE, pokemon.getMegaStone());
        assertEquals(MEGA_SUFFIX, pokemon.getMegaSuffix());
    }

}