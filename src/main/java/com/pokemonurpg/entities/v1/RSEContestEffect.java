package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectRequest;

import javax.persistence.*;

@Table(name = "rse_contest_move_type")
@Entity
public class RSEContestEffect extends ContestEffect {
    public RSEContestEffect() {
        super();
    }

    public RSEContestEffect(ContestEffectRequest input) {
        super(input);
    }
}
