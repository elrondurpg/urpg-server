package com.pokemonurpg.configuration.v1.pokemon.species.shared.view;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.shared.view.NamedResponse;

public interface GetSpeciesView extends NamedResponse {
    @JsonView(GetSpeciesView.class) 
    Integer getDexno();
    
    @JsonView(GetSpeciesView.class) 
    ListSpeciesTypeView getType1();
    
    @JsonView(GetSpeciesView.class) 
    ListSpeciesTypeView getType2();
    
    @JsonView(GetSpeciesView.class) 
    String getDisplayName();
    
    @JsonView(GetSpeciesView.class) 
    String getClassification();
    
    @JsonView(GetSpeciesView.class) 
    Integer getHp();
    
    @JsonView(GetSpeciesView.class) 
    Integer getAttack();
    
    @JsonView(GetSpeciesView.class) 
    Integer getDefense();
    
    @JsonView(GetSpeciesView.class) 
    Integer getSpecialAttack();
    
    @JsonView(GetSpeciesView.class) 
    Integer getSpecialDefense();
    
    @JsonView(GetSpeciesView.class) 
    Integer getSpeed();
    
    @JsonView(GetSpeciesView.class) 
    Double getHeight();
    
    @JsonView(GetSpeciesView.class) 
    Double getWeight();
    
    @JsonView(GetSpeciesView.class) 
    Boolean getMaleAllowed();
    
    @JsonView(GetSpeciesView.class) 
    Boolean getFemaleAllowed();
    
    @JsonView(GetSpeciesView.class) 
    Integer getPokemart();
    
    @JsonView(GetSpeciesView.class) 
    GetSpeciesStoryRankView getStoryRank();
    
    @JsonView(GetSpeciesView.class) 
    GetSpeciesArtRankView getArtRank();
    
    @JsonView(GetSpeciesView.class) 
    GetSpeciesParkRankView getParkRank();
    
    @JsonView(GetSpeciesView.class) 
    GetSpeciesParkLocationView getParkLocation();
    
    @JsonView(GetSpeciesView.class) 
    Integer getContestCredits();
    
    @JsonView(GetSpeciesView.class) 
    String getFormName();
    
    @JsonView(GetSpeciesView.class) 
    List<? extends GetSpeciesSpeciesAttackView> getAttacks();
    
    @JsonView(GetSpeciesView.class) 
    List<? extends GetSpeciesSpeciesAbilityView> getAbilities();
    
    @JsonView(GetSpeciesView.class) 
    Integer getLegendaryTier();
    
    @JsonView(GetSpeciesView.class) 
    String getAlteredFormMethod();
    
    @JsonView(GetSpeciesView.class) 
    Set<? extends GetSpeciesCosmeticFormView> getCosmeticForms();
    
    @JsonView(GetSpeciesView.class) 
    @JsonSerialize(as = GetSpeciesPreEvolutionView.class)
    GetSpeciesPreEvolutionView getPreEvolution();
    
    @JsonView(GetSpeciesView.class) 
    String getEvolutionMethod();
    
    @JsonView(GetSpeciesView.class) 
    Integer getEvolutionExpRequirement();
    
    @JsonView(GetSpeciesView.class) 
    @JsonSerialize(as = GetSpeciesPreMegaView.class)
    GetSpeciesPreMegaView getPreMega();
    
    @JsonView(GetSpeciesView.class) 
    String getMegaStone();
    
    @JsonView(GetSpeciesView.class) 
    String getMegaSuffix();
    
    @JsonView(GetSpeciesView.class) 
    Boolean getBattleOnly();
}
