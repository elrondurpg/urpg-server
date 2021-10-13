package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.repository.DPPContestMoveTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DPPContestMoveTypeService implements NamedObjectService<DPPContestMoveType> {

    @Resource
    private DPPContestMoveTypeRepository dppContestMoveTypeRepository;

    public List<String> findAllNames() {
        return dppContestMoveTypeRepository.findAllNames();
    }

    public DPPContestMoveType findByDbid(int dbid) {
        return dppContestMoveTypeRepository.findByDbid(dbid);
    }

    public DPPContestMoveType findByName(String name) {
        DPPContestMoveType dppContestMoveType = findByNameExact(name);
        if (dppContestMoveType == null && name != null) {
            return dppContestMoveTypeRepository.findFirstByNameStartingWith(name);
        }
        else return dppContestMoveType;
    }

    @Override
    public DPPContestMoveType findByNameExact(String name) {
        return dppContestMoveTypeRepository.findByName(name);
    }

    public DPPContestMoveType create(ContestMoveTypeInputDto input) {
        DPPContestMoveType dppContestMoveType = new DPPContestMoveType(input);
        dppContestMoveTypeRepository.save(dppContestMoveType);
        return dppContestMoveType;
    }

    public DPPContestMoveType update(ContestMoveTypeInputDto input, int dbid) {
        DPPContestMoveType dppContestMoveType = dppContestMoveTypeRepository.findByDbid(dbid);
        if (dppContestMoveType != null) {
            dppContestMoveType.update(input);
            dppContestMoveTypeRepository.save(dppContestMoveType);
        }
        return dppContestMoveType;
    }
}
