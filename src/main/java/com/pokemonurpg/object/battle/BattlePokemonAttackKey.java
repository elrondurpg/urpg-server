package com.pokemonurpg.object.battle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BattlePokemonAttackKey implements Serializable {
    @Column(name = "battle_pokemon_dbid")
    private Integer battlePokemonDbid;

    @Column(name = "attack_dbid")
    private Integer attackDbid;

    public BattlePokemonAttackKey() {
    }

    public BattlePokemonAttackKey(Integer battlePokemonDbid, Integer attackDbid) {
        this.battlePokemonDbid = battlePokemonDbid;
        this.attackDbid = attackDbid;
    }

    public Integer getBattlePokemonDbid() {
        return battlePokemonDbid;
    }

    public void setBattlePokemonDbid(Integer battlePokemonDbid) {
        this.battlePokemonDbid = battlePokemonDbid;
    }

    public Integer getAttackDbid() {
        return attackDbid;
    }

    public void setAttackDbid(Integer attackDbid) {
        this.attackDbid = attackDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattlePokemonAttackKey that = (BattlePokemonAttackKey) o;
        return Objects.equals(battlePokemonDbid, that.battlePokemonDbid) &&
                Objects.equals(attackDbid, that.attackDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(battlePokemonDbid, attackDbid);
    }
}
