package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.badges.BadgeRequest;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Badge implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public Badge() {}

    public Badge(BadgeRequest input) {
        this.update(input);
    }

    public void update(BadgeRequest input) {
        setName(input.getName());
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
}
