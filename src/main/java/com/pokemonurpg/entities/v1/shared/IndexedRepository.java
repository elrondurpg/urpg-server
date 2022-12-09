package com.pokemonurpg.entities.v1.shared;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IndexedRepository<T> extends UrpgRepository<T, Integer> {
    T findByDbid(Integer dbid);
}
