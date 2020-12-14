package com.pokemonurpg.creative.service;

import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.creative.input.StoryRankInputDto;
import com.pokemonurpg.creative.repository.StoryRankRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoryRankService implements NamedObjectService<StoryRank> {

    @Resource
    private StoryRankRepository storyRankRepository;

    public List<String> findAllNames() {
        return storyRankRepository.findAllNames();
    }

    public StoryRank findByDbid(int dbid) {
        return storyRankRepository.findByDbid(dbid);
    }

    public StoryRank findByName(String name) {
        StoryRank storyRank = storyRankRepository.findByName(name);
        if (storyRank == null && name != null) {
            return storyRankRepository.findFirstByNameStartingWith(name);
        }
        else return storyRank;
    }

    public StoryRank create(StoryRankInputDto input) {
        StoryRank storyRank = new StoryRank(input);
        storyRankRepository.save(storyRank);
        return storyRank;
    }

    public StoryRank update(StoryRankInputDto input, int dbid) {
        StoryRank storyRank = storyRankRepository.findByDbid(dbid);
        if (storyRank != null) {
            storyRank.update(input);
            storyRankRepository.save(storyRank);
        }
        return storyRank;
    }
}
