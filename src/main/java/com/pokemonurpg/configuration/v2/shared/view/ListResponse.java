package com.pokemonurpg.configuration.v2.shared.view;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class ListResponse<T> implements ListView<T> {
    Page<? extends T> page;

    public ListResponse(Page<? extends T> page) {
        this.page = page;
    }

    @Override
    public List<T> getContent() {
        return page.getContent().stream().map(item -> (T) item).collect(Collectors.toList());
    }

    @Override
    public long getTotalItems() {
        return page.getTotalElements();
    }

    @Override
    public int getPage() {
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
