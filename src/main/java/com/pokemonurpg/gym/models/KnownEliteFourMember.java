package com.pokemonurpg.gym.models;

import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;

@Entity
public class KnownEliteFourMember implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public KnownEliteFourMember() {

    }

    public KnownEliteFourMember(String name) {
        setName(name);
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
        this.name = name;
    }
}
