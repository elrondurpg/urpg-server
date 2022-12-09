package com.pokemonurpg.configuration.v1.member.member.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.entities.v1.item.Item;

public class OwnedItemInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Item.class)
    private String item;

    @Min(1)
    private Integer quantity;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
