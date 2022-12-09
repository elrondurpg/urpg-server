package com.pokemonurpg.entities.v1.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.pokemonurpg.configuration.v1.item.input.ItemInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item extends NamedEntity {

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
}
