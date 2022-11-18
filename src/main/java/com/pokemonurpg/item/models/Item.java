package com.pokemonurpg.item.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.item.input.ItemInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { AttackViews.Full.class, View.MemberView.Summary.class })
public class Item implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private String type;

    @Column
    private String description;

    public Item() {
    }

    public Item(ItemInputDto item) {
        this.update(item);
		if (price == null) price = 0;
    }

    public void update(ItemInputDto input) {
        setName(input.getName());
        setPrice(input.getPrice());
        setType(input.getType());
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        if (price != null) {
            this.price = price;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null) {
            this.type = type;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (type != null) {
            this.description = description;
        }
    }
}
