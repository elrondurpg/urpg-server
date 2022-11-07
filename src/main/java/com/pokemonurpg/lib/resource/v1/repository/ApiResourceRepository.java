package com.pokemonurpg.lib.resource.v1.repository;

import com.pokemonurpg.lib.resource.v1.model.ApiResource;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ApiResourceRepository extends JpaRepository<ApiResource, Integer> {
    ApiResource findByUrl(String url);
}
