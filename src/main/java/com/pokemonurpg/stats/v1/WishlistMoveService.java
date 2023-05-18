package com.pokemonurpg.stats.v1;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.entities.v1.WishlistMove;
import com.pokemonurpg.infrastructure.v1.data.jpa.WishlistMoveRepository;

@Service
public class WishlistMoveService {

    @Resource
    private WishlistMoveRepository wishlistMoveRepository;

    @Resource
    private AttackRepository moveRepository;

    public Set<WishlistMove> findByPokemon(OwnedPokemon pokemon) {
        return wishlistMoveRepository.findByPokemon(pokemon);
    }

    public void update(WishlistMoveRequest input, OwnedPokemon pokemon) {
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
