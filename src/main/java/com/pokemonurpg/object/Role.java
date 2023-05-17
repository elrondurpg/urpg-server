package com.pokemonurpg.object;

import com.pokemonurpg.dto.security.RoleInputDto;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @Column
    private String name;

    public Role() {
    }

    public Role(RoleInputDto input) {
        if (input != null) {
            setName(input.getName());
        }
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
