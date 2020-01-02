package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.trainer.Item;
import com.pokemonurpg.object.trainer.OwnedItem;

public class OwnedItemDto {
    private String name;
    private int price;
    private String type;
    private int quantity;

    public OwnedItemDto() {
    }

    public OwnedItemDto(OwnedItem ownedItemRecord) {
        if (ownedItemRecord != null) {
            Item item = ownedItemRecord.getItem();
            if (item != null) {
                setName(item.getName());
                setPrice(item.getPrice());
                setType(item.getType());
            }

            setQuantity(ownedItemRecord.getQuantity());
        }
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
