package com.pokemonurpg.login.dto;

import javax.validation.constraints.NotEmpty;

public class LoginInputDto {
    @NotEmpty
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
