package com.pokemonurpg.gym.service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.gym.input.KnownEliteFourMemberInputDto;
import com.pokemonurpg.gym.models.KnownEliteFourMember;
import com.pokemonurpg.gym.repository.KnownEliteFourMemberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnownEliteFourMemberService implements NamedObjectService<KnownEliteFourMember> {

    @Resource
    private KnownEliteFourMemberRepository knownEliteFourMemberRepository;

    public List<String> findAllNames() {
        return knownEliteFourMemberRepository.findAllNames();
    }

    public KnownEliteFourMember findByName(String name) {
        KnownEliteFourMember eliteFourMember = knownEliteFourMemberRepository.findByName(name);
        if (eliteFourMember == null && name != null) {
            return knownEliteFourMemberRepository.findFirstByNameStartingWith(name);
        }
        else return eliteFourMember;
    }

    public void update(KnownEliteFourMemberInputDto input) {
        KnownEliteFourMember eliteFourMember = knownEliteFourMemberRepository.findByName(input.getName());
        if (eliteFourMember != null && input.getDelete()) {
            knownEliteFourMemberRepository.delete(eliteFourMember);
        }
        else if (eliteFourMember == null) {
            eliteFourMember = new KnownEliteFourMember(input.getName());
            knownEliteFourMemberRepository.save(eliteFourMember);
        }
    }
}
