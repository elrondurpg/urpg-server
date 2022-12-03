package com.pokemonurpg.configuration.v3.pokemon.species.shared.view;

import java.util.List;
import java.util.Set;

public interface SpeciesFullResponseView extends SpeciesBriefResponseView {
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
    SpeciesFullResponseStoryRankView getStoryRank();
    SpeciesFullResponseArtRankView getArtRank();
    SpeciesFullResponseParkRankView getParkRank();
    SpeciesFullResponseParkLocationView getParkLocation();
    Integer getContestCredits();
    String getFormName();
    List<? extends SpeciesFullResponseSpeciesAttackView> getAttacks();
    List<? extends SpeciesFullResponseSpeciesAbilityView> getAbilities();
    Integer getLegendaryTier();
    String getAlteredFormMethod();
    Set<? extends SpeciesFullResponseCosmeticFormView> getCosmeticForms();
    SpeciesFullResponsePreEvolutionView getPreEvolution();
    String getEvolutionMethod();
    Integer getEvolutionExpRequirement();
    SpeciesFullResponsePreMegaView getPreMega();
    String getMegaStone();
    String getMegaSuffix();
    Boolean getBattleOnly();
}
