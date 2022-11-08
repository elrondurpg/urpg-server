package com.pokemonurpg.lib.input.v1;

import org.springframework.data.domain.Example;

public class FilterlessGetParams<T> extends FilterableGetParams<T> {

    @Override
    public Example<T> asExample() {
        return null;
    }
    
}
