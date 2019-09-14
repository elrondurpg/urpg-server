package com.pokemonurpg.service;

import com.pokemonurpg.object.ArtRank;
import com.pokemonurpg.repository.ArtRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtRankService {

    private ArtRankRepository artRankRepository;

    @Autowired
    public ArtRankService(ArtRankRepository artRankRepository) {
        this.artRankRepository = artRankRepository;
    }

    public List<Object> findAll() {
        return artRankRepository.findAllNames();
    }

    public ArtRank findByDbid(int dbid) {
        return artRankRepository.findByDbid(dbid);
    }

    public ArtRank findByName(String name) {
        return artRankRepository.findByName(name);
    }

    public List<ArtRank> findByNameStartingWith(String name) {
        return artRankRepository.findByNameStartingWith(name);
    }

    public void save(ArtRank artRank) {
        artRankRepository.save(artRank);
    }

    public void delete(ArtRank artRank) {
        artRankRepository.delete(artRank);
    }
}
