package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.entities.v1.TypeMatchup;
import com.pokemonurpg.infrastructure.v1.data.jpa.TypeMatchupRepository;
import com.pokemonurpg.configuration.v1.types.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeMatchupService {

    @Resource
    private TypeMatchupRepository typeMatchupRepository;

    @Resource
    private TypeService typeService;

    public List<TypeMatchupResponse> findBySpecies(Pokemon pokemon) {
        return findTypeMatchupsByTypes(pokemon.getType1(), pokemon.getType2());
    }

    public List<TypeMatchupResponse> findTypeMatchupsByTypes(Type type1, Type type2) {
        return typeService.findAll().stream()
            .filter(type -> type.getDbid() != -1)
            .map(attackingType -> {
                TypeMatchup typeMatchup1 = findByTypes(attackingType, type1);
                TypeMatchup typeMatchup2 = findByTypes(attackingType, type2);
                double multiplier = typeMatchup1.getMultiplier();
                multiplier *= typeMatchup2 == null ? 1 : typeMatchup2.getMultiplier();
                return new TypeMatchupResponse(attackingType, multiplier);
            })
            .collect(Collectors.toList());
    }

    public TypeMatchup findByTypes(Type attackingType, Type defendingType) {
        return typeMatchupRepository.findByAttackTypeAndDefendType(attackingType, defendingType);
    }
}
