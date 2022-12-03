package com.pokemonurpg.configuration.v3.shared.response;

import java.util.List;

public class PagedResponseFake<T> implements PagedResponse<T> {

    @Override
    public List<T> getContent() {
        return null;
    }

    @Override
    public long getTotalElements() {
        return 0;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }
    
}
