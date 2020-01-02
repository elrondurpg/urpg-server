package com.pokemonurpg.object.battle;

import com.pokemonurpg.object.pokemon.Attack;
import com.pokemonurpg.object.trainer.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "battle_pokemon_attack")
public class BattlePokemonAttack {

    @EmbeddedId
    BattlePokemonAttackKey id;

    @ManyToOne
    @JoinColumn(name="battle_pokemon_dbid", insertable = false, updatable = false)
    private BattlePokemon pokemon;

    @ManyToOne
    @JoinColumn(name="attack_dbid", insertable = false, updatable = false)
    private Attack attack;

    @Column
    private int uses;

    public BattlePokemonAttack() {
    }

    public BattlePokemonAttack(BattlePokemon pokemon, Attack attack) {
        this.id = new BattlePokemonAttackKey(pokemon.getDbid(), attack.getDbid());
    }

    public BattlePokemonAttackKey getId() {
        return id;
    }

    public void setId(BattlePokemonAttackKey id) {
        this.id = id;
    }

    public BattlePokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(BattlePokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
}
