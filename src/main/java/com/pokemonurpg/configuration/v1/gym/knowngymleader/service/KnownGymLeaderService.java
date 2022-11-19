package com.pokemonurpg.configuration.v1.gym.knowngymleader.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.input.KnownGymLeaderInputDto;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.model.KnownGymLeader;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.repository.KnownGymLeaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnownGymLeaderService extends SimpleNamedConfigurationService<KnownGymLeader, KnownGymLeaderInputDto> {

    @Autowired
    public KnownGymLeaderService(KnownGymLeaderRepository repo) {
        super(repo, KnownGymLeader.class);
    }

    public void createForNameIfUnique(String name) {
        KnownGymLeader model = repository.findByName(name);
        if (model == null) {
            createForName(name);
        }
    }

    private void createForName(String name) {
        KnownGymLeader model = new KnownGymLeader();
        model.setName(name);
        repository.save(model);
    }

    public void renameForMember(Member member, String newName) {
        KnownGymLeader model = findByNameExact(member.getName());
        setIfNotNull(newName, model::setName);
        repository.save(model);
    }
}
