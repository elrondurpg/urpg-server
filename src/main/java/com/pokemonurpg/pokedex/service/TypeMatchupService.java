package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.pokedex.output.TypeMatchupDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.pokedex.models.TypeMatchup;
import com.pokemonurpg.pokedex.repository.TypeMatchupRepository;
import com.pokemonurpg.species.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeMatchupService {

    @Resource
    private TypeMatchupRepository typeMatchupRepository;

    @Resource
    private TypeService typeService;

    public List<TypeMatchupDto> findBySpecies(Species species) {
        return findTypeMatchupsByTypes(species.getType1(), species.getType2());
    }

    public List<TypeMatchupDto> findTypeMatchupsByTypes(Type type1, Type type2) {
        return typeService.findAll().stream()
            .filter(type -> type.getDbid() != -1)
            .map(attackingType -> {
                TypeMatchup typeMatchup1 = findByTypes(attackingType, type1);
                TypeMatchup typeMatchup2 = findByTypes(attackingType, type2);
                double multiplier = typeMatchup1.getMultiplier();
                multiplier *= typeMatchup2 == null ? 1 : typeMatchup2.getMultiplier();
                return new TypeMatchupDto(attackingType.getName(), multiplier);
            })
            .collect(Collectors.toList());
    }

    public TypeMatchup findByTypes(Type attackingType, Type defendingType) {
        return typeMatchupRepository.findByAttackTypeAndDefendType(attackingType, defendingType);
    }
}
