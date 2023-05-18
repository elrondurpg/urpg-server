package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAbility;
import com.pokemonurpg.entities.v1.PokemonAttack;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.junit.Assert.*;

public class OwnedPokemonValidatorTest {
    private final static String ATTACK_NAME = "ATTACK_NAME";
    private final static String INVALID_ATTACK_NAME = "INVALID_ATTACK_NAME";
    private final static String ABILITY_NAME = "ABILITY_NAME";
    private final static String INVALID_ABILITY_NAME = "INVALID_ABILITY_NAME";

    private OwnedPokemonValidator ownedPokemonValidator = new OwnedPokemonValidator();

    @Test
    public void isValid() {
        OwnedPokemonRequest input = new OwnedPokemonRequest();
        input.setGender("M");

        Pokemon pokemon = new Pokemon();
        pokemon.setMaleAllowed(true);
        pokemon.setBattleOnly(false);

        assertTrue(ownedPokemonValidator.isValid(pokemon, input));
    }

    @Test
    public void isOwnable() {
        Pokemon pokemon = new Pokemon();
        pokemon.setBattleOnly(false);
        assertTrue(ownedPokemonValidator.isOwnable(pokemon));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsNotOwnable() {
        Pokemon pokemon = new Pokemon();
        pokemon.setPreMega(new Pokemon());
        ownedPokemonValidator.isOwnable(pokemon);
    }

    @Test
    public void isGenderLegalMale() {
        Pokemon pokemon = new Pokemon();
        pokemon.setMaleAllowed(true);

        assertTrue(ownedPokemonValidator.isGenderLegal(pokemon, "M"));
    }

    @Test
    public void isGenderLegalFemale() {
        Pokemon pokemon = new Pokemon();
        pokemon.setFemaleAllowed(true);

        assertTrue(ownedPokemonValidator.isGenderLegal(pokemon, "F"));
    }

    @Test
    public void isGenderLegalGenderless() {
        Pokemon pokemon = new Pokemon();
        pokemon.setMaleAllowed(false);
        pokemon.setFemaleAllowed(false);

        assertTrue(ownedPokemonValidator.isGenderLegal(pokemon, "G"));
    }

    @Test
    public void isGenderLegalNull() {
        Pokemon pokemon = new Pokemon();
        assertTrue(ownedPokemonValidator.isGenderLegal(pokemon, null));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsGenderNotLegal() {
        Pokemon pokemon = new Pokemon();
        ownedPokemonValidator.isGenderLegal(pokemon, "F");
    }

    @Test
    public void allMovesLegalSmeargle() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Smeargle");

        assertTrue(ownedPokemonValidator.areAllMovesLegal(pokemon, null));
    }

    @Test
    public void allMovesLegal() {
        Pokemon pokemon = new Pokemon();
        PokemonAttack pokemonAttack = new PokemonAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        pokemonAttack.setAttack(attack);
        pokemonAttack.setMethod("TM");
        pokemon.setAttacks(Collections.singletonList(pokemonAttack));

        OwnedExtraMoveRequest move = new OwnedExtraMoveRequest();
        move.setAttack(ATTACK_NAME);

        assertTrue(ownedPokemonValidator.areAllMovesLegal(pokemon, Collections.singletonList(move)));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsAllMovesNotLegal() {
        Pokemon pokemon = new Pokemon();
        PokemonAttack pokemonAttack = new PokemonAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        pokemonAttack.setAttack(attack);
        pokemon.setAttacks(Collections.singletonList(pokemonAttack));

        OwnedExtraMoveRequest move = new OwnedExtraMoveRequest();
        move.setAttack(INVALID_ATTACK_NAME);
        ownedPokemonValidator.areAllMovesLegal(pokemon, Collections.singletonList(move));
    }

    @Test
    public void allAbilitiesLegal() {
        Pokemon pokemon = new Pokemon();
        PokemonAbility pokemonAbility = new PokemonAbility();
        Ability ability = new Ability();
        ability.setName(ABILITY_NAME);
        pokemonAbility.setAbility(ability);
        pokemonAbility.setHidden(true);
        pokemon.setAbilities(Collections.singletonList(pokemonAbility));

        OwnedHiddenAbilityRequest ownedHiddenAbilityInputDto = new OwnedHiddenAbilityRequest();
        ownedHiddenAbilityInputDto.setAbility(ABILITY_NAME);

        assertTrue(ownedPokemonValidator.areAllAbilitiesLegal(pokemon, Collections.singletonList(ownedHiddenAbilityInputDto)));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsAllAbilitiesNotLegal() {
        Pokemon pokemon = new Pokemon();
        PokemonAbility pokemonAbility = new PokemonAbility();
        Ability ability = new Ability();
        ability.setName(ABILITY_NAME);
        pokemonAbility.setAbility(ability);
        pokemonAbility.setHidden(true);
        pokemon.setAbilities(Collections.singletonList(pokemonAbility));

        OwnedHiddenAbilityRequest ownedHiddenAbilityInputDto = new OwnedHiddenAbilityRequest();
        ownedHiddenAbilityInputDto.setAbility(INVALID_ABILITY_NAME);

        assertFalse(ownedPokemonValidator.areAllAbilitiesLegal(pokemon, Collections.singletonList(ownedHiddenAbilityInputDto)));
    }
}