package com.pokemonurpg.configuration.v1.itembundles;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pokemonurpg.lib.v1.request.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.entities.v1.Item;

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
