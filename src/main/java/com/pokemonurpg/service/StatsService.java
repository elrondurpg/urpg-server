package com.pokemonurpg.service;

import com.pokemonurpg.dto.stats.*;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.MemberRepository;
import com.pokemonurpg.repository.OwnedPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {
    private MemberRepository memberRepository;
    private OwnedPokemonRepository ownedPokemonRepository;

    @Autowired
    public StatsService(MemberRepository memberRepository, OwnedPokemonRepository ownedPokemonRepository) {
        this.memberRepository = memberRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
    }

    public StatsDto findByName(String name) {
        Member trainer = memberRepository.findByUsername(name);
        if (trainer != null) {
            return buildStatsDto(trainer);
        }
        else {
            List<Member> results = memberRepository.findByUsernameStartingWith(name);
            if (!results.isEmpty()) {
                return buildStatsDto(results.get(0));
            }
            else return null;
        }
    }

    public StatsDto buildStatsDto(Member trainer) {
        StatsDto dto = new StatsDto(trainer);
        dto.setPokemon(buildOwnedPokemonDtoList(trainer));
        dto.setItems(buildOwnedItemsDtoList(trainer));
        return dto;
    }

    public List<OwnedPokemonBriefDto> buildOwnedPokemonDtoList(Member trainer) {
        List<OwnedPokemon> ownedPokemonRecords = trainer.getPokemon();
        List<OwnedPokemonBriefDto> result = new ArrayList<>();

        for (OwnedPokemon pokemon : ownedPokemonRecords) {
            OwnedPokemonBriefDto dto = new OwnedPokemonBriefDto(pokemon);
            result.add(dto);
        }

        return result;
    }

    public OwnedPokemonDto findOwnedPokemonByDbid(int dbid) {
        OwnedPokemon pokemon = ownedPokemonRepository.findByDbid(dbid);
        OwnedPokemonDto dto = new OwnedPokemonDto(pokemon);
        if (dto != null) {
            if (dto.getAttacks() != null) {
                dto.getAttacks().addAll(getLevelUpMoves(pokemon));
            }
            if (dto.getAbilities() != null) {
                dto.getAbilities().addAll(getNonHiddenAbilities(pokemon));
            }
        }
        return dto;
    }

    public List<String> getLevelUpMoves(OwnedPokemon pokemon) {
        List<String> result = new ArrayList<>();
        Species species = pokemon.getSpecies();
        if (species != null) {
            for (SpeciesAttack attack : species.getSpeciesAttacks()) {
                if (attack.getAttack() != null && attack.getMethod().equals("LEVEL-UP")) {
                    result.add(attack.getAttack().getName());
                }
            }
        }
        return result;
    }

    public List<String> getNonHiddenAbilities(OwnedPokemon pokemon) {
        List<String> result = new ArrayList<>();
        Species species = pokemon.getSpecies();
        if (species != null) {
            for (SpeciesAbility ability : species.getSpeciesAbilities()) {
                if (ability.getAbility() != null && !ability.getHidden()) {
                    result.add(ability.getAbility().getName());
                }
            }
        }
        return result;
    }

    public List<OwnedItemDto> buildOwnedItemsDtoList(Member trainer) {
        List<OwnedItem> ownedItemRecords = trainer.getItems();
        List<OwnedItemDto> result = new ArrayList<>();

        for (OwnedItem item : ownedItemRecords) {
            OwnedItemDto dto = new OwnedItemDto(item);
            result.add(dto);
        }

        return result;
    }
}
