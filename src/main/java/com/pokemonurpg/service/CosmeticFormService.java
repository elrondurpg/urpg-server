package com.pokemonurpg.service;

import com.pokemonurpg.dto.CosmeticFormDto;
import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.repository.CosmeticFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CosmeticFormService
{
    private CosmeticFormRepository cosmeticFormRepository;

    @Autowired
    public CosmeticFormService(CosmeticFormRepository cosmeticFormRepository) {
        this.cosmeticFormRepository = cosmeticFormRepository;
    }


    public List<CosmeticFormDto> findBySpeciesDbid(int dbid) {
        List<CosmeticForm> list = cosmeticFormRepository.findByIdSpeciesDbid(dbid);
        List<CosmeticFormDto> responseList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (CosmeticForm form : list) {
                CosmeticFormDto cosmeticFormDto = new CosmeticFormDto(form);
                responseList.add(cosmeticFormDto);
            }
            return responseList;
        }
        else return Collections.emptyList();
    }
}
