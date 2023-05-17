package com.pokemonurpg.entities;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;

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
