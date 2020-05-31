package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
@Table(name = "owned_extra_move")
public class OwnedExtraMove {

    @EmbeddedId
    OwnedExtraMoveKey id;

    @ManyToOne
    @JoinColumn(name="pokemon_dbid", insertable = false, updatable = false)
    private OwnedPokemon pokemon;

    @ManyToOne
    @JoinColumn(name="attack_dbid", insertable = false, updatable = false)
    private Attack attack;

    public OwnedExtraMove() {
    }

    public OwnedExtraMove(int pokemonDbid, int attackDbid) {
        this.id = new OwnedExtraMoveKey(pokemonDbid, attackDbid);
    }

    public OwnedExtraMoveKey getId() {
        return id;
    }

    public void setId(OwnedExtraMoveKey id) {
        this.id = id;
    }

    public OwnedPokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(OwnedPokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }
}
