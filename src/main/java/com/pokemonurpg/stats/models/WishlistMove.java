package com.pokemonurpg.stats.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.attack.models.Attack;

@Entity
@Table(name = "wishlist_move")
public class WishlistMove {

    @EmbeddedId
    WishlistMoveKey id;

    @ManyToOne
    @MapsId("pokemon_dbid")
    @JoinColumn(name="pokemon_dbid", nullable=false)
    private OwnedPokemon pokemon;

    @ManyToOne
    @MapsId("move_dbid")
    @JoinColumn(name="move_dbid")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Attack move;

    public WishlistMove() {

    }

    public WishlistMove(OwnedPokemon pokemon, Attack move) {
        this.id = new WishlistMoveKey(pokemon.getDbid(), move.getDbid());
        this.setPokemon(pokemon);
        this.setMove(move);
    }

    public void setPokemon(OwnedPokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Attack getMove() {
        return move;
    }

    public void setMove(Attack move) {
        this.move = move;
    }

    
}
