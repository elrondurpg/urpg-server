package com.pokemonurpg.contest.models;

import com.pokemonurpg.contest.input.ContestAttributeInputDto;

import javax.persistence.*;

@Table(name = "contest_attribute")
@Entity
public class ContestAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ContestAttribute() {}

    public ContestAttribute(ContestAttributeInputDto input) {
        this.update(input);
    }

    public void update(ContestAttributeInputDto input) {
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
