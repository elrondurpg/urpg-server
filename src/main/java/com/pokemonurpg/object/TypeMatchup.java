package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
@Table(name = "type_matchup")
public class TypeMatchup {
    @EmbeddedId
    TypeMatchupKey id;

    @Column
    double multiplier;

    public TypeMatchup() {
    }

    public TypeMatchup(TypeMatchupKey id, double multiplier) {
        this.id = id;
        this.multiplier = multiplier;
    }

    public TypeMatchupKey getId() {
        return id;
    }

    public void setId(TypeMatchupKey id) {
        this.id = id;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}
