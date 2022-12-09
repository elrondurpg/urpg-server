package com.pokemonurpg.entities.v1.creative;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.GetSpeciesArtRankView;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "art_rank")
public class ArtRank extends NamedEntity implements GetSpeciesArtRankView {

    @Column
    private String requirement;
}
