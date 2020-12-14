package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Integer> {
    Gym findByDbid(int dbid);
    Gym findFirstByNameAndActiveIsTrue(String name);
    Gym findFirstByNameStartingWithAndActiveIsTrue(String name);
}
