package com.pokemonurpg.creative.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.creative.input.ParkLocationInputDto;

import javax.persistence.*;

@Entity
@Table(name = "park_location")
@JsonView(value = { View.MemberView.Pokemon.class, ConfigurationViews.V1.Pokemon.Species.Full.class })
public class ParkLocation implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ParkLocation() {
    }

    public ParkLocation(ParkLocationInputDto input) {
        this.update(input);
    }

    public void update(ParkLocationInputDto input) {
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
