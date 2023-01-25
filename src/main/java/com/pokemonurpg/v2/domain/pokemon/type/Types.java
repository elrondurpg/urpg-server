package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.PageNotFoundException;
import com.pokemonurpg.v2.lib.request.PagedListRequest;
import com.pokemonurpg.v2.lib.response.PageResponse;
import com.pokemonurpg.v2.lib.validator.Validator;
import com.pokemonurpg.v2.entities.pokemon.Type;

interface Types {
    Validator<Type> getValidator();
    Type save(Type entity);
    Type getByDbid(int dbid);
    Type getByName(String name);
    Type getByNameStartingWith(String begin);
    boolean existsByDbid(int dbid);
    boolean existsByName(String name);
    PageResponse<? extends Type> getPageByExample(PagedListRequest<Type> pagedListRequest) throws PageNotFoundException;
    Type deleteByDbid(Integer dbid);
}
