package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.infrastructure.data.AdvContestMoveTypeRepository;
import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.AdvContestMoveType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvContestMoveTypeService implements NamedObjectService<AdvContestMoveType> {

    @Resource
    private AdvContestMoveTypeRepository advContestMoveTypeRepository;

    public List<String> findAllNames() {
        return advContestMoveTypeRepository.findAllNames();
    }

    public AdvContestMoveType findByDbid(int dbid) {
        return advContestMoveTypeRepository.findByDbid(dbid);
    }

    public AdvContestMoveType findByName(String name) {
        AdvContestMoveType advContestMoveType = advContestMoveTypeRepository.findByName(name);
        if (advContestMoveType == null && name != null) {
            return advContestMoveTypeRepository.findFirstByNameStartingWith(name);
        }
        else return advContestMoveType;
    }

    @Override
    public AdvContestMoveType findByNameExact(String name) {
        return advContestMoveTypeRepository.findByName(name);
    }

    public AdvContestMoveType create(ContestMoveTypeInputDto input) {
        AdvContestMoveType advContestMoveType = new AdvContestMoveType(input);
        advContestMoveTypeRepository.save(advContestMoveType);
        return advContestMoveType;
    }

    public AdvContestMoveType update(ContestMoveTypeInputDto input, int dbid) {
        AdvContestMoveType advContestMoveType = advContestMoveTypeRepository.findByDbid(dbid);
        if (advContestMoveType != null) {
            advContestMoveType.update(input);
            advContestMoveTypeRepository.save(advContestMoveType);
        }
        return advContestMoveType;
    }
}
