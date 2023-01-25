package com.pokemonurpg.v2.domain.pokemon.type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface JpaTypeRepository extends JpaRepository<JpaTypeModel, Integer> {
    boolean existsByDbid(int dbid);
    boolean existsByName(String name);
    JpaTypeModel findByDbid(int dbid);
    JpaTypeModel findByName(String name);
    JpaTypeModel findFirstByNameStartingWith(String begin);
    List<JpaTypeModel> deleteByDbid(Integer dbid);
}
