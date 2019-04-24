package com.pokemonurpg.service;

import com.pokemonurpg.object.Species;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpeciesService {

    public List<Species> findByName(String name) {
        List<Species> result = new ArrayList<>();
        result.add(new Species());
        return result;
    }
}
