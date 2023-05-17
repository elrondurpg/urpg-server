package com.pokemonurpg.service;

import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnedExtraMoveService {
    private AttackRepository attackRepository;
    private OwnedExtraMoveRepository ownedExtraMoveRepository;
    private LogService logService;

    @Autowired
    public OwnedExtraMoveService(AttackRepository attackRepository, OwnedExtraMoveRepository ownedExtraMoveRepository, LogService logService) {
        this.attackRepository = attackRepository;
        this.ownedExtraMoveRepository = ownedExtraMoveRepository;
        this.logService = logService;
    }

    public void add(OwnedPokemon pokemon, String attackName) {
        Attack attack = attackRepository.findByName(attackName);
        if (pokemon != null && attack != null) {
            int pokemonDbid = pokemon.getDbid();
            int attackDbid = attack.getDbid();
            OwnedExtraMove existingRecord = ownedExtraMoveRepository.findByIdPokemonDbidAndIdAttackDbid(pokemonDbid, attackDbid);
            if (existingRecord == null) {
                OwnedExtraMove extraMove = new OwnedExtraMove(pokemonDbid, attackDbid);
                ownedExtraMoveRepository.save(extraMove);
                String msg = pokemon.getNickname() != null ? pokemon.getNickname() + " the " : "";
                msg += pokemon.getSpecies().getDisplayName() + " learned " + attack.getName();
                logService.log(pokemon.getTrainer(), msg);
            }
        }
    }
}
