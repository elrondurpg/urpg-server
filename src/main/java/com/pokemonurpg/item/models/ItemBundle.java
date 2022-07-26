package com.pokemonurpg.item.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.item.input.ItemBundleInputDto;

@Entity
public class ItemBundle implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private Integer price;

    @OneToMany(mappedBy="bundle")
    @JsonIgnoreProperties({ "bundle"})
    private List<ItemBundleItem> items = new ArrayList<>();

    public ItemBundle() {}

    public ItemBundle(ItemBundleInputDto input) {
        this.update(input);
    }

    public void update(ItemBundleInputDto input) {
        setName(input.getName());
        setPrice(input.getPrice());
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

    
}
