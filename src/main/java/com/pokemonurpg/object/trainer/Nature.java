package com.pokemonurpg.object.trainer;

import javax.persistence.*;

@Entity
public class Nature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Nature() {
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
