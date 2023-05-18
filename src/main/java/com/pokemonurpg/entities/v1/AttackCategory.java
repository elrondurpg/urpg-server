package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryRequest;
import com.pokemonurpg.lib.v1.models.NamedObject;

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

    public AttackCategory(AttackCategoryRequest input) {
        this.update(input);
    }

    public void update(AttackCategoryRequest input) {
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
