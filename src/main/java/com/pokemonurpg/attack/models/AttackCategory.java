package com.pokemonurpg.attack.models;

import com.pokemonurpg.attack.input.AttackCategoryInputDto;
import com.pokemonurpg.core.model.NamedObject;

import javax.persistence.*;

@Entity
@Table(name = "attack_category")
public class AttackCategory implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public AttackCategory() {
    }

    public AttackCategory(AttackCategoryInputDto input) {
        this.update(input);
    }

    public void update(AttackCategoryInputDto input) {
        setName(input.getName());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        if (dbid != null) {
            this.dbid = dbid;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }
}
