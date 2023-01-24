package com.pokemonurpg.v2.domain.lib.request;

import com.pokemonurpg.v2.entities.Entity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageRequest<EntityType extends Entity> {
    int itemsPerPage;
    int page;
    EntityType example;
}
