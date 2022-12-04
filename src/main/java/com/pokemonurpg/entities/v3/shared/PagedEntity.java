package com.pokemonurpg.entities.v3.shared;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;

import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;

import lombok.Getter;

@Getter
public class PagedEntity<T> implements PagedResponse<T> {

    private PageImpl<? extends T> page;

    public PagedEntity(PageImpl<? extends T> page) {
        this.page = page;
    }

    @Override
    public List<T> getContent() {
        return page.getContent().stream().map(item -> (T) item).collect(Collectors.toList());
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public int getNumber() {
        return page.getNumber();
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public int getSize() {
        return page.getSize();
    }
    
}
