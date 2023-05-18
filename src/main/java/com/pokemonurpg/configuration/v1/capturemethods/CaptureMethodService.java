package com.pokemonurpg.configuration.v1.capturemethods;

import com.pokemonurpg.infrastructure.v1.data.jpa.CaptureMethodRepository;
import com.pokemonurpg.entities.v1.CaptureMethod;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CaptureMethodService implements NamedObjectService<CaptureMethod> {

    @Resource
    private CaptureMethodRepository captureMethodRepository;

    public List<String> findAllNames() {
        return captureMethodRepository.findAllNames();
    }

    public CaptureMethod findByDbid(int dbid) {
        return captureMethodRepository.findByDbid(dbid);
    }

    public CaptureMethod findByName(String name) {
        CaptureMethod captureMethod = findByNameExact(name);
        if (captureMethod == null && name != null) {
            return captureMethodRepository.findFirstByNameStartingWith(name);
        }
        else return captureMethod;
    }

    @Override
    public CaptureMethod findByNameExact(String name) {
        return captureMethodRepository.findByName(name);
    }

    public CaptureMethod create(CaptureMethodRequest input) {
        CaptureMethod captureMethod = new CaptureMethod(input);
        captureMethodRepository.save(captureMethod);
        return captureMethod;
    }

    public CaptureMethod update(CaptureMethodRequest input, int dbid) {
        CaptureMethod captureMethod = captureMethodRepository.findByDbid(dbid);
        if (captureMethod != null) {
            captureMethod.update(input);
            captureMethodRepository.save(captureMethod);
        }
        return captureMethod;
    }
}
