package com.pokemonurpg.configuration.v1.contestgenerations;

import com.pokemonurpg.infrastructure.v1.data.jpa.ContestGenerationRepository;
import com.pokemonurpg.entities.v1.ContestGeneration;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContestGenerationService implements NamedObjectService<ContestGeneration> {

    @Resource
    private ContestGenerationRepository contestGenerationRepository;

    public List<String> findAllNames() {
        return contestGenerationRepository.findAllNames();
    }

    public ContestGeneration findByDbid(int dbid) {
        return contestGenerationRepository.findByDbid(dbid);
    }

    public ContestGeneration findByName(String name) {
        ContestGeneration contestGeneration = contestGenerationRepository.findByName(name);
        if (contestGeneration == null && name != null) {
            return contestGenerationRepository.findFirstByNameStartingWith(name);
        }
        else return contestGeneration;
    }

    @Override
    public ContestGeneration findByNameExact(String name) {
        return contestGenerationRepository.findByName(name);
    }

    public ContestGeneration create(ContestGenerationRequest input) {
        ContestGeneration contestGeneration = new ContestGeneration(input);
        contestGenerationRepository.save(contestGeneration);
        return contestGeneration;
    }

    public ContestGeneration update(ContestGenerationRequest input, int dbid) {
        ContestGeneration contestGeneration = contestGenerationRepository.findByDbid(dbid);
        if (contestGeneration != null) {
            contestGeneration.update(input);
            contestGenerationRepository.save(contestGeneration);
        }
        return contestGeneration;
    }
}
