package com.pokemonurpg.gym.models;

import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.gym.input.KnownGymLeaderInputDto;

import javax.persistence.*;

@Entity
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
