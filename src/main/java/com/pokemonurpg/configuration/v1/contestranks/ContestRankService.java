package com.pokemonurpg.configuration.v1.contestranks;

import com.pokemonurpg.infrastructure.v1.data.jpa.ContestRankRepository;
import com.pokemonurpg.entities.v1.ContestRank;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContestRankService implements NamedObjectService<ContestRank> {

    @Resource
    private ContestRankRepository contestRankRepository;

    public List<String> findAllNames() {
        return contestRankRepository.findAllNames();
    }

    public ContestRank findByDbid(int dbid) {
        return contestRankRepository.findByDbid(dbid);
    }

    public ContestRank findByName(String name) {
        ContestRank contestRank = contestRankRepository.findByName(name);
        if (contestRank == null && name != null) {
            return contestRankRepository.findFirstByNameStartingWith(name);
        }
        else return contestRank;
    }

    @Override
    public ContestRank findByNameExact(String name) {
        return contestRankRepository.findByName(name);
    }

    public ContestRank create(ContestRankInputDto input) {
        ContestRank contestRank = new ContestRank(input);
        contestRankRepository.save(contestRank);
        return contestRank;
    }

    public ContestRank update(ContestRankInputDto input, int dbid) {
        ContestRank contestRank = contestRankRepository.findByDbid(dbid);
        if (contestRank != null) {
            contestRank.update(input);
            contestRankRepository.save(contestRank);
        }
        return contestRank;
    }
}
