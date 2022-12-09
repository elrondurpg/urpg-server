package com.pokemonurpg.stats.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.attack.AttackRepository;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.entities.v1.stats.WishlistMove;
import com.pokemonurpg.entities.v1.stats.WishlistMoveRepository;
import com.pokemonurpg.stats.input.WishlistMoveInputDto;

@Service
public class WishlistMoveService {

    @Resource
    private WishlistMoveRepository wishlistMoveRepository;

    @Resource
    private AttackRepository moveRepository;

    public Set<WishlistMove> findByPokemon(OwnedPokemon pokemon) {
        return wishlistMoveRepository.findByPokemon(pokemon);
    }

    public void update(WishlistMoveInputDto input, OwnedPokemon pokemon) {
        Attack move = moveRepository.findByName(input.getMove());
        WishlistMove existingRecord = wishlistMoveRepository.findByPokemonAndMove(pokemon, move);

        if (existingRecord != null) {
            if (input.getDelete()) {
                wishlistMoveRepository.delete(existingRecord);
            }
        }
        else {
            if (!pokemon.getOwnedExtraMoves().contains(move)) {
                WishlistMove newRecord = new WishlistMove(pokemon, move);
                wishlistMoveRepository.save(newRecord);
            }
        }
    }
    
}
