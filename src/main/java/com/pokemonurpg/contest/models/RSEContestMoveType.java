package com.pokemonurpg.contest.models;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;

import javax.persistence.*;

@Table(name = "rse_contest_move_type")
@Entity
public class RSEContestMoveType extends ContestMoveType {
    public RSEContestMoveType() {
        super();
    }

    public RSEContestMoveType(ContestMoveTypeInputDto input) {
        super(input);
    }
}
