package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationRequest;

import javax.persistence.*;

@Entity
@Table(name = "park_location")
@JsonView(value = { View.MemberView.Pokemon.class })
public class ParkLocation implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ParkLocation() {
    }

    public ParkLocation(ParkLocationRequest input) {
        this.update(input);
    }

    public void update(ParkLocationRequest input) {
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
