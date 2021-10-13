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
        KnownEliteFourMember eliteFourMember = findByNameExact(name);
        if (eliteFourMember == null && name != null) {
            return knownEliteFourMemberRepository.findFirstByNameStartingWith(name);
        }
        else return eliteFourMember;
    }

    @Override
    public KnownEliteFourMember findByNameExact(String name) {
        return knownEliteFourMemberRepository.findByName(name);
    }

    public void create(String name) {
        KnownEliteFourMember member = findByNameExact(name);
        if (member == null) {
            member = new KnownEliteFourMember();
            member.setName(name);
            knownEliteFourMemberRepository.save(member);
        }
    }

    public void update(String newName, String oldName) {
        if (newName != null && oldName != null) {
            KnownEliteFourMember eliteFourMember = knownEliteFourMemberRepository.findByName(oldName);
            if (eliteFourMember != null) {
                eliteFourMember.setName(newName);
                knownEliteFourMemberRepository.save(eliteFourMember);
            }
        }
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
