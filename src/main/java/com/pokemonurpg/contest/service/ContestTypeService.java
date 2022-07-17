package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestTypeInputDto;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.contest.repository.ContestTypeRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContestTypeService implements NamedObjectService<ContestType> {

    @Resource
    private ContestTypeRepository contestTypeRepository;

    public List<String> findAllNames() {
        return contestTypeRepository.findAllNames();
    }

    public ContestType findByDbid(int dbid) {
        return contestTypeRepository.findByDbid(dbid);
    }

    public ContestType findByName(String name) {
        ContestType contestType = contestTypeRepository.findByName(name);
        if (contestType == null && name != null) {
            return contestTypeRepository.findFirstByNameStartingWith(name);
        }
        else return contestType;
    }

    @Override
    public ContestType findByNameExact(String name) {
        return contestTypeRepository.findByName(name);
    }

    public ContestType create(ContestTypeInputDto input) {
        ContestType contestType = new ContestType(input);
        contestTypeRepository.save(contestType);
        return contestType;
    }

    public ContestType update(ContestTypeInputDto input, int dbid) {
        ContestType contestType = contestTypeRepository.findByDbid(dbid);
        if (contestType != null) {
            contestType.update(input);
            contestTypeRepository.save(contestType);
        }
        return contestType;
    }
}
