package com.pokemonurpg.configuration.v1.shared.view;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;

import lombok.Getter;

@Getter
public class ListResponse<T> implements ListView<T> {
    PageImpl<? extends T> page;

    public ListResponse(PageImpl<? extends T> page) {
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
