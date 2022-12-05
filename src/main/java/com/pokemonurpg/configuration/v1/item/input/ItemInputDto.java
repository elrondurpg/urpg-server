package com.pokemonurpg.configuration.v1.item.input;


import com.pokemonurpg.configuration.v1.item.models.Item;
import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@UniqueName(type = Item.class)
public class ItemInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    @Min(0)
    private Integer price;

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "^(TM|HM|Held|Mail|Evolution|Other|Mega|Special|Berry|Contest|Fossil|ZCrystal)$")
    private String type;

    @Size(min = 3, max = 720)
    private String description;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
