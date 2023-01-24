package com.pokemonurpg.v2.domain.pokemon.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeModelTest {
    private final static int DBID = 123;
    private final static String NAME = "NAME";
    @Test
    public void build() {
        TypeModel model = TypeModel.builder()
                .dbid(DBID)
                .name(NAME)
                .build();
        assertEquals(DBID, model.getDbid());
        assertEquals(NAME, model.getName());
    }
}