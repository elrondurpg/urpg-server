package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.champions.KnownChampionInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class KnownChampion implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public KnownChampion() {

    }

    public KnownChampion(String name) {
        setName(name);
    }

    public KnownChampion(KnownChampionInputDto input) {
        this.update(input);
    }

    public void update(KnownChampionInputDto input) {
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
