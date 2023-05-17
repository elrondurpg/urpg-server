package com.pokemonurpg.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.model.NamedObject;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class KnownGymLeader implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public KnownGymLeader() {

    }

    public KnownGymLeader(String name) {
        setName(name);
    }

    public KnownGymLeader(KnownGymLeaderInputDto input) {
        this.update(input);
    }

    public void update(KnownGymLeaderInputDto input) {
        setName(input.getName());
    }

    @Override
    public Integer getDbid() {
        return dbid;
    }

    @Override
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