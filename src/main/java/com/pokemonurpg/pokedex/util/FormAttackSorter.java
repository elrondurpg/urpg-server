package com.pokemonurpg.pokedex.util;

import com.pokemonurpg.pokedex.output.AlteredFormDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormAttackSorter {
    public void run(List<AlteredFormDto> alteredForms) {
        Set<String> allAttacks = new HashSet<>();

        alteredForms.forEach(alteredForm -> {
            alteredForm.getSpecies().getMappedSpeciesAttacks().forEach(speciesAttack -> {
                String attackName = speciesAttack.getAttack().getName();
                alteredForm.addMappedAttack(attackName, speciesAttack.getMethod());
                allAttacks.add(attackName);
            });
        });

        allAttacks.forEach(attackName -> {
            List<String> methods = alteredForms.stream()
                .map(alteredForm -> alteredForm.getAttackMethod(attackName))
                .collect(Collectors.toList());
            if (Collections.frequency(methods, methods.get(0)) == alteredForms.size()) {
                alteredForms.forEach(alteredForm -> alteredForm.removeAttack(attackName));
            }
        });
    }
}
