package com.pokemonurpg.gym.models;

import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.gym.input.KnownEliteFourMemberInputDto;

import javax.persistence.*;

@Entity
public class KnownEliteFourMember implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public KnownEliteFourMember() {

    }

    public KnownEliteFourMember(String name) {
        setName(name);
    }

    public KnownEliteFourMember(KnownEliteFourMemberInputDto input) {
        this.update(input);
    }

    public void update(KnownEliteFourMemberInputDto input) {
        setName(input.getName());
    }

    @Override
    public Integer getDbid() {
        return dbid;
    }

    @Override
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
