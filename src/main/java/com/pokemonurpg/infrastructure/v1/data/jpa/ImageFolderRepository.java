package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ImageFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageFolderRepository extends JpaRepository<ImageFolder, Integer> {
    @Query("select a.name from ImageFolder a")
    List<String> findAllNames();
    ImageFolder findByName(String name);
    ImageFolder findByDbid(Integer dbid);
}
