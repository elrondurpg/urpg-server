package com.pokemonurpg.species.service;

import com.pokemonurpg.species.input.CosmeticFormInputDto;
import com.pokemonurpg.species.models.CosmeticForm;
import com.pokemonurpg.species.repository.CosmeticFormRepository;
import org.springframework.stereotype.Service;

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
