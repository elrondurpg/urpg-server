package com.pokemonurpg.stats.service;

import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.contest.service.ContestAttributeService;
import com.pokemonurpg.contest.service.ContestRankService;
import com.pokemonurpg.contest.service.ContestTypeService;
import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.EarnedRibbonRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EarnedRibbonService implements IndexedObjectService<EarnedRibbon> {

    @Resource
    private EarnedRibbonRepository earnedRibbonRepository;

    @Resource
    private ContestRankService contestRankService;

    @Resource
    private ContestAttributeService contestAttributeService;

    @Resource
    private ContestTypeService contestTypeService;

    public EarnedRibbon findByDbid(Integer dbid) {
        return earnedRibbonRepository.findByDbid(dbid);
    }

    public void update(EarnedRibbonInputDto input, OwnedPokemon pokemon) {
        Integer dbid = input.getDbid();
        if (dbid == null) {
            ContestRank rank = contestRankService.findByName(input.getRank());
            ContestAttribute attribute = contestAttributeService.findByName(input.getAttribute());
            ContestType contestType = contestTypeService.findByName(input.getContestType());
            EarnedRibbon ribbon = new EarnedRibbon(input, pokemon, rank, attribute, contestType);
            earnedRibbonRepository.save(ribbon);
        }
        else {
            EarnedRibbon ribbon = findByDbid(input.getDbid());
            if (input.getDelete()) {
                earnedRibbonRepository.delete(ribbon);
            }
            else {
                ribbon.update(input);
                earnedRibbonRepository.save(ribbon);
            }
        }
    }
}
