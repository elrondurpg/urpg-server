package com.pokemonurpg.configuration.v1.storyranks;

import com.pokemonurpg.infrastructure.v1.data.jpa.StoryRankRepository;
import com.pokemonurpg.entities.v1.StoryRank;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
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
        StoryRank storyRank = findByNameExact(name);
        if (storyRank == null && name != null) {
            return storyRankRepository.findFirstByNameStartingWith(name);
        }
        else return storyRank;
    }

    @Override
    public StoryRank findByNameExact(String name) {
        return storyRankRepository.findByName(name);
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
