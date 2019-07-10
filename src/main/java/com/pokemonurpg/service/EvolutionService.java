package com.pokemonurpg.service;

import com.pokemonurpg.object.Evolution;
import com.pokemonurpg.repository.EvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvolutionService {
    private EvolutionRepository evolutionRepository;

    @Autowired
    public EvolutionService(EvolutionRepository evolutionRepository) {
        this.evolutionRepository = evolutionRepository;
    }

    public List<Evolution> findAll(Example<Evolution> evolution) {
        return evolutionRepository.findAll(evolution);
    }
}
