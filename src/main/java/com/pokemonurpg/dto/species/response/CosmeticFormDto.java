package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.object.CosmeticFormKey;

import java.util.Objects;

public class CosmeticFormDto {
    private String name;
    private String displayName;
    private String method;

    public CosmeticFormDto(CosmeticForm cosmeticForm) {
        if (cosmeticForm != null) {
            CosmeticFormKey key = cosmeticForm.getId();
            if (key != null) {
                name = key.getName();
            }
            displayName = cosmeticForm.getDisplayName();
            method = cosmeticForm.getMethod();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmeticFormDto that = (CosmeticFormDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, method);
    }
}
