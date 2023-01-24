package com.pokemonurpg.v2.lib.request;

import com.pokemonurpg.v2.entities.Entity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagedListRequest<EntityType extends Entity> {
    int itemsPerPage;
    int page;
    String sortBy;
    EntityType example;
}
