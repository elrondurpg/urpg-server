package com.pokemonurpg.configuration.v1.member.member.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.OwnedItemInputDto;
import com.pokemonurpg.item.models.Item;

import javax.persistence.*;

@Entity
@Table(name = "owned_item")
public class OwnedItem {

    @EmbeddedId
    OwnedItemKey id;

    @ManyToOne
    @MapsId("trainer_dbid")
    @JoinColumn(name="trainer_dbid")
    private Member trainer;

    @ManyToOne
    @MapsId("item_dbid")
    @JoinColumn(name="item_dbid")
    @JsonView(value = { MemberViews.Full.class })
    private Item item;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Integer quantity = 0;

    public OwnedItem() {
    }

    public OwnedItem(OwnedItemInputDto input, Member member, Item item) {
        this.update(input);
        this.id = new OwnedItemKey(member.getDbid(), item.getDbid());
        setTrainer(member);
        setItem(item);
    }

    public OwnedItem(Member member, Item item, int quantity) {
        this.id = new OwnedItemKey(member.getDbid(), item.getDbid());
        setTrainer(member);
        setItem(item);
        setQuantity(quantity);
    }

    public void update(OwnedItemInputDto input) {
        setQuantity(input.getQuantity());
    }

    public void update(int quantity) {
        this.quantity += quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity != null) {
            this.quantity = quantity;
        }
    }
}
