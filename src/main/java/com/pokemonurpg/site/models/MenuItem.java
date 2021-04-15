package com.pokemonurpg.site.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.site.input.MenuItemInputDto;

import javax.persistence.*;

@Entity
public class MenuItem {
    @Id
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
