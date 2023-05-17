package com.pokemonurpg.service;

import com.pokemonurpg.object.AlteredFormMethod;
import com.pokemonurpg.repository.AlteredFormMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlteredFormMethodService {

    private AlteredFormMethodRepository alteredFormMethodRepository;

    @Autowired
    public AlteredFormMethodService(AlteredFormMethodRepository alteredFormMethodRepository) {
        this.alteredFormMethodRepository = alteredFormMethodRepository;
    }

    /*public List<AlteredFormMethod> findAll() {
        return alteredFormMethodRepository.findAll();
    }*/

    public String findByDexno(Integer dexno) {
        AlteredFormMethod method = alteredFormMethodRepository.findByDexno(dexno);
        if (method != null)
            return method.getMethod();
        else return null;
    }

    public void create(int dexno, String method) {
        AlteredFormMethod alteredFormMethod = new AlteredFormMethod(dexno, method);
        alteredFormMethodRepository.save(alteredFormMethod);
    }

    public void update(int dexno, String method) {
        if (method != null) {
            AlteredFormMethod existingRecord = alteredFormMethodRepository.findByDexno(dexno);
            if (existingRecord != null) {
                existingRecord.setMethod(method);
                alteredFormMethodRepository.save(existingRecord);
            }
            else {
                create(dexno, method);
            }
        }
    }
}
