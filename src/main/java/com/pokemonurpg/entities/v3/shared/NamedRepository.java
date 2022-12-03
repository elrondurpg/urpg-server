package com.pokemonurpg.entities.v3.shared;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedRepository<T extends UrpgEntity> extends IndexedRepository<T> {
    T findByName(String name);
    T findFirstByNameStartingWith(String name);
}
