package com.pokemonurpg.v2.lib.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GetListRequest {
    private int itemsPerPage;
    private int page;
}
