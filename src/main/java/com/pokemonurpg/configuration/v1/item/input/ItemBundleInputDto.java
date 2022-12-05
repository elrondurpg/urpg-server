package com.pokemonurpg.configuration.v1.item.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pokemonurpg.configuration.v1.item.models.ItemBundle;
import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

@UniqueName(type = ItemBundle.class)
public class ItemBundleInputDto implements UniquelyNamedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer price;

    private List<@Valid ItemBundleItemInputDto> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<ItemBundleItemInputDto> getItems() {
        return items;
    }

    public void setItems(List<ItemBundleItemInputDto> items) {
        this.items = items;
    }
}