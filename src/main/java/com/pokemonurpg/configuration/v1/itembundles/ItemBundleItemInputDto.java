package com.pokemonurpg.configuration.v1.itembundles;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;
import com.pokemonurpg.entities.Item;

public class ItemBundleItemInputDto extends ChildInputDto {

    @NotNull
    @ExistsInDb(type = Item.class)
    private String item;

    @Min(0)
    @Max(Integer.MAX_VALUE)
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
