package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = GetSpeciesAttackView.class)
public interface GetSpeciesAttackView extends NamedEntityResponseView {
    ListSpeciesTypeView getType();
    String getDescription();
    Integer getPower();
    Integer getAccuracy();
    Integer getPp();
    GetSpeciesAttackCategoryView getCategory();
    GetSpeciesAttackTargetTypeView getTarget();
    Boolean getContact();
    Boolean getSnatch();
    Boolean getSubstitute();
    Boolean getSheerForce();
    Boolean getMagicCoat();
    GetSpeciesContestMoveTypeView getRseContestMoveType();
    GetSpeciesContestAttributeView getRseContestAttribute();
    GetSpeciesContestMoveTypeView getOrasContestMoveType();
    GetSpeciesContestAttributeView getOrasContestAttribute();
}
