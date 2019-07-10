package com.pokemonurpg.validator;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.object.SpeciesAttackKey;
import com.pokemonurpg.service.AttackService;
import com.pokemonurpg.service.SpeciesService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpeciesAttackValidatorTest {
/*
    private SpeciesAttackValidator speciesAttackValidator;
    private SpeciesService speciesService = mock(SpeciesService.class);
    private AttackService attackService = mock(AttackService.class);

    private static final Integer VALID_SPECIES_DBID = 80085;
    private static final String VALID_SPECIES_NAME = "Pikablu";

    private static final Integer VALID_ATTACK_DBID = 1337;
    private static final String VALID_ATTACK_NAME = "Tombstoner";

    @Before
    public void initValidator() {
        speciesAttackValidator = new SpeciesAttackValidator(speciesService, attackService);
    }

    @Before
    public void initValidSpecies() {
        Species validSpecies = mock(Species.class);
        when(validSpecies.getName()).thenReturn(VALID_SPECIES_NAME);

        when(speciesService.findByDbid(VALID_SPECIES_DBID)).thenReturn(Optional.of(validSpecies));
        when(speciesService.findByName(VALID_SPECIES_NAME)).thenReturn(Optional.of(validSpecies));
    }

    @Before
    public void initValidAttack() {
        Attack validAttack = mock(Attack.class);
        when(validAttack.getName()).thenReturn(VALID_ATTACK_NAME);

        when(attackService.findByDbid(VALID_ATTACK_DBID)).thenReturn(Optional.of(validAttack));
        when(attackService.findByName(VALID_ATTACK_NAME)).thenReturn(Optional.of(validAttack));
    }

    @Test
    public void validSpeciesAttackReturnsNoErrors() {
        SpeciesAttack subject = new SpeciesAttack();

        Species species = new Species();
        species.setName(VALID_SPECIES_NAME);
        subject.setSpecies(species);

        Attack attack = new Attack();
        attack.setName(VALID_ATTACK_NAME);
        subject.setAttack(attack);

        subject.setMethod("TM");
        subject.setGeneration(3);

        Errors errors = speciesAttackValidator.validate(subject);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void speciesAttackUsesKeyReturnsNoErrors() {
        SpeciesAttack subject = new SpeciesAttack();
        subject.setId(new SpeciesAttackKey(VALID_SPECIES_DBID, VALID_ATTACK_DBID));
        subject.setMethod("BREEDING");

        Errors errors = speciesAttackValidator.validate(subject);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void nullSpeciesAttackReturnsErrors() {
        Errors errors = speciesAttackValidator.validate(null);
        assertTrue(errors.toString().contains("No/Invalid Pokemon object specified."));
    }

    @Test
    public void speciesAttackWithNullValuesReturnsErrors() {
        SpeciesAttack subject = new SpeciesAttack();

        Species species = new Species();
        subject.setSpecies(species);

        Attack attack = new Attack();
        subject.setAttack(attack);

        subject.setMethod(null);

        Errors errors = speciesAttackValidator.validate(subject);

        assertTrue(errors.toString().contains("Species 'null' is invalid"));
        assertTrue(errors.toString().contains("Attack 'null' is invalid"));
        assertTrue(errors.toString().contains("Attack learning method 'null' is invalid"));
    }

    @Test
    public void speciesAttackWithInvalidValuesReturnsErrors() {
        SpeciesAttack subject = new SpeciesAttack();

        Species species = new Species();
        species.setName("Xalapeno");
        subject.setSpecies(species);

        Attack attack = new Attack();
        attack.setName(":boi:");
        subject.setAttack(attack);

        subject.setMethod(":weary2:");
        subject.setGeneration(9001);

        Errors errors = speciesAttackValidator.validate(subject);

        assertTrue(errors.toString().contains("Species 'Xalapeno' is invalid"));
        assertTrue(errors.toString().contains("Attack ':boi:' is invalid"));
        assertTrue(errors.toString().contains("Attack learning method ':weary2:' is invalid"));
        assertTrue(errors.toString().contains("Generation '9001' is invalid"));
    }

    @Test
    public void tmWithNoGenerationReturnsErrors() {
        SpeciesAttack subject = new SpeciesAttack();

        Species species = new Species();
        subject.setSpecies(species);

        Attack attack = new Attack();
        subject.setAttack(attack);

        subject.setMethod("TM");

        Errors errors = speciesAttackValidator.validate(subject);

        assertTrue(errors.toString().contains("TM learning method requires generation"));
    }
*/
}