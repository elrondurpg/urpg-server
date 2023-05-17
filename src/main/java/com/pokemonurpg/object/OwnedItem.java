package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
@Table(name = "owned_item")
public class OwnedItem {

    @EmbeddedId
    OwnedItemKey id;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", insertable = false, updatable = false)
    private Member trainer;

    @ManyToOne
    @JoinColumn(name="item_dbid", insertable = false, updatable = false)
    private Item item;

    @Column
    private int quantity;

    public OwnedItem() {
    }

    public OwnedItem(int trainerDbid, int itemDbid, int quantity) {
        OwnedItemKey id = new OwnedItemKey(trainerDbid, itemDbid);
        this.id = id;
        this.quantity = quantity;
    }

    public OwnedItemKey getId() {
        return id;
    }

    public void setId(OwnedItemKey id) {
        this.id = id;
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        this.trainer = trainer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
