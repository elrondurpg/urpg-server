package com.pokemonurpg.v2.domain.lib.response;

import com.pokemonurpg.v2.entities.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageResponse<EntityType extends Entity> {
    int totalItems;
    int totalPages;
    int itemsPerPage;
    int page;
    List<EntityType> items;
}
