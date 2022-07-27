package com.pokemonurpg.stats.models;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.general.models.Nature;
import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class OwnedPokemonTest {
    private final static Integer DBID = 432;
    private final static Member TRAINER = mock(Member.class);
    private final static Species SPECIES = mock(Species.class);
    private final static String GENDER = "GENDER";
    private final static Nature NATURE = mock(Nature.class);
    private final static Integer EXP = 4311;
    private final static Obtained OBTAINED = mock(Obtained.class);
    private final static String OBTAINED_LINK = "OBTAINED_LINK";
    private final static String NICKNAME = "NICKNAME";
    private final static Type HIDDEN_POWER_TYPE = mock(Type.class);
    private final static String HIDDEN_POWER_LINK = "HIDDEN_POWER_LINK";
    private final static Set<Ability> OWNED_HIDDEN_ABILITIES = new HashSet<>();
    private final static Set<Attack> OWNED_EXTRA_MOVES = new HashSet<>();
    private final static List<EarnedRibbon> EARNED_RIBBONS = new ArrayList<>();
    private final static Boolean FULLY_EVOLVED = true;
    private final static Boolean JOB = null;
    private final static Boolean RENTAL = null;
    private final static Boolean UFT = null;
    private final static Boolean BOX = null;

    @Test
    public void testPojo() {
        OwnedPokemon pokemon = new OwnedPokemon();
        pokemon.setDbid(DBID);
        pokemon.setNature(NATURE);
        pokemon.setObtained(OBTAINED);
        pokemon.setHiddenPowerType(HIDDEN_POWER_TYPE);
        pokemon.setOwnedHiddenAbilities(OWNED_HIDDEN_ABILITIES);
        pokemon.setOwnedExtraMoves(OWNED_EXTRA_MOVES);
        pokemon.setEarnedRibbons(EARNED_RIBBONS);

        assertEquals(DBID, pokemon.getDbid());
        assertEquals(NATURE, pokemon.getNature());
        assertEquals(OBTAINED, pokemon.getObtained());
        assertEquals(HIDDEN_POWER_TYPE, pokemon.getHiddenPowerType());
        assertEquals(OWNED_HIDDEN_ABILITIES, pokemon.getOwnedHiddenAbilities());
        assertEquals(OWNED_EXTRA_MOVES, pokemon.getOwnedExtraMoves());
        assertEquals(EARNED_RIBBONS, pokemon.getEarnedRibbons());
    }

    @Test
    public void testConstructor() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        input.setGender(GENDER);
        input.setExp(EXP);
        input.setObtainedLink(OBTAINED_LINK);
        input.setNickname(NICKNAME);
        input.setHiddenPowerLink(HIDDEN_POWER_LINK);
        input.setJob(JOB);
        input.setBox(BOX);
        input.setRental(RENTAL);
        input.setUft(UFT);

        OwnedPokemon pokemon = new OwnedPokemon(input, TRAINER, SPECIES);
        assertEquals(TRAINER, pokemon.getTrainer());
        assertEquals(SPECIES, pokemon.getSpecies());
        assertEquals(GENDER, pokemon.getGender());
        assertEquals(EXP, pokemon.getExp());
        assertEquals(OBTAINED_LINK, pokemon.getObtainedLink());
        assertEquals(NICKNAME, pokemon.getNickname());
        assertEquals(HIDDEN_POWER_LINK, pokemon.getHiddenPowerLink());
        assertEquals(false, pokemon.getJob());
        assertEquals(false, pokemon.getBox());
        assertEquals(false, pokemon.getUft());
        assertEquals(false, pokemon.getRental());
    }
}