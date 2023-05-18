package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAbility;
import com.pokemonurpg.entities.v1.PokemonAttack;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
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
    private PokemonService pokemonService;

    public boolean isValidStarter(Pokemon pokemon) {
        return isOwnable(pokemon)
                && pokemon.getPreEvolution() == null
                && !pokemonService.findByPreEvolution(pokemon).isEmpty()
                && pokemon.getLegendaryTier() == 0;
    }

    public boolean isValid(Pokemon pokemon, OwnedPokemonRequest input) {
        return isOwnable(pokemon)
            && isGenderLegal(pokemon, input.getGender())
            && areAllMovesLegal(pokemon, input.getOwnedExtraMoves())
            && areAllAbilitiesLegal(pokemon, input.getOwnedHiddenAbilities());
    }

    public boolean isOwnable(Pokemon pokemon) {
        if (pokemon.getPreMega() == null && Boolean.FALSE.equals(pokemon.isBattleOnly()))
            return true;
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't own that Pokemon form!");
    }

    public boolean isGenderLegal(Pokemon pokemon, String gender) {
        if (gender == null) {
            return true;
        }
        else if ("F".equalsIgnoreCase(gender) && pokemon.getFemaleAllowed()) {
            return true;
        }
        else if ("M".equalsIgnoreCase(gender) && pokemon.getMaleAllowed()) {
            return true;
        }
        else if ("G".equalsIgnoreCase(gender) && !pokemon.getFemaleAllowed() && !pokemon.getMaleAllowed()) {
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, gender + " is not a legal gender for " + pokemon.getName());
        }
    }

    public boolean areAllMovesLegal(Pokemon pokemon, List<OwnedExtraMoveRequest> moves) {
        if ("Smeargle".equals(pokemon.getName())) return true;

        List<PokemonAttack> validMoves = pokemon.getAttacks();
        List<String> validMoveNames =
            validMoves.stream()
                .filter(speciesAttack -> !"LEVEL-UP".equals(speciesAttack.getMethod()))
                .map(speciesAttack -> speciesAttack.getAttack().getName())
                .collect(Collectors.toList());

        List<String> invalidMoves =
            moves.stream()
                .filter(move -> !validMoveNames.contains(move.getAttack()))
                .map(OwnedExtraMoveRequest::getAttack)
                .collect(Collectors.toList());

        if (invalidMoves.isEmpty()) {
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, pokemon.getName() + " cannot learn the following moves: " + invalidMoves.toString());
        }
    }

    public boolean areAllAbilitiesLegal(Pokemon pokemon, List<OwnedHiddenAbilityRequest> abilities) {
        List<String> invalidAbilities = new ArrayList<>();
        List<PokemonAbility> validAbilities = pokemon.getAbilities();
        List<String> validAbilityNames = validAbilities.stream()
                .filter(PokemonAbility::getHidden)
                .map(speciesAbility -> speciesAbility.getAbility().getName())
                .collect(Collectors.toList());

        for (OwnedHiddenAbilityRequest ability : abilities) {
            if (!validAbilityNames.contains(ability.getAbility())) invalidAbilities.add(ability.getAbility());
        }

        if (invalidAbilities.isEmpty()) {
            return true;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, pokemon.getName() + " cannot have the following abilities: " + invalidAbilities.toString());
        }
    }

}
