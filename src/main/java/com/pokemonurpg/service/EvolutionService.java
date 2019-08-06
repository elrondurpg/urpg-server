package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.EvolutionFamilyMemberDto;
import com.pokemonurpg.object.Evolution;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.repository.EvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvolutionService {

    private EvolutionRepository evolutionRepository;

    private SpeciesService speciesService;

    @Autowired
    public EvolutionService(EvolutionRepository evolutionRepository, SpeciesService speciesService) {
        this.evolutionRepository = evolutionRepository;
        this.speciesService = speciesService;
    }

    public int getPreEvolutionDbid(int evolutionDbid) {
        Evolution evolution = evolutionRepository.findByIdEvolutionDbid(evolutionDbid);
        if (evolution != null) {
            int prevoDbid = evolution.getId().getPreEvolutionDbid();
            return prevoDbid;
        }
        else return -1;
    }

    public List<EvolutionFamilyMemberDto> findEvolutionsByPreEvolutionDbid(int preEvolutionDbid) {
        List<EvolutionFamilyMemberDto> dtos = new ArrayList<>();

        List<Evolution> evolutions = evolutionRepository.findByIdPreEvolutionDbid(preEvolutionDbid);
        if (evolutions != null) {
            for (Evolution evolution : evolutions) {
                Species evo = speciesService.findByDbid(evolution.getId().getEvolutionDbid());
                dtos.add(new EvolutionFamilyMemberDto(evo, evolution.getMethod()));
            }
        }

        return dtos;
    }
}
