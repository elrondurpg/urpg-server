package com.pokemonurpg.entities.v1.shared;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedRepository<T> extends IndexedRepository<T> {
    T findByName(String name);
    T findFirstByNameStartingWith(String name);
}
