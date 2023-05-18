package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeInputDto;
import com.pokemonurpg.lib.v1.model.NamedObject;

import javax.persistence.*;

@Table(name = "contest_attribute")
@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class ContestAttribute implements NamedObject {

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
