package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownEliteFourMember;
import com.pokemonurpg.gym.input.KnownEliteFourMemberInputDto;
import com.pokemonurpg.gym.repository.KnownEliteFourMemberRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Member;
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

    public KnownEliteFourMember findByDbid(int dbid) {
        return knownEliteFourMemberRepository.findByDbid(dbid);
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

    public KnownEliteFourMember create(String name) {
        KnownEliteFourMember knownEliteFourMember = knownEliteFourMemberRepository.findByName(name);
        if (knownEliteFourMember == null) {
            knownEliteFourMember = new KnownEliteFourMember(name);
            knownEliteFourMemberRepository.save(knownEliteFourMember);
        }
        return knownEliteFourMember;
    }

    public KnownEliteFourMember create(KnownEliteFourMemberInputDto input) {
        KnownEliteFourMember eliteFourMember = new KnownEliteFourMember(input);
        knownEliteFourMemberRepository.save(eliteFourMember);
        return eliteFourMember;
    }

    public KnownEliteFourMember update(KnownEliteFourMemberInputDto input, int dbid) {
        KnownEliteFourMember eliteFourMember = knownEliteFourMemberRepository.findByDbid(dbid);
        if (eliteFourMember != null) {
            eliteFourMember.update(input);
            knownEliteFourMemberRepository.save(eliteFourMember);
        }
        return eliteFourMember;
    }

    public void rename(MemberInputDto input, Member member) {
        KnownEliteFourMember knownEliteFourMember = findByNameExact(member.getName());
        if (knownEliteFourMember != null) {
            knownEliteFourMember.setName(input.getName());
            knownEliteFourMemberRepository.save(knownEliteFourMember);
        }
    }
}
