package com.pokemonurpg.general.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.general.input.NatureInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class Nature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Nature() {
    }

    public Nature(NatureInputDto input) {
        this.update(input);
    }

    public void update(NatureInputDto input) {
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
