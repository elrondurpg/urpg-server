package com.pokemonurpg.stats.validation;

import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAbility;
import com.pokemonurpg.species.models.SpeciesAttack;
import com.pokemonurpg.stats.input.OwnedExtraMoveInputDto;
import com.pokemonurpg.stats.input.OwnedHiddenAbilityInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnedPokemonValidator {

    public boolean isValid(Species species, OwnedPokemonInputDto input) {
        return isOwnable(species)
            && isGenderLegal(species, input.getGender())
            && areAllMovesLegal(species, input.getOwnedExtraMoves())
            && areAllAbilitiesLegal(species, input.getOwnedHiddenAbilities());
    }

    boolean isOwnable(Species species) {
        if (species.getPreMega() == null)
            return true;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't own a Mega form!");
    }

    boolean isGenderLegal(Species species, String gender) {
        if (gender == null) {
            return true;
        }
        else if ("F".equalsIgnoreCase(gender) && species.getFemaleAllowed()) {
            return true;
        }
        else if ("M".equalsIgnoreCase(gender) && species.getMaleAllowed()) {
            return true;
        }
        else if ("G".equalsIgnoreCase(gender) && !species.getFemaleAllowed() && !species.getMaleAllowed()) {
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, gender + " is not a legal gender for " + species.getName());
        }
    }

    boolean areAllMovesLegal(Species species, List<OwnedExtraMoveInputDto> moves) {
        if ("Smeargle".equals(species.getName())) return true;

        List<SpeciesAttack> validMoves = species.getMappedSpeciesAttacks();
        List<String> validMoveNames =
            validMoves.stream()
                .filter(speciesAttack -> !"LEVEL-UP".equals(speciesAttack.getMethod()))
                .map(speciesAttack -> speciesAttack.getAttack().getName())
                .collect(Collectors.toList());

        List<String> invalidMoves =
            moves.stream()
                .filter(move -> !validMoveNames.contains(move.getAttack()))
                .map(OwnedExtraMoveInputDto::getAttack)
                .collect(Collectors.toList());

        if (invalidMoves.isEmpty()) {
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, species.getName() + " cannot learn the following moves: " + invalidMoves.toString());
        }
    }

    boolean areAllAbilitiesLegal(Species species, List<OwnedHiddenAbilityInputDto> abilities) {
        List<String> invalidAbilities = new ArrayList<>();
        List<SpeciesAbility> validAbilities = species.getMappedSpeciesAbilities();
        List<String> validAbilityNames = validAbilities.stream()
                .filter(SpeciesAbility::getHidden)
                .map(speciesAbility -> speciesAbility.getAbility().getName())
                .collect(Collectors.toList());

        for (OwnedHiddenAbilityInputDto ability : abilities) {
            if (!validAbilityNames.contains(ability.getAbility())) invalidAbilities.add(ability.getAbility());
        }

        if (invalidAbilities.isEmpty()) {
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, species.getName() + " cannot have the following abilities: " + invalidAbilities.toString());
        }
    }

}