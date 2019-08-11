package com.pokemonurpg.dto;

public class InputDto {
    private boolean delete = false;

    public InputDto() {
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
