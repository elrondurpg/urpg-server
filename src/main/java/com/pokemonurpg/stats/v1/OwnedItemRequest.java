package com.pokemonurpg.stats.v1;

import com.pokemonurpg.lib.v1.requests.ChildRequest;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.entities.v1.Item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OwnedItemRequest extends ChildRequest {
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
