package com.pokemonurpg.v2.lib.response;

import com.pokemonurpg.v2.entities.Entity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageResponse<EntityType extends Entity> {
    long totalItems;
    int totalPages;
    int itemsPerPage;
    int page;
    List<EntityType> items;
}
