package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.contest.repository.RSEContestMoveTypeRepository;
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
        RSEContestMoveType rseContestMoveType = rseContestMoveTypeRepository.findByName(name);
        if (rseContestMoveType == null && name != null) {
            return rseContestMoveTypeRepository.findFirstByNameStartingWith(name);
        }
        else return rseContestMoveType;
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
