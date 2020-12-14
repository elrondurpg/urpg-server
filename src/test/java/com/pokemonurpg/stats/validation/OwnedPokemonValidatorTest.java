package com.pokemonurpg.stats.validation;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAbility;
import com.pokemonurpg.species.models.SpeciesAttack;
import com.pokemonurpg.stats.input.OwnedExtraMoveInputDto;
import com.pokemonurpg.stats.input.OwnedHiddenAbilityInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
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
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        input.setGender("M");

        Species species = new Species();
        species.setMaleAllowed(true);

        assertTrue(ownedPokemonValidator.isValid(species, input));
    }

    @Test
    public void isOwnable() {
        Species species = new Species();
        assertTrue(ownedPokemonValidator.isOwnable(species));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsNotOwnable() {
        Species species = new Species();
        species.setPreMega(new Species());
        ownedPokemonValidator.isOwnable(species);
    }

    @Test
    public void isGenderLegalMale() {
        Species species = new Species();
        species.setMaleAllowed(true);

        assertTrue(ownedPokemonValidator.isGenderLegal(species, "M"));
    }

    @Test
    public void isGenderLegalFemale() {
        Species species = new Species();
        species.setFemaleAllowed(true);

        assertTrue(ownedPokemonValidator.isGenderLegal(species, "F"));
    }

    @Test
    public void isGenderLegalGenderless() {
        Species species = new Species();
        species.setMaleAllowed(false);
        species.setFemaleAllowed(false);

        assertTrue(ownedPokemonValidator.isGenderLegal(species, "G"));
    }

    @Test
    public void isGenderLegalNull() {
        Species species = new Species();
        assertTrue(ownedPokemonValidator.isGenderLegal(species, null));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsGenderNotLegal() {
        Species species = new Species();
        ownedPokemonValidator.isGenderLegal(species, "F");
    }

    @Test
    public void allMovesLegalSmeargle() {
        Species species = new Species();
        species.setName("Smeargle");

        assertTrue(ownedPokemonValidator.areAllMovesLegal(species, null));
    }

    @Test
    public void allMovesLegal() {
        Species species = new Species();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        speciesAttack.setAttack(attack);
        speciesAttack.setMethod("TM");
        species.setMappedSpeciesAttacks(Collections.singletonList(speciesAttack));

        OwnedExtraMoveInputDto move = new OwnedExtraMoveInputDto();
        move.setAttack(ATTACK_NAME);

        assertTrue(ownedPokemonValidator.areAllMovesLegal(species, Collections.singletonList(move)));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsAllMovesNotLegal() {
        Species species = new Species();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        speciesAttack.setAttack(attack);
        species.setMappedSpeciesAttacks(Collections.singletonList(speciesAttack));

        OwnedExtraMoveInputDto move = new OwnedExtraMoveInputDto();
        move.setAttack(INVALID_ATTACK_NAME);
        ownedPokemonValidator.areAllMovesLegal(species, Collections.singletonList(move));
    }

    @Test
    public void allAbilitiesLegal() {
        Species species = new Species();
        SpeciesAbility speciesAbility = new SpeciesAbility();
        Ability ability = new Ability();
        ability.setName(ABILITY_NAME);
        speciesAbility.setAbility(ability);
        speciesAbility.setHidden(true);
        species.setMappedSpeciesAbilities(Collections.singletonList(speciesAbility));

        OwnedHiddenAbilityInputDto ownedHiddenAbilityInputDto = new OwnedHiddenAbilityInputDto();
        ownedHiddenAbilityInputDto.setAbility(ABILITY_NAME);

        assertTrue(ownedPokemonValidator.areAllAbilitiesLegal(species, Collections.singletonList(ownedHiddenAbilityInputDto)));
    }

    @Test(expected = ResponseStatusException.class)
    public void failsAllAbilitiesNotLegal() {
        Species species = new Species();
        SpeciesAbility speciesAbility = new SpeciesAbility();
        Ability ability = new Ability();
        ability.setName(ABILITY_NAME);
        speciesAbility.setAbility(ability);
        speciesAbility.setHidden(true);
        species.setMappedSpeciesAbilities(Collections.singletonList(speciesAbility));

        OwnedHiddenAbilityInputDto ownedHiddenAbilityInputDto = new OwnedHiddenAbilityInputDto();
        ownedHiddenAbilityInputDto.setAbility(INVALID_ABILITY_NAME);

        assertFalse(ownedPokemonValidator.areAllAbilitiesLegal(species, Collections.singletonList(ownedHiddenAbilityInputDto)));
    }
}