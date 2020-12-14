package com.pokemonurpg.general.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.general.input.ObtainedInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class Obtained {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Obtained() {
    }

    public Obtained(ObtainedInputDto input) {
        this.update(input);
    }

    public void update(ObtainedInputDto input) {
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
        this.name = name;
    }
}
