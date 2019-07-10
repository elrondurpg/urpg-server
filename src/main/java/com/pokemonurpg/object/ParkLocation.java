package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
@Table(name = "park_location")
public class ParkLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ParkLocation() {
    }

    public ParkLocation(Integer dbid, String name) {
        this.dbid = dbid;
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
}
