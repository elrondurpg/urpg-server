package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.exception.PageNotFoundException;
import com.pokemonurpg.v2.domain.lib.request.PageRequest;
import com.pokemonurpg.v2.domain.lib.response.PageResponse;
import com.pokemonurpg.v2.domain.lib.validator.Validator;
import com.pokemonurpg.v2.entities.pokemon.Type;

interface Types {
    Validator<Type> getValidator();
    Type save(Type entity);
    Type getByDbid(int dbid);
    Type getByName(String name);
    Type getByNameStartingWith(String begin);
    boolean existsByDbid(int dbid);
    boolean existsByName(String name);
    PageResponse<Type> getPageByExample(PageRequest<Type> pageRequest) throws PageNotFoundException;
    Type deleteByDbid(int dbid);
}
