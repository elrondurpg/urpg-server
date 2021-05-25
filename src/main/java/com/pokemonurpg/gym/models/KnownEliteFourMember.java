package com.pokemonurpg.gym.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KnownEliteFourMember {

    @Id
    @Column
    private String name;

    public KnownEliteFourMember() {

    }

    public KnownEliteFourMember(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
