package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectRequest;

import javax.persistence.*;

@Table(name = "oras_contest_move_type")
@Entity
public class ORASContestEffect extends ContestEffect {
    public ORASContestEffect() {
        super();
    }

    public ORASContestEffect(ContestEffectRequest input) {
        super(input);
    }
}
