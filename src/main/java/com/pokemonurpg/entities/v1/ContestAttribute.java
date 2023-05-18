package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeRequest;
import com.pokemonurpg.lib.v1.models.NamedObject;

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

    public ContestAttribute(ContestAttributeRequest input) {
        this.update(input);
    }

    public void update(ContestAttributeRequest input) {
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
