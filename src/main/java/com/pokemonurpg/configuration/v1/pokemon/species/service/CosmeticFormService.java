package com.pokemonurpg.configuration.v1.pokemon.species.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.CosmeticForm;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.CosmeticFormRepository;

import java.util.Set;

import javax.annotation.Resource;

@Service
public class CosmeticFormService
{
    @Resource
    private CosmeticFormRepository cosmeticFormRepository;

    public Set<CosmeticForm> findBySpeciesDbid(Integer speciesDbid) {
        return cosmeticFormRepository.findBySpeciesDbid(speciesDbid);
    }

    public void update(CosmeticFormInputDto input, Integer speciesDbid) {
        CosmeticForm form = cosmeticFormRepository.findByName(input.getName());
        if (form != null) {
            if (input.getDelete()) {
                cosmeticFormRepository.delete(form);
            }
            else {
                form.update(input);
                cosmeticFormRepository.save(form);
            }
        }
        else {
            CosmeticForm newForm = new CosmeticForm(input, speciesDbid);
            cosmeticFormRepository.save(newForm);
        }
    }
}
