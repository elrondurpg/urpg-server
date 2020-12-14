package com.pokemonurpg.contest.models;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;

import javax.persistence.*;

@Table(name = "oras_contest_move_type")
@Entity
public class ORASContestMoveType extends ContestMoveType {
    public ORASContestMoveType() {
        super();
    }

    public ORASContestMoveType(ContestMoveTypeInputDto input) {
        super(input);
    }
}
