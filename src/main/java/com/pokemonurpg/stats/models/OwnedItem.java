package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.OwnedItemInputDto;

import javax.persistence.*;

@Entity
@Table(name = "owned_item")
@JsonView(value = { View.MemberView.Summary.class })
public class OwnedItem {

    @EmbeddedId
    OwnedItemKey id;

    @ManyToOne
    @MapsId("trainer_dbid")
    @JoinColumn(name="trainer_dbid")
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration"})
    private Member trainer;

    @ManyToOne
    @MapsId("item_dbid")
    @JoinColumn(name="item_dbid")
    private Item item;

    @Column
    private Integer quantity = 1;

    public OwnedItem() {
    }

    public OwnedItem(OwnedItemInputDto input, Member member, Item item) {
        this.update(input);
        this.id = new OwnedItemKey(member.getDbid(), item.getDbid());
        setTrainer(member);
        setItem(item);
    }

    public void update(OwnedItemInputDto input) {
        setQuantity(input.getQuantity());
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