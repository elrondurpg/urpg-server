package com.pokemonurpg.entities;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;

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
