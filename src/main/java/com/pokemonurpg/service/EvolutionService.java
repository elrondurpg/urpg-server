package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.input.EvolutionInputDto;
import com.pokemonurpg.dto.species.response.EvolutionFamilyMemberBriefDto;
import com.pokemonurpg.dto.species.response.EvolutionFamilyMemberDto;
import com.pokemonurpg.object.Evolution;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.repository.EvolutionRepository;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvolutionService {

    private EvolutionRepository evolutionRepository;
    private SpeciesRepository speciesRepository;

    @Autowired
    public EvolutionService(EvolutionRepository evolutionRepository, SpeciesRepository speciesRepository) {
        this.evolutionRepository = evolutionRepository;
        this.speciesRepository = speciesRepository;
    }

    public int getPreEvolutionDbid(int evolutionDbid) {
        Evolution evolution = evolutionRepository.findByIdEvolutionDbid(evolutionDbid);
        if (evolution != null) {
            int prevoDbid = evolution.getId().getPreEvolutionDbid();
            return prevoDbid;
        }
        else return -1;
    }

    public EvolutionFamilyMemberBriefDto findByEvolutionDbid(int evolutionDbid) {
        Evolution evolution = evolutionRepository.findByIdEvolutionDbid(evolutionDbid);
        if (evolution != null) {
            EvolutionFamilyMemberBriefDto dto = new EvolutionFamilyMemberBriefDto();

            Species evo = speciesRepository.findByDbid(evolution.getId().getPreEvolutionDbid());
            dto.setName(evo.getName());
            dto.setMethod(evolution.getMethod());

            return dto;
        }
        else return null;
    }

    public List<EvolutionFamilyMemberDto> findEvolutionsByPreEvolutionDbid(int preEvolutionDbid) {
        List<EvolutionFamilyMemberDto> dtos = new ArrayList<>();

        List<Evolution> evolutions = evolutionRepository.findByIdPreEvolutionDbid(preEvolutionDbid);
        if (evolutions != null) {
            for (Evolution evolution : evolutions) {
                Species evo = speciesRepository.findByDbid(evolution.getId().getEvolutionDbid());
                dtos.add(new EvolutionFamilyMemberDto(evo, evolution.getMethod()));
            }
        }

        return dtos;
    }

    public void create(int evoDbid, EvolutionInputDto input) {
        if (input != null && input.getName() != null) {
            Species prevo = speciesRepository.findByName(input.getName());
            Evolution evolution = new Evolution(evoDbid, prevo.getDbid(), input.getMethod());
            evolutionRepository.save(evolution);
        }
    }

    public void update(int evoDbid, EvolutionInputDto input) {
        if (input != null) {
            Evolution existingRecord = evolutionRepository.findByIdEvolutionDbid(evoDbid);
            if (existingRecord != null) {
                if (input.getMethod() != null) {
                    existingRecord.setMethod(input.getMethod());
                }
                if (input.getNumBattles() != null) {
                    existingRecord.setNumBattles(input.getNumBattles());
                }
                evolutionRepository.save(existingRecord);
            }
            else {
                create(evoDbid, input);
            }
        }
    }
}
