package com.pokemonurpg.configuration.v1.pokemon.nature.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.pokemon.nature.input.NatureInputDto;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;

@Entity
@JsonView(value = { ConfigurationViews.V1.Pokemon.Nature.class })
public class Nature implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Nature() {
    }

    public Nature(NatureInputDto input) {
        this.update(input);
    }

    public void update(NatureInputDto input) {
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
