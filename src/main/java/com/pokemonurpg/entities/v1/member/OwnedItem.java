package com.pokemonurpg.entities.v1.member;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.member.member.input.OwnedItemInputDto;
import com.pokemonurpg.entities.v1.item.Item;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owned_item")
@Getter
@Setter
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
    private Item item;

    @Column
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
}
