package com.pokemonurpg.entities.v1.contest;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.pokemonurpg.configuration.v1.attack.attack.shared.view.AttackResponseContestMoveTypePart;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class ContestMoveType extends NamedEntity implements AttackResponseContestMoveTypePart {

    @Column
    private String description;

    @Column
    private Integer score;

    @Column
    private Integer jam;
}
