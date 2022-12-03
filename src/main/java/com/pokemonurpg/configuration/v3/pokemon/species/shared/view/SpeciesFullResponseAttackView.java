package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public interface SpeciesFullResponseAttackView extends NamedEntityResponseView {
    SpeciesBriefResponseTypeView getType();
    String getDescription();
    Integer getPower();
    Integer getAccuracy();
    Integer getPp();
    SpeciesFullResponseAttackCategoryView getCategory();
    SpeciesFullResponseAttackTargetTypeView getTarget();
    Boolean getContact();
    Boolean getSnatch();
    Boolean getSubstitute();
    Boolean getSheerForce();
    Boolean getMagicCoat();
    SpeciesFullResponseContestMoveTypeView getRseContestMoveType();
    SpeciesFullResponseContestAttributeView getRseContestAttribute();
    SpeciesFullResponseContestMoveTypeView getOrasContestMoveType();
    SpeciesFullResponseContestAttributeView getOrasContestAttribute();
}
