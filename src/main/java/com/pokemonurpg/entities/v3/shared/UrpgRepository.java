package com.pokemonurpg.entities.v3.shared;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UrpgRepository<T, U> extends JpaRepository<T, U> {
    @Override
    public <S extends T> PageImpl<S> findAll(Example<S> example, Pageable pageable);
    @Override
    public PageImpl<T> findAll(Pageable pageable);
}
