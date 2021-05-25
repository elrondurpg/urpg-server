package com.pokemonurpg.gym.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KnownGymLeader {

    @Id
    @Column
    private String name;

    public KnownGymLeader() {

    }

    public KnownGymLeader(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
