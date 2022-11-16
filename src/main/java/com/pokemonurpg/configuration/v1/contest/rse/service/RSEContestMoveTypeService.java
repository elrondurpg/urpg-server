package com.pokemonurpg.configuration.v1.contest.service;

import com.pokemonurpg.configuration.v1.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.configuration.v1.contest.models.RSEContestMoveType;
import com.pokemonurpg.configuration.v1.contest.repository.RSEContestMoveTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RSEContestMoveTypeService implements NamedObjectService<RSEContestMoveType> {

    @Resource
    private RSEContestMoveTypeRepository rseContestMoveTypeRepository;

    public List<String> findAllNames() {
        return rseContestMoveTypeRepository.findAllNames();
    }

    public RSEContestMoveType findByDbid(int dbid) {
        return rseContestMoveTypeRepository.findByDbid(dbid);
    }

    public RSEContestMoveType findByName(String name) {
        RSEContestMoveType rseContestMoveType = findByNameExact(name);
        if (rseContestMoveType == null && name != null) {
            return rseContestMoveTypeRepository.findFirstByNameStartingWith(name);
        }
        else return rseContestMoveType;
    }

    @Override
    public RSEContestMoveType findByNameExact(String name) {
        return rseContestMoveTypeRepository.findByName(name);
    }

    public RSEContestMoveType create(ContestMoveTypeInputDto input) {
        RSEContestMoveType rseContestMoveType = new RSEContestMoveType(input);
        rseContestMoveTypeRepository.save(rseContestMoveType);
        return rseContestMoveType;
    }

    public RSEContestMoveType update(ContestMoveTypeInputDto input, int dbid) {
        RSEContestMoveType rseContestMoveType = rseContestMoveTypeRepository.findByDbid(dbid);
        if (rseContestMoveType != null) {
            rseContestMoveType.update(input);
            rseContestMoveTypeRepository.save(rseContestMoveType);
        }
        return rseContestMoveType;
    }
}
