package com.pokemonurpg.configuration.v1.capturemethods;

import com.pokemonurpg.infrastructure.data.ObtainedRepository;
import com.pokemonurpg.entities.Obtained;
import com.pokemonurpg.lib.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ObtainedService implements NamedObjectService<Obtained> {

    @Resource
    private ObtainedRepository obtainedRepository;

    public List<String> findAllNames() {
        return obtainedRepository.findAllNames();
    }

    public Obtained findByDbid(int dbid) {
        return obtainedRepository.findByDbid(dbid);
    }

    public Obtained findByName(String name) {
        Obtained obtained = findByNameExact(name);
        if (obtained == null && name != null) {
            return obtainedRepository.findFirstByNameStartingWith(name);
        }
        else return obtained;
    }

    @Override
    public Obtained findByNameExact(String name) {
        return obtainedRepository.findByName(name);
    }

    public Obtained create(ObtainedInputDto input) {
        Obtained obtained = new Obtained(input);
        obtainedRepository.save(obtained);
        return obtained;
    }

    public Obtained update(ObtainedInputDto input, int dbid) {
        Obtained obtained = obtainedRepository.findByDbid(dbid);
        if (obtained != null) {
            obtained.update(input);
            obtainedRepository.save(obtained);
        }
        return obtained;
    }
}
