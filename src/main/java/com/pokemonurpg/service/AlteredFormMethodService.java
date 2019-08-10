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
        Optional<AlteredFormMethod> method = alteredFormMethodRepository.findByDexno(dexno);
        if (method.isPresent())
            return method.get().getMethod();
        else return null;
    }

    public void create(int dexno, String method) {
        AlteredFormMethod alteredFormMethod = new AlteredFormMethod(dexno, method);
        alteredFormMethodRepository.save(alteredFormMethod);
    }

/*    public void save(AlteredFormMethod alteredFormMethod) {
        alteredFormMethodRepository.save(alteredFormMethod);
    }

    public void delete(AlteredFormMethod alteredFormMethod) {
        alteredFormMethodRepository.delete(alteredFormMethod);
    }
*/
}
