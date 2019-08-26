package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.input.MegaEvolutionInputDto;
import com.pokemonurpg.dto.species.response.MegaEvolutionDto;
import com.pokemonurpg.dto.species.response.SpeciesAbilityDto;
import com.pokemonurpg.object.Ability;
import com.pokemonurpg.object.MegaEvolution;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAbility;
import com.pokemonurpg.repository.MegaEvolutionRepository;
import com.pokemonurpg.repository.SpeciesAbilityRepository;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MegaEvolutionService {

    private MegaEvolutionRepository megaEvolutionRepository;
    private SpeciesRepository speciesRepository;
    private SpeciesAbilityService speciesAbilityService;

    @Autowired
    public MegaEvolutionService(MegaEvolutionRepository megaEvolutionRepository, SpeciesRepository speciesRepository, SpeciesAbilityService speciesAbilityService) {
        this.megaEvolutionRepository = megaEvolutionRepository;
        this.speciesRepository = speciesRepository;
        this.speciesAbilityService = speciesAbilityService;
    }

    public boolean isMegaEvolution(int dbid) {
        MegaEvolution mega = megaEvolutionRepository.findByIdMegaEvolutionDbid(dbid);
        return mega != null;
    }

    public List<MegaEvolutionDto> findByOriginalDbid(int dbid) {
        List<MegaEvolutionDto> dtos = new ArrayList<>();

        List<MegaEvolution> megas = megaEvolutionRepository.findByIdOriginalDbid(dbid);
        if (megas != null) {
            for (MegaEvolution mega : megas) {
                Species species = speciesRepository.findByDbid(mega.getId().getMegaEvolutionDbid());
                MegaEvolutionDto dto = new MegaEvolutionDto(species, mega.getMegaStone());
                List<SpeciesAbilityDto> abilityList = speciesAbilityService.findBySpeciesDbid(dbid);
                if (abilityList != null) {
                    dto.setAbility(abilityList.get(0));
                }
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public void create(int megaEvolutionDbid, MegaEvolutionInputDto input) {
        if (input != null) {
            Species species = speciesRepository.findByName(input.getName());
            MegaEvolution mega = new MegaEvolution(megaEvolutionDbid, species.getDbid(), input.getMegaStone());
            megaEvolutionRepository.save(mega);
        }
    }

    public void update(int megaEvolutionDbid, MegaEvolutionInputDto input) {
        if (input != null) {
            MegaEvolution existingRecord = megaEvolutionRepository.findByIdMegaEvolutionDbid(megaEvolutionDbid);
            if (existingRecord != null) {
                if (input.getMegaStone() != null) {
                    existingRecord.setMegaStone(input.getMegaStone());
                    megaEvolutionRepository.save(existingRecord);
                }
            }
            else {
                create(megaEvolutionDbid, input);
            }
        }
    }

}
