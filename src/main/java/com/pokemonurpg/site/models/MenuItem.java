package com.pokemonurpg.site.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.site.input.MenuItemInputDto;

import javax.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @OneToOne
    @JoinColumn(name = "parent_dbid")
    @JsonIgnoreProperties({ "parent", "url", "position" })
    private MenuItem parent;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private Integer position = 100;

    public MenuItem() {

    }

    public MenuItem(MenuItemInputDto input) {
        this.update(input);
    }

    public void update(MenuItemInputDto input) {

    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        if (parent != null) {
            this.parent = parent;
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
}
