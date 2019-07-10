package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
@Table(name = "art_rank")
public class ArtRank {
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

    public ArtRank(Integer dbid, String name, String requirement) {
        this.dbid = dbid;
        this.name = name;
        this.requirement = requirement;
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
