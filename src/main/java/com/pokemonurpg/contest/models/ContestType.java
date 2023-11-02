package com.pokemonurpg.contest.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.contest.input.ContestTypeInputDto;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;

@Table(name = "contest_type")
@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class ContestType implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ContestType() {}

    public ContestType(ContestTypeInputDto input) {
        this.update(input);
    }

    public void update(ContestTypeInputDto input) {
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