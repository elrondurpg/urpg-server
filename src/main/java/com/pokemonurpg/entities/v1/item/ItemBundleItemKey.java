package com.pokemonurpg.entities.v1.item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemBundleItemKey implements Serializable {
    @Column(name = "bundle_dbid")
    private Integer bundleDbid;

    @Column(name = "item_dbid")
    private Integer itemDbid;
}
