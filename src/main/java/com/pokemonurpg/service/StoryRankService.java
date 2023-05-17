package com.pokemonurpg.service;

import com.pokemonurpg.object.StoryRank;
import com.pokemonurpg.repository.StoryRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryRankService {

    private StoryRankRepository storyRankRepository;

    @Autowired
    public StoryRankService(StoryRankRepository storyRankRepository) {
        this.storyRankRepository = storyRankRepository;
    }

    public List<Object> findAll() {
        return storyRankRepository.findAllNames();
    }

    public StoryRank findByDbid(int dbid) {
        return storyRankRepository.findByDbid(dbid);
    }

    public StoryRank findByName(String name) {
        return storyRankRepository.findByName(name);
    }

    public List<StoryRank> findByNameStartingWith(String name) {
        return storyRankRepository.findByNameStartingWith(name);
    }

    public void save(StoryRank storyRank) {
        storyRankRepository.save(storyRank);
    }

    public void delete(StoryRank storyRank) {
        storyRankRepository.delete(storyRank);
    }
}
