package com.pokemonurpg.configuration.v1.gym.knownchampion.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.configuration.v1.gym.knownchampion.input.KnownChampionInputDto;
import com.pokemonurpg.entities.v1.gym.KnownChampion;
import com.pokemonurpg.entities.v1.gym.KnownChampionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnownChampionService extends SimpleNamedConfigurationService<KnownChampion, KnownChampionInputDto> {

    @Autowired
    public KnownChampionService(KnownChampionRepository repo) {
        super(repo, KnownChampion.class);
    }

    public void createForNameIfUnique(String name) {
        KnownChampion model = repository.findByName(name);
        if (model == null) {
            createForName(name);
        }
    }

    private void createForName(String name) {
        KnownChampion model = new KnownChampion();
        model.setName(name);
        repository.save(model);
    }

    public void renameForMember(Member member, String newName) {
        KnownChampion model = findByNameExact(member.getName());
        setIfNotNull(newName, model::setName);
        repository.save(model);
    }
}
