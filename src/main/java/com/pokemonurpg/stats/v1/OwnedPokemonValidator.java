package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAbility;
import com.pokemonurpg.entities.v1.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnedPokemonValidator {

    @Resource
    private SpeciesService speciesService;

    public boolean isValidStarter(Species species) {
        return isOwnable(species)
                && species.getPreEvolution() == null
                && !speciesService.findByPreEvolution(species).isEmpty()
                && species.getLegendaryTier() == 0;
    }

    public boolean isValid(Species species, OwnedPokemonInputDto input) {
        return isOwnable(species)
            && isGenderLegal(species, input.getGender())
            && areAllMovesLegal(species, input.getOwnedExtraMoves())
            && areAllAbilitiesLegal(species, input.getOwnedHiddenAbilities());
    }

    public boolean isOwnable(Species species) {
        if (species.getPreMega() == null && Boolean.FALSE.equals(species.isBattleOnly()))
            return true;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't own that Pokemon form!");
    }

    public boolean isGenderLegal(Species species, String gender) {
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

    public boolean areAllMovesLegal(Species species, List<OwnedExtraMoveInputDto> moves) {
        if ("Smeargle".equals(species.getName())) return true;

        List<SpeciesAttack> validMoves = species.getAttacks();
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

    public boolean areAllAbilitiesLegal(Species species, List<OwnedHiddenAbilityInputDto> abilities) {
        List<String> invalidAbilities = new ArrayList<>();
        List<SpeciesAbility> validAbilities = species.getAbilities();
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
