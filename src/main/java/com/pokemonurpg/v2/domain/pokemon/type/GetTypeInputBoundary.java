package com.pokemonurpg.v2.domain.pokemon.type;

public interface GetTypeInputBoundary {
    GetTypeListResponse getList(GetTypeListRequest request);
    GetTypeResponse getByName(String name);
    GetTypeResponse getByNameStartingWith(String begin);
    GetTypeResponse getByDbid(int dbid);
    boolean existsByName(String name);
    boolean existsByDbid(int dbid);
}
