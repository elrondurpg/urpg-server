package com.pokemonurpg.configuration.v1.lib.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@JsonView(value = { ConfigurationViews.V1.class })
@Getter
@Setter
public abstract class NamedConfigurationModel extends IndexedConfigurationModel {

    @Column
    @JsonView(value = { ConfigurationViews.V1.class })
    private String name;

    public NamedConfigurationModel() {
        
    }
}
