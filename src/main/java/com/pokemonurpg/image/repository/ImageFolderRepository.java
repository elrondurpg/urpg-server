package com.pokemonurpg.image.repository;

import com.pokemonurpg.image.models.ImageFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageFolderRepository extends JpaRepository<ImageFolder, Integer> {
    @Query("select a.name from ImageFolder a")
    List<String> findAllNames();
    ImageFolder findByName(String name);
    ImageFolder findByDbid(Integer dbid);
}
