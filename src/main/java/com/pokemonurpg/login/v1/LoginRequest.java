package com.pokemonurpg.login.v1;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
