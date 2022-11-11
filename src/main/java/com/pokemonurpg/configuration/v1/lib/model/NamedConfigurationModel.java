package com.pokemonurpg.configuration.v1.lib.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@JsonView(value = { ConfigurationViews.V1.class })
@Getter
@Setter
public abstract class NamedConfigurationModel extends ConfigurationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public NamedConfigurationModel() {
        
    }
}
