package com.pokemonurpg.configuration.v1.creative.storyrank.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.creative.storyrank.StoryRankViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { StoryRankViews.Brief.class, SpeciesViews.Full.class })
public class StoryRank extends NamedConfigurationModel {

    @Column
    private String requirement;
}
