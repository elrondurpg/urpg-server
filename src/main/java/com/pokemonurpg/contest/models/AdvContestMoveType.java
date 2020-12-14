package com.pokemonurpg.contest.models;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;

import javax.persistence.*;

@Table(name = "adv_contest_move_type")
@Entity
public class AdvContestMoveType extends ContestMoveType {
    public AdvContestMoveType() {
        super();
    }

    public AdvContestMoveType(ContestMoveTypeInputDto input) {
        super(input);
    }
}
