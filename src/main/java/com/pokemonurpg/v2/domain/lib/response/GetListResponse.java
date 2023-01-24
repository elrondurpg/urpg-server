package com.pokemonurpg.v2.domain.lib.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public abstract class GetListResponse<T> {
    int totalItems;
    int totalPages;
    int itemsPerPage;
    int page;
    List<T> items;
}
