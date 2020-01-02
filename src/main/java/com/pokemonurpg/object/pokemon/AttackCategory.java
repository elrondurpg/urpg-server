package com.pokemonurpg.object.pokemon;

import javax.persistence.*;

@Entity
@Table(name = "attack_category")
public class AttackCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public AttackCategory() {
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
