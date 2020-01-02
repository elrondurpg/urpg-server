package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;
import com.pokemonurpg.object.pokemon.CosmeticForm;
import com.pokemonurpg.object.pokemon.CosmeticFormKey;

import java.util.Objects;

public class CosmeticFormInputDto extends InputDto {
    private String name;
    private String formName;
    private String method;

    public CosmeticFormInputDto() {}

    public CosmeticFormInputDto(CosmeticForm cosmeticForm) {
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
        CosmeticFormInputDto that = (CosmeticFormInputDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(formName, that.formName) &&
                Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, formName, method);
    }
}
