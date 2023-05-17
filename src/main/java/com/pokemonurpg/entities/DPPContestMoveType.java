package com.pokemonurpg.entities;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;

import javax.persistence.*;

@Table(name = "dpp_contest_move_type")
@Entity
public class DPPContestMoveType extends ContestMoveType {
    public DPPContestMoveType() {
        super();
    }

    public DPPContestMoveType(ContestMoveTypeInputDto input) {
        super(input);
    }
}
