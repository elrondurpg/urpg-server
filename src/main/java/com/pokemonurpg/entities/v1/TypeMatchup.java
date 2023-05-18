package com.pokemonurpg.entities.v1;

import javax.persistence.*;

@Entity
@Table(name = "type_matchup")
public class TypeMatchup {
    @EmbeddedId
    private
    TypeMatchupKey id;

    @ManyToOne
    @MapsId("attack_type_dbid")
    @JoinColumn(name = "attack_type_dbid")
    private Type attackType;

    @ManyToOne
    @MapsId("defend_type_dbid")
    @JoinColumn(name = "defend_type_dbid")
    private Type defendType;

    @Column
    private
    Double multiplier;

    public TypeMatchup() {
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public Type getAttackType() {
        return attackType;
    }

    public void setAttackType(Type attackType) {
        this.attackType = attackType;
    }

    public Type getDefendType() {
        return defendType;
    }

    public void setDefendType(Type defendType) {
        this.defendType = defendType;
    }
}
