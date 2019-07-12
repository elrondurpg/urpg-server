package com.pokemonurpg.dto;

import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.object.CosmeticFormKey;

public class CosmeticFormDto extends DataDto {
    private int speciesDbid;
    private String name;
    private String displayName;
    private String method;

    public CosmeticFormDto(CosmeticForm cosmeticForm) {
        if (cosmeticForm != null) {
            CosmeticFormKey key = cosmeticForm.getId();
            if (key != null) {
                speciesDbid = key.getSpeciesDbid();
                name = key.getName();
            }
            displayName = cosmeticForm.getDisplayName();
            method = cosmeticForm.getMethod();
        }
    }

    public int getSpeciesDbid() {
        return speciesDbid;
    }

    public void setSpeciesDbid(int speciesDbid) {
        this.speciesDbid = speciesDbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
