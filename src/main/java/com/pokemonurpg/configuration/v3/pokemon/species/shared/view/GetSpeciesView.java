package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

@JsonSerialize(as = GetSpeciesView.class)
public interface GetSpeciesView extends NamedEntityResponseView {
    Integer getDexno();
    ListSpeciesTypeView getType1();
    ListSpeciesTypeView getType2();
    String getDisplayName();
    String getClassification();
    Integer getHp();
    Integer getAttack();
    Integer getDefense();
    Integer getSpecialAttack();
    Integer getSpecialDefense();
    Integer getSpeed();
    Double getHeight();
    Double getWeight();
    Boolean getMaleAllowed();
    Boolean getFemaleAllowed();
    Integer getPokemart();
    GetSpeciesStoryRankView getStoryRank();
    GetSpeciesArtRankView getArtRank();
    GetSpeciesParkRankView getParkRank();
    GetSpeciesParkLocationView getParkLocation();
    Integer getContestCredits();
    String getFormName();
    List<? extends GetSpeciesSpeciesAttackView> getAttacks();
    List<? extends GetSpeciesSpeciesAbilityView> getAbilities();
    Integer getLegendaryTier();
    String getAlteredFormMethod();
    Set<? extends GetSpeciesCosmeticFormView> getCosmeticForms();
    GetSpeciesPreEvolutionView getPreEvolution();
    String getEvolutionMethod();
    Integer getEvolutionExpRequirement();
    GetSpeciesPreMegaView getPreMega();
    String getMegaStone();
    String getMegaSuffix();
    Boolean getBattleOnly();
}
