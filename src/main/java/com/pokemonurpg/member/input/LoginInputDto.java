package com.pokemonurpg.member.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
