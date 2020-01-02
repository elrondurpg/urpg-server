package com.pokemonurpg.object.pokemon;

import javax.persistence.*;

@Entity
public class ContestRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ContestRank() {}
    public ContestRank(String name) {
        this.name = name;
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

    public String toString() {
        return "{ \"dbid\": " + dbid + ",\n\"name\": \"" + name + "\"}";
    }
}
