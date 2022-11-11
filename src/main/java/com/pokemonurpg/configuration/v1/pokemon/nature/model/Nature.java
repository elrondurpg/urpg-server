package com.pokemonurpg.configuration.v1.pokemon.nature.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.nature.NatureViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonView(value = { NatureViews.Id.class })
@Getter
@Setter
public class Nature extends ConfigurationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Nature() {
    }
}
