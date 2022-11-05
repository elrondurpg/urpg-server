package com.pokemonurpg.lib.v1;

import org.springframework.data.domain.Example;

public abstract class FilterableGetParams<T> extends GetParams {
    public abstract Example<T> asExample();
}
