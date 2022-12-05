package com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.input.KnownEliteFourMemberInputDto;
import com.pokemonurpg.entities.v1.gym.KnownEliteFourMember;
import com.pokemonurpg.entities.v1.gym.KnownEliteFourMemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnownEliteFourMemberService extends SimpleNamedConfigurationService<KnownEliteFourMember, KnownEliteFourMemberInputDto> {

    @Autowired
    public KnownEliteFourMemberService(KnownEliteFourMemberRepository repo) {
        super(repo, KnownEliteFourMember.class);
    }

    public void renameForMember(Member member, String newName) {
        KnownEliteFourMember model = findByNameExact(member.getName());
        setIfNotNull(newName, model::setName);
        repository.save(model);
    }

    public void createForNameIfUnique(String name) {
        KnownEliteFourMember model = repository.findByName(name);
        if (model == null) {
            createForName(name);
        }
    }

    private void createForName(String name) {
        KnownEliteFourMember model = new KnownEliteFourMember();
        model.setName(name);
        repository.save(model);
    }
}
