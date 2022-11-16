package com.pokemonurpg.configuration.v1.contest.rank.service;

import com.pokemonurpg.configuration.v1.contest.rank.model.ContestRank;
import com.pokemonurpg.configuration.v1.contest.rank.input.ContestRankInputDto;
import com.pokemonurpg.configuration.v1.contest.rank.repository.ContestRankRepository;
import com.pokemonurpg.core.service.NamedObjectService;
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
