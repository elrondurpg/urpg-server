package com.pokemonurpg.entities.v1.stats;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.entities.v1.pokemon.Ability;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wishlist_ability")
@Getter
@Setter
public class WishlistAbility {

    @EmbeddedId
    WishlistAbilityKey id;

    @ManyToOne
    @MapsId("pokemon_dbid")
    @JoinColumn(name="pokemon_dbid", nullable=false)
    private OwnedPokemon pokemon;

    @ManyToOne
    @MapsId("ability_dbid")
    @JoinColumn(name="ability_dbid")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Ability ability;

    public WishlistAbility() {

    }

    public WishlistAbility(OwnedPokemon pokemon, Ability ability) {
        this.id = new WishlistAbilityKey(pokemon.getDbid(), ability.getDbid());
        this.setPokemon(pokemon);
        this.setAbility(ability);
    }
}
