package com.pokemonurpg.item.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.models.ItemBundle;

public class ItemBundleItemInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = ItemBundle.class)
    private String bundle;

    @NotNull
    @ExistsInDb(type = Item.class)
    private String item;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer quantity;

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

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
