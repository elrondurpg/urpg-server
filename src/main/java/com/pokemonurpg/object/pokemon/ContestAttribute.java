package com.pokemonurpg.object.pokemon;

import javax.persistence.*;

@Table(name = "contest_attribute")
@Entity
public class ContestAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public ContestAttribute() {}

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
