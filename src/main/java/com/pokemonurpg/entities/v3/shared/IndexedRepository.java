package com.pokemonurpg.entities.v3.shared;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IndexedRepository<T extends UrpgEntity> extends UrpgRepository<T, Integer> {
    T findByDbid(Integer dbid);
}
