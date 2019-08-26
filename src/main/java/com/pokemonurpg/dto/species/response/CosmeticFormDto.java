package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.object.CosmeticFormKey;

import java.util.Objects;

public class CosmeticFormDto {
    private String name;
    private String formName;
    private String method;

    public CosmeticFormDto(CosmeticForm cosmeticForm) {
        if (cosmeticForm != null) {
            CosmeticFormKey key = cosmeticForm.getId();
            if (key != null) {
                name = key.getName();
            }
            formName = cosmeticForm.getFormName();
            method = cosmeticForm.getMethod();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
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
                Objects.equals(formName, that.formName) &&
                Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, formName, method);
    }
}
