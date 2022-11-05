package com.pokemonurpg.configuration.v1.pokemon.nature.service;

import com.pokemonurpg.configuration.v1.pokemon.nature.input.NatureInputDto;
import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.nature.repository.NatureRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NatureService implements NamedObjectService<Nature> {

    @Resource
    private NatureRepository natureRepository;

    public List<String> findAllNames() {
        return natureRepository.findAllNames();
    }

    public Nature findByDbid(int dbid) {
        return natureRepository.findByDbid(dbid);
    }

    public Nature findByName(String name) {
        Nature nature = findByNameExact(name);
        if (nature == null && name != null) {
            return natureRepository.findFirstByNameStartingWith(name);
        }
        else return nature;
    }

    @Override
    public Nature findByNameExact(String name) {
        return natureRepository.findByName(name);
    }

    public Nature create(NatureInputDto input) {
        Nature nature = new Nature(input);
        natureRepository.save(nature);
        return nature;
    }

    public Nature update(NatureInputDto input, int dbid) {
        Nature nature = natureRepository.findByDbid(dbid);
        if (nature != null) {
            nature.update(input);
            natureRepository.save(nature);
        }
        return nature;
    }
}
