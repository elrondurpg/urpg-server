package com.pokemonurpg.entities;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;

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
