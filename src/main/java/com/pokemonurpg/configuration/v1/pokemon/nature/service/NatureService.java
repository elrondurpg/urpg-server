package com.pokemonurpg.configuration.v1.pokemon.nature.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.nature.input.NatureInputDto;
import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.nature.repository.NatureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NatureService extends SimpleNamedConfigurationService<Nature, NatureInputDto> {

    @Autowired
    public NatureService(NatureRepository repo) {
        super(repo);
    }

    @Override
    protected Nature createBase(NatureInputDto input) {
        Nature nature = new Nature();
        updateBase(nature, input);
        return nature;
    }

    @Override
    protected void updateBase(Nature nature, NatureInputDto input) {
        nature.setName(input.getName());
    }
}
