package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderRequest;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
@Table(name = "known_gym_leader")
public class GymLeader implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public GymLeader() {

    }

    public GymLeader(String name) {
        setName(name);
    }

    public GymLeader(GymLeaderRequest input) {
        this.update(input);
    }

    public void update(GymLeaderRequest input) {
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
