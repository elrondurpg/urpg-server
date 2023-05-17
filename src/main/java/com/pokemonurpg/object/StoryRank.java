package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
@Table(name = "story_rank")
public class StoryRank {
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

    public StoryRank(String name) {
        this.name = name;
    }

    public StoryRank(Integer dbid, String name, String requirement) {
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
