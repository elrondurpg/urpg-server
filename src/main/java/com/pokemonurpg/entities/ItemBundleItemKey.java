package com.pokemonurpg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemBundleItemKey implements Serializable {
    @Column(name = "bundle_dbid")
    private Integer bundleDbid;

    @Column(name = "item_dbid")
    private Integer itemDbid;

    public ItemBundleItemKey() {}

    public ItemBundleItemKey(Integer bundleDbid, Integer itemDbid) {
        this.bundleDbid = bundleDbid;
        this.itemDbid = itemDbid;
    }

    public Integer getBundleDbid() {
        return bundleDbid;
    }

    public Integer getItemDbid() {
        return itemDbid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bundleDbid == null) ? 0 : bundleDbid.hashCode());
        result = prime * result + ((itemDbid == null) ? 0 : itemDbid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemBundleItemKey other = (ItemBundleItemKey) obj;
        if (bundleDbid == null) {
            if (other.bundleDbid != null)
                return false;
        } else if (!bundleDbid.equals(other.bundleDbid))
            return false;
        if (itemDbid == null) {
            if (other.itemDbid != null)
                return false;
        } else if (!itemDbid.equals(other.itemDbid))
            return false;
        return true;
    }
    
    
}
