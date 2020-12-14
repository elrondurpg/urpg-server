package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.item.models.Item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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