package com.pokemonurpg.stats.service;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.general.service.NatureService;
import com.pokemonurpg.general.service.ObtainedService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.security.service.SessionService;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
import com.pokemonurpg.species.service.TypeService;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonCreateForMemberInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.input.StarterPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.OwnedPokemonRepository;
import com.pokemonurpg.stats.validation.OwnedPokemonValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.inject.Provider;
import java.util.List;

@Service
public class OwnedPokemonService implements IndexedObjectService<OwnedPokemon> {

    @Resource
    private OwnedPokemonValidator ownedPokemonValidator;

    @Resource
    private OwnedPokemonRepository ownedPokemonRepository;

    @Resource
    private MemberService memberService;

    @Resource
    private SpeciesService speciesService;

    @Resource
    private NatureService natureService;

    @Resource
    private ObtainedService obtainedService;

    @Resource
    private TypeService typeService;

    @Resource
    private OwnedExtraMoveService ownedExtraMoveService;

    @Resource
    private OwnedHiddenAbilityService ownedHiddenAbilityService;

    @Resource
    private EarnedRibbonService earnedRibbonService;

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    public OwnedPokemon findByDbid(Integer dbid) {
        OwnedPokemon pokemon = ownedPokemonRepository.findByDbid(dbid);

        if (pokemon != null) {
            List<Species> evolutions = speciesService.findByPreEvolution(pokemon.getSpecies());
            if (evolutions == null || evolutions.isEmpty()) {
                pokemon.setFullyEvolved(true);
            }
        }
        return pokemon;
    }

    public OwnedPokemon createStarter(StarterPokemonInputDto input) {
        Member member = sessionServiceProvider.get().getAuthenticatedMember();
        Species species = speciesService.findByName(input.getSpecies());
        if (!member.hasStarter() && ownedPokemonValidator.isGenderLegal(species, input.getGender()) && ownedPokemonValidator.isValidStarter(species)) {
            OwnedPokemon pokemon = new OwnedPokemon(member, species, input.getGender());
            pokemon.setObtained(obtainedService.findByName("Starter"));
            ownedPokemonRepository.save(pokemon);
            return pokemon;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Starter create() request was invalid.");
    }

    public OwnedPokemon create(OwnedPokemonInputDto input) {
        return createForMember(input, sessionServiceProvider.get().getAuthenticatedMember());
    }

    public OwnedPokemon create(OwnedPokemonCreateForMemberInputDto input) {
        return createForMember(input, memberService.findByNameExact(input.getTrainer()));
    }

    private OwnedPokemon createForMember(OwnedPokemonInputDto input, Member member) {
        Species species = speciesService.findByName(input.getSpecies());
        if (ownedPokemonValidator.isValid(species, input)) {
            OwnedPokemon pokemon = new OwnedPokemon(input, member, species);
            updateEmbeddedValues(input, pokemon);
            ownedPokemonRepository.save(pokemon);
            updateAssociatedValues(input, pokemon);
            return pokemon;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OwnedPokemon create() request was invalid.");
    }

    public OwnedPokemon update(OwnedPokemonInputDto input, int dbid) {
        OwnedPokemon pokemon = ownedPokemonRepository.findByDbid(dbid);
        if (pokemon != null && ownedPokemonValidator.isValid(pokemon.getSpecies(), input)) {
            pokemon.update(input);
            updateEmbeddedValues(input, pokemon);
            ownedPokemonRepository.save(pokemon);
            updateAssociatedValues(input, pokemon);
        }
        return pokemon;
    }

    private void updateEmbeddedValues(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        pokemon.setNature(natureService.findByName(input.getNature()));
        pokemon.setObtained(obtainedService.findByName(input.getObtained()));
        pokemon.setHiddenPowerType(typeService.findByName(input.getHiddenPowerType()));
        pokemon.setFullyEvolved(speciesService.findByPreEvolution(pokemon.getSpecies()).isEmpty());
    }

    private void updateAssociatedValues(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        ownedExtraMoveService.updateAll(input, pokemon);
        ownedHiddenAbilityService.updateAll(input, pokemon);
        updateEarnedRibbons(input, pokemon);
    }

    private void updateEarnedRibbons(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        List<EarnedRibbonInputDto> ribbons = input.getEarnedRibbons();
        for (EarnedRibbonInputDto ribbon : ribbons) {
            earnedRibbonService.update(ribbon, pokemon);
        }
    }

    public void delete(int dbid) {
        ownedPokemonRepository.deleteByDbid(dbid);
    }
}
