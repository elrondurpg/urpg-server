package com.pokemonurpg.configuration.v2.shared.view;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;

@JsonSerialize(as = WrappedListResponse.class)
@AllArgsConstructor
public class WrappedListResponse<Wrapper extends EntityWrapper> implements ListView<EntityWrapper> {
    protected ListView<?> wrappedList;
    protected Class<Wrapper> wrapperClass;

    @Override
    public List<Wrapper> getContent() {
        return (List<Wrapper>) EntityWrapper.wrapEntitiesInClass(wrappedList.getContent(), wrapperClass);
    }

    @Override
    public long getTotalItems() {
        return wrappedList.getTotalItems();
    }

    @Override
    public int getPage() {
        return wrappedList.getPage();
    }

    @Override
    public int getTotalPages() {
        return wrappedList.getTotalPages();
    }

    @Override
    public int getSize() {
        return wrappedList.getSize();
    }
}
