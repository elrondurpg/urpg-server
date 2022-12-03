package com.pokemonurpg.entities.v3.creative;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesArtRankView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "art_rank")
public class ArtRankEntity extends NamedEntity implements GetSpeciesArtRankView {

    @Column
    private String requirement;
}
