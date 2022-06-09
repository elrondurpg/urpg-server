package com.pokemonurpg.creative.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.creative.input.StoryRankInputDto;

import javax.persistence.*;

@Entity
@Table(name = "story_rank")
@JsonView(value = { View.MemberView.Pokemon.class })
public class StoryRank implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String requirement;

    public StoryRank() {
    }

    public StoryRank(StoryRankInputDto input) {
        this.update(input);
    }

    public void update(StoryRankInputDto input) {
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
