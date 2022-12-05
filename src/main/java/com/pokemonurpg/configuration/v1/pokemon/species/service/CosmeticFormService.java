package com.pokemonurpg.configuration.v1.pokemon.species.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.entities.v1.pokemon.CosmeticForm;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.CosmeticFormRepository;

import java.util.Set;

import javax.annotation.Resource;

@Service
public class CosmeticFormService
{
    @Resource
    private CosmeticFormRepository repository;

    public Set<CosmeticForm> findBySpeciesDbid(Integer speciesDbid) {
        return repository.findBySpeciesDbid(speciesDbid);
    }

    public void update(Species species, CosmeticFormInputDto input) {
        CosmeticForm form = repository.findByName(input.getName());
        if (form != null) {
            if (input.getDelete()) {
                repository.delete(form);
            }
            else {
                form.update(input);
                repository.save(form);
            }
        }
        else {
            CosmeticForm newForm = new CosmeticForm(input, species.getDbid());
            repository.save(newForm);
        }
    }

    public void delete(CosmeticForm record) {
        repository.delete(record);
    }
}
