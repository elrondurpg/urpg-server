package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.response.CosmeticFormDto;
import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.repository.CosmeticFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void create(int speciesDbid, CosmeticFormDto input) {
        CosmeticForm form = new CosmeticForm(speciesDbid, input.getName(), input.getFormName(), input.getMethod());
        cosmeticFormRepository.save(form);
    }

    public void createAll(int speciesDbid, List<CosmeticFormDto> input) {
        if (input != null) {
            for (CosmeticFormDto dto : input) {
                create(speciesDbid, dto);
            }
        }
    }

    public void update(CosmeticForm existingRecord, CosmeticFormDto input) {
        if (input.getFormName() != null) {
            existingRecord.setFormName(input.getFormName());
        }
        if (input.getMethod() != null) {
            existingRecord.setMethod(input.getMethod());
        }
        cosmeticFormRepository.save(existingRecord);
    }

    public void updateAll(int speciesDbid, List<CosmeticFormDto> input) {
        if (input != null) {
            for (CosmeticFormDto record : input) {
                CosmeticForm existingRecord = cosmeticFormRepository.findByIdSpeciesDbidAndIdName(speciesDbid, record.getName());
                if (existingRecord != null) {
                    update(existingRecord, record);
                } else {
                    create(speciesDbid, record);
                }
            }
        }
    }
}
