package com.pokemonurpg.entities.v1.shared;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class NamedEntity extends IndexedEntity {
    @Column
    @JsonView(value = { ConfigurationViews.V1.class })
    private String name;
}
