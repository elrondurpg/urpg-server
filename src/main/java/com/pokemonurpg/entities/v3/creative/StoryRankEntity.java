package com.pokemonurpg.entities.v3.creative;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesStoryRankView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "story_rank")
public class StoryRankEntity extends NamedEntity implements GetSpeciesStoryRankView {

    @Column
    private String requirement;
}
