package com.pokemonurpg.item.input;


import com.pokemonurpg.core.validation.ObjectCreation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ItemInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    @Min(1)
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
