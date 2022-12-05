package com.pokemonurpg.entities.v1.site;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.pokemonurpg.entities.v1.shared.NamedEntity;
import com.pokemonurpg.site.input.MenuItemInputDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MenuItem extends NamedEntity {

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
}
