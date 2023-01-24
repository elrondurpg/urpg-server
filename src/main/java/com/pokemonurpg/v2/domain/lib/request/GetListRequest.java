package com.pokemonurpg.v2.domain.lib.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class GetListRequest {
    private int itemsPerPage;
    private int page;
}
