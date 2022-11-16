package com.pokemonurpg.configuration.v1.contest.service;

import com.pokemonurpg.configuration.v1.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.configuration.v1.contest.models.ORASContestMoveType;
import com.pokemonurpg.configuration.v1.contest.repository.ORASContestMoveTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ORASContestMoveTypeService implements NamedObjectService<ORASContestMoveType> {

    @Resource
    private ORASContestMoveTypeRepository orasContestMoveTypeRepository;

    public List<String> findAllNames() {
        return orasContestMoveTypeRepository.findAllNames();
    }

    public ORASContestMoveType findByDbid(int dbid) {
        return orasContestMoveTypeRepository.findByDbid(dbid);
    }

    public ORASContestMoveType findByName(String name) {
        ORASContestMoveType orasContestMoveType = findByNameExact(name);
        if (orasContestMoveType == null && name != null) {
            return orasContestMoveTypeRepository.findFirstByNameStartingWith(name);
        }
        else return orasContestMoveType;
    }

    @Override
    public ORASContestMoveType findByNameExact(String name) {
        return orasContestMoveTypeRepository.findByName(name);
    }

    public ORASContestMoveType create(ContestMoveTypeInputDto input) {
        ORASContestMoveType orasContestMoveType = new ORASContestMoveType(input);
        orasContestMoveTypeRepository.save(orasContestMoveType);
        return orasContestMoveType;
    }

    public ORASContestMoveType update(ContestMoveTypeInputDto input, int dbid) {
        ORASContestMoveType orasContestMoveType = orasContestMoveTypeRepository.findByDbid(dbid);
        if (orasContestMoveType != null) {
            orasContestMoveType.update(input);
            orasContestMoveTypeRepository.save(orasContestMoveType);
        }
        return orasContestMoveType;
    }
}
