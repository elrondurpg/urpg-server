package com.pokemonurpg.v2.lib.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public abstract class GetListResponse<T> {
    long totalItems;
    int totalPages;
    int itemsPerPage;
    int page;
    List<T> items;
}
