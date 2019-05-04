package com.pokemonurpg.service;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {
    private SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public List<Species> findAll() {
        return speciesRepository.findAll();
    }

    public Optional<Species> findByName(String name) {
        return speciesRepository.findByName(name);
    }

    public List<Species> findByNameStartingWith(String name) {
        return speciesRepository.findByNameStartingWith(name);
    }

    public void save(Species species) {
        speciesRepository.save(species);
    }

}
