package com.pokemonurpg.object.trainer;

import com.pokemonurpg.object.pokemon.Ability;

import javax.persistence.*;

@Entity
@Table(name = "owned_hidden_ability")
public class OwnedHiddenAbility {

    @EmbeddedId
    OwnedHiddenAbilityKey id;

    @ManyToOne
    @JoinColumn(name="pokemon_dbid", insertable = false, updatable = false)
    private OwnedPokemon pokemon;

    @ManyToOne
    @JoinColumn(name="ability_dbid", insertable = false, updatable = false)
    private Ability ability;

    public OwnedHiddenAbility() {
    }

    public OwnedHiddenAbilityKey getId() {
        return id;
    }

    public void setId(OwnedHiddenAbilityKey id) {
        this.id = id;
    }

    public OwnedPokemon getPokemon() {
        return pokemon;
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
