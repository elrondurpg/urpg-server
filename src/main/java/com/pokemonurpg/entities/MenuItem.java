package com.pokemonurpg.entities;

import com.pokemonurpg.configuration.v1.sitemenuitem.MenuItemInputDto;
import com.pokemonurpg.lib.model.NamedObject;

import javax.persistence.*;

@Entity
public class MenuItem implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private Integer indent = 0;

    @Column
    private Integer position = 100;

    public MenuItem() {

    }

    public MenuItem(MenuItemInputDto input) {
        this.update(input);
    }

    public void update(MenuItemInputDto input) {
        setName(input.getName());
        setUrl(input.getUrl());
        setPosition(input.getPosition());
        setIndent(input.getIndent());
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url != null) {
            this.url = url;
        }
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        if (position != null) {
            this.position = position;
        }
    }

    public Integer getIndent() {
        return indent;
    }

    public void setIndent(Integer indent) {
        this.indent = indent;
    }
}
