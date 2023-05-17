package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String description;

    public Ability() {}

    public Ability(Integer dbid, String name, String description) {
        this.dbid = dbid;
        this.name = name;
        this.description = description;
    }

    public void cloneValuesFrom(Ability ability) {
        if (dbid == null) this.dbid = ability.getDbid();
        if (name == null) this.name = ability.getName();
        if (description == null) this.description = ability.getDescription();
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
}
