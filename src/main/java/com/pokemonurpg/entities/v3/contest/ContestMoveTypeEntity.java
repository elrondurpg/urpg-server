package com.pokemonurpg.entities.v3.contest;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesContestMoveTypeView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class ContestMoveTypeEntity extends NamedEntity implements GetSpeciesContestMoveTypeView {

    @Column
    private String description;

    @Column
    private Integer score;

    @Column
    private Integer jam;
}
