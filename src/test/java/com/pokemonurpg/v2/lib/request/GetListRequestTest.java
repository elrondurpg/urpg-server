package com.pokemonurpg.v2.lib.request;

import com.pokemonurpg.v2.domain.pokemon.type.GetTypeListRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetListRequestTest {
    @Test
    public void setSortBy_isLowercase() {
        GetListRequest request = new GetTypeListRequest();
        request.setSortBy("NAME");
        assertEquals("name", request.getSortBy());
    }

    @Test
    public void setSortBy_isEmptyString() {
        GetListRequest request = new GetTypeListRequest();
        request.setSortBy("");
        assertNull(request.getSortBy());
    }

    @Test
    public void setSortBy_isNull() {
        GetListRequest request = new GetTypeListRequest();
        request.setSortBy(null);
        assertNull(request.getSortBy());
    }
}