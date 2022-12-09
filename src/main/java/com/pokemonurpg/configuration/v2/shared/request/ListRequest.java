package com.pokemonurpg.configuration.v2.shared.request;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ListRequest {
    @Min(0)
    Integer page;

    @Min(1)
    Integer itemsPerPage;

    String sortBy;
}
