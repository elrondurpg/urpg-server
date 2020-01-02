package com.pokemonurpg.object.battle;

import javax.persistence.*;

@Entity
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column(name = "perma_allowed")
    private boolean permaAllowed;

    public Terrain() {}
    public Terrain(String name) {
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

    public boolean isPermaAllowed() {
        return permaAllowed;
    }

    public void setPermaAllowed(boolean permaAllowed) {
        this.permaAllowed = permaAllowed;
    }
}
