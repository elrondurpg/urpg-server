package com.pokemonurpg.configuration.v1.contest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;

@MappedSuperclass
@JsonView(value = { SpeciesViews.Full.class })
public class ContestMoveType implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer score;

    @Column
    private Integer jam;

    public ContestMoveType() {
    }

    public ContestMoveType(ContestMoveTypeInputDto input) {
        this.update(input);
    }

    public void update(ContestMoveTypeInputDto input) {
        setName(input.getName());
        setDescription(input.getDescription());
        setScore(input.getScore());
        setJam(input.getJam());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        if (score != null) {
            this.score = score;
        }
    }

    public Integer getJam() {
        return jam;
    }

    public void setJam(Integer jam) {
        if (jam != null) {
            this.jam = jam;
        }
    }
}
