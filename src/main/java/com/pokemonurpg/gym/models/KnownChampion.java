package com.pokemonurpg.gym.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KnownChampion {

    @Id
    @Column
    private String name;

    public KnownChampion() {

    }

    public KnownChampion(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
