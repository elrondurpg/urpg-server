package com.pokemonurpg.service.pokemon;

import com.pokemonurpg.dto.species.response.TypeMatchupDto;
import com.pokemonurpg.object.pokemon.Species;
import com.pokemonurpg.object.pokemon.Type;
import com.pokemonurpg.object.pokemon.TypeMatchup;
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
        return findTypeMatchupsBySpeciesTypes(species.getType1(), species.getType2());
    }

    public List<TypeMatchupDto> findTypeMatchupsBySpeciesTypes(String type1Name, String type2Name) {
        Type type1 = typeService.findByName(type1Name);
        Type type2 = typeService.findByName(type2Name);
        return findTypeMatchupsBySpeciesTypes(type1, type2);
    }

    public List<TypeMatchupDto> findTypeMatchupsBySpeciesTypes(Type type1, Type type2) {
        List<TypeMatchupDto> matchups = new ArrayList<>();

        List<Type> types = typeService.findAllFull();
        int type1Dbid = type1.getDbid();
        for (Type type : types) {
            if (type.getDbid() != -1) {
                TypeMatchup matchup = typeMatchupRepository.findByIdAttackTypeDbidAndIdDefendTypeDbid(type.getDbid(), type1Dbid);
                matchups.add(new TypeMatchupDto(type.getName(), matchup.getMultiplier()));
            }
        }

        if (type2 != null && type2.getDbid() != -1) {
            int type2Dbid = type2.getDbid();
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
