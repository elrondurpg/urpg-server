package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.MegaEvolutionDto;
import com.pokemonurpg.object.MegaEvolution;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.repository.MegaEvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MegaEvolutionService {

    private MegaEvolutionRepository megaEvolutionRepository;
    private SpeciesService speciesService;

    @Autowired
    public MegaEvolutionService(MegaEvolutionRepository megaEvolutionRepository, SpeciesService speciesService) {
        this.megaEvolutionRepository = megaEvolutionRepository;
        this.speciesService = speciesService;
    }

    public List<MegaEvolutionDto> findByOriginalDbid(int dbid) {
        List<MegaEvolutionDto> dtos = new ArrayList<>();

        List<MegaEvolution> megas = megaEvolutionRepository.findByIdOriginalDbid(dbid);
        if (megas != null) {
            for (MegaEvolution mega : megas) {
                Species species = speciesService.findByDbid(mega.getId().getMegaEvolutionDbid());
                dtos.add(new MegaEvolutionDto(species, mega.getMegaStone()));
            }
        }

        return dtos;
    }

}
