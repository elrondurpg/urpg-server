package com.pokemonurpg.entities.v1;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;

@Entity
@Table(name = "wishlist_ability")
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

    public void setPokemon(OwnedPokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    
}
