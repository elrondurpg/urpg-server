package com.pokemonurpg.service;

import com.pokemonurpg.dto.TypeMatchupDto;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.Type;
import com.pokemonurpg.object.TypeMatchup;
import com.pokemonurpg.repository.TypeMatchupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeMatchupService {

    private TypeMatchupRepository typeMatchupRepository;
    private TypeService typeService;

    @Autowired
    public TypeMatchupService (TypeMatchupRepository typeMatchupRepository, TypeService typeService) {
        this.typeMatchupRepository = typeMatchupRepository;
        this.typeService = typeService;
    }

    List<TypeMatchupDto> findTypeMatchupsBySpecies(Species species) {
        List<TypeMatchupDto> matchups = new ArrayList<>();

        List<Type> types = typeService.findAll();
        int type1Dbid = species.getType1().getDbid();
        for (Type type : types) {
            if (type.getDbid() != -1) {
                TypeMatchup matchup = typeMatchupRepository.findByIdAttackTypeDbidAndIdDefendTypeDbid(type.getDbid(), type1Dbid);
                matchups.add(new TypeMatchupDto(type.getName(), matchup.getMultiplier()));
            }
        }

        if (species.getType2() != null && species.getType2().getDbid() != -1) {
            int type2Dbid = species.getType2().getDbid();
            int index = 0;
            for (Type type : types) {
                if (type.getDbid() != -1) {
                    TypeMatchup matchup = typeMatchupRepository.findByIdAttackTypeDbidAndIdDefendTypeDbid(type.getDbid(), type2Dbid);
                    TypeMatchupDto dto = matchups.get(index);
                    dto.setMultiplier(dto.getMultiplier() * matchup.getMultiplier());
                    index++;
                }
            }
        }

        return matchups;
    }
}
