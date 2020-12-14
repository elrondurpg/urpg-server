package com.pokemonurpg.species.repository;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAttack;
import com.pokemonurpg.species.models.SpeciesAttackKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesAttackRepository  extends JpaRepository<SpeciesAttack, SpeciesAttackKey> {
    SpeciesAttack findBySpeciesAndAttack(Species species, Attack attack);
}
