package com.pokemonurpg.object.pokemon;

import javax.persistence.*;

@Table(name = "oras_contest_move_type")
@Entity
public class ORASContestMoveType {

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

    public ORASContestMoveType() {
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
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getJam() {
        return jam;
    }

    public void setJam(Integer jam) {
        this.jam = jam;
    }
}
