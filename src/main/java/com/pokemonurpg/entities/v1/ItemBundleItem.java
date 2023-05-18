package com.pokemonurpg.entities.v1;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.itembundles.ItemBundleItemInputDto;

@Entity
@Table(name = "item_bundle_item")
public class ItemBundleItem {
    @EmbeddedId
    ItemBundleItemKey id;

    @ManyToOne
    @MapsId("bundle_dbid")
    @JoinColumn(name = "bundle_dbid")
    private ItemBundle bundle;

    @ManyToOne
    @MapsId("item_dbid")
    @JoinColumn(name = "item_dbid")
    private Item item;

    @Column
    private Integer quantity;

    public ItemBundleItem() {}

    public ItemBundleItem(ItemBundleItemInputDto input, ItemBundle bundle, Item item) {
        this.update(input);
        this.id = new ItemBundleItemKey(bundle.getDbid(), item.getDbid());
        this.bundle = bundle;
        this.item = item;
        if (this.quantity == null) this.quantity = 1;
    }

    public void update(ItemBundleItemInputDto input) {
        setQuantity(input.getQuantity());
    }

    public ItemBundle getBundle() {
        return bundle;
    }

    public void setBundle(ItemBundle bundle) {
        this.bundle = bundle;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity != null) {
            this.quantity = quantity;
        }
    } 
}
