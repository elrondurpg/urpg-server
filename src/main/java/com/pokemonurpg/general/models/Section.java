package com.pokemonurpg.general.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.general.input.SectionInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Section() {}

    public Section(SectionInputDto input) {
        this.update(input);
    }

    public void update(SectionInputDto input) {
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
