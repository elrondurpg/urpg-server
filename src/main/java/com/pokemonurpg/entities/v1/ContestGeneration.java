package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.contestgenerations.ContestGenerationRequest;
import com.pokemonurpg.lib.v1.models.NamedObject;

import javax.persistence.*;

@Table(name = "contest_type")
@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class ContestGeneration implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ContestGeneration() {}

    public ContestGeneration(ContestGenerationRequest input) {
        this.update(input);
    }

    public void update(ContestGenerationRequest input) {
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
