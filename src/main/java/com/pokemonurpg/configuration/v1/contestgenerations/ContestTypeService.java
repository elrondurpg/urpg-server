package com.pokemonurpg.configuration.v1.contestgenerations;

import com.pokemonurpg.infrastructure.v1.data.jpa.ContestTypeRepository;
import com.pokemonurpg.entities.v1.ContestType;
import com.pokemonurpg.lib.v1.service.NamedObjectService;
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
