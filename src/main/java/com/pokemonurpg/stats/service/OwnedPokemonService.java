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
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.input.WishlistAbilityInputDto;
import com.pokemonurpg.stats.input.WishlistMoveInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.models.WishlistAbility;
import com.pokemonurpg.stats.repository.OwnedPokemonRepository;
import com.pokemonurpg.stats.validation.OwnedPokemonValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.inject.Provider;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    @Resource
    private WishlistAbilityService wishlistAbilityService;

    @Resource
    private WishlistMoveService wishlistMoveService;

    public List<OwnedPokemon> findByOwner(String ownerName) {
        Member owner = memberService.findByNameExact(ownerName);
        if (owner != null) {
            return ownedPokemonRepository.findByTrainer(owner);
        }
        else return Collections.emptyList();
    }

    public OwnedPokemon findByDbid(Integer dbid) {
        return ownedPokemonRepository.findByDbid(dbid);
    }

    public OwnedPokemon create(OwnedPokemonInputDto input) {
        if (input.getTrainer() == null) {
            return create(input, sessionServiceProvider.get().getAuthenticatedMember());
        }
        else {
            Member member = memberService.findByNameExact(input.getTrainer());
            return create(input, member);
        }
    }

    private OwnedPokemon create(OwnedPokemonInputDto input, Member member) {
        Species species = speciesService.findByName(input.getSpecies());
        if (ownedPokemonValidator.isValid(species, input)) {
            OwnedPokemon pokemon = new OwnedPokemon(input, member, species);
            updateEmbeddedValues(input, pokemon);
            ownedPokemonRepository.save(pokemon);
            updateAssociatedValues(input, pokemon);
            ownedPokemonRepository.save(pokemon);
            pokemon = ownedPokemonRepository.findByDbid(pokemon.getDbid());
            return pokemon;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OwnedPokemon create() request was invalid.");
    }

    public OwnedPokemon update(OwnedPokemonInputDto input, int dbid) {
        Member member = null;
        if (input.getTrainer() != null) {
            member = memberService.findByNameExact(input.getTrainer());
        }

        OwnedPokemon pokemon = ownedPokemonRepository.findByDbid(dbid);
        if (pokemon != null && ownedPokemonValidator.isValid(pokemon.getSpecies(), input)) {
            pokemon.update(input, member);
            updateEmbeddedValues(input, pokemon);
            ownedPokemonRepository.save(pokemon);
            updateAssociatedValues(input, pokemon);
            ownedPokemonRepository.save(pokemon);
        }
        return pokemon;
    }

    private void updateEmbeddedValues(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        pokemon.setNature(natureService.findByName(input.getNature()));
        pokemon.setObtained(obtainedService.findByName(input.getObtained()));
        pokemon.setHiddenPowerType(typeService.findByName(input.getHiddenPowerType()));
    }

    private void updateAssociatedValues(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        ownedExtraMoveService.updateAll(input, pokemon);
        ownedHiddenAbilityService.updateAll(input, pokemon);
        updateEarnedRibbons(input, pokemon);
        updateWishlistAbilities(input, pokemon);
        updateWishlistMoves(input, pokemon);
    }

    private void updateEarnedRibbons(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        List<EarnedRibbonInputDto> ribbons = input.getEarnedRibbons();
        for (EarnedRibbonInputDto ribbon : ribbons) {
            earnedRibbonService.update(ribbon, pokemon);
        }
        pokemon.setEarnedRibbons(earnedRibbonService.findByOwnedPokemon(pokemon));
    }

    private void updateWishlistAbilities(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        Set<WishlistAbilityInputDto> abilities = input.getWishlistAbilities();
        for(WishlistAbilityInputDto ability : abilities) {
            wishlistAbilityService.update(ability, pokemon);
        }
        pokemon.setWishlistAbilities(wishlistAbilityService.findByPokemon(pokemon));
    }

    private void updateWishlistMoves(OwnedPokemonInputDto input, OwnedPokemon pokemon) {
        Set<WishlistMoveInputDto> moves = input.getWishlistMoves();
        for(WishlistMoveInputDto move : moves) {
            wishlistMoveService.update(move, pokemon);
        }
        pokemon.setWishlistMoves(wishlistMoveService.findByPokemon(pokemon));
    }

    public void delete(int dbid) {
        ownedPokemonRepository.deleteByDbid(dbid);
    }
}
