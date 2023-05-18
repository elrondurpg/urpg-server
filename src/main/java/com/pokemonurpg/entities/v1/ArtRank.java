package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.artranks.ArtRankRequest;

import javax.persistence.*;

@Entity
@Table(name = "art_rank")
@JsonView(value = { View.MemberView.Pokemon.class })
public class ArtRank implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String requirement;

    public ArtRank() {
    }

    public ArtRank(ArtRankRequest input) {
        this.update(input);
    }

    public void update(ArtRankRequest input) {
        setName(input.getName());
        setRequirement(input.getRequirement());
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        if (requirement != null) {
            this.requirement = requirement;
        }
    }
}
