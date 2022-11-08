package com.pokemonurpg.configuration.v1.pokemon.type.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews;
import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;

@Entity
@JsonView(value = { ConfigurationViews.V1.Pokemon.Type.class, ConfigurationViews.V1.Pokemon.Species.Brief.class })
public class Type implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Type() {}

    public Type(String name) {
        this.name = name;
    }

    public Type(TypeInputDto input) {
        this.update(input);
    }

    public void update(TypeInputDto input) {
        setName(input.getName());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }
}
