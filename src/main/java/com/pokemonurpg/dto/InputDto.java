package com.pokemonurpg.dto;

public class InputDto {
    private boolean deleted = false;

    public InputDto() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
