package com.pokemonurpg.entities.v3.shared;

import java.util.List;

import org.springframework.data.domain.PageImpl;

import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

public class PagedEntity<T> extends PageImpl<T> implements PagedResponse<T> {

    public PagedEntity(List<T> content) {
        super(content);
    }
    
}
