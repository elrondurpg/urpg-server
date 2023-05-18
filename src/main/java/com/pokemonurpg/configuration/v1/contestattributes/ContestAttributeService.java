package com.pokemonurpg.configuration.v1.contestattributes;

import com.pokemonurpg.infrastructure.v1.data.jpa.ContestAttributeRepository;
import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.lib.v1.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContestAttributeService implements NamedObjectService<ContestAttribute> {

    @Resource
    private ContestAttributeRepository contestAttributeRepository;

    public List<String> findAllNames() {
        return contestAttributeRepository.findAllNames();
    }

    public ContestAttribute findByDbid(int dbid) {
        return contestAttributeRepository.findByDbid(dbid);
    }

    public ContestAttribute findByName(String name) {
        ContestAttribute contestAttribute = contestAttributeRepository.findByName(name);
        if (contestAttribute == null && name != null) {
            return contestAttributeRepository.findFirstByNameStartingWith(name);
        }
        else return contestAttribute;
    }

    @Override
    public ContestAttribute findByNameExact(String name) {
        return contestAttributeRepository.findByName(name);
    }

    public ContestAttribute create(ContestAttributeInputDto input) {
        ContestAttribute contestAttribute = new ContestAttribute(input);
        contestAttributeRepository.save(contestAttribute);
        return contestAttribute;
    }

    public ContestAttribute update(ContestAttributeInputDto input, int dbid) {
        ContestAttribute contestAttribute = contestAttributeRepository.findByDbid(dbid);
        if (contestAttribute != null) {
            contestAttribute.update(input);
            contestAttributeRepository.save(contestAttribute);
        }
        return contestAttribute;
    }
}
