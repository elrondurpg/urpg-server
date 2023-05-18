package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeRequest;
import com.pokemonurpg.lib.v1.models.NamedObject;

import javax.persistence.*;

@Entity
@Table(name = "attack_target_type")
public class AttackTargetType implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String description;

    public AttackTargetType() {
    }

    public AttackTargetType(AttackTargetTypeRequest input) {
        this.update(input);
    }

    public void update(AttackTargetTypeRequest input) {
        setName(input.getName());
        setDescription(input.getDescription());
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
        if (name != null) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }
}
