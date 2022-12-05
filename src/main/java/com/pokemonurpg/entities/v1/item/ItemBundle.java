package com.pokemonurpg.entities.v1.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.configuration.v1.item.input.ItemBundleInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemBundle extends NamedEntity {
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
}
