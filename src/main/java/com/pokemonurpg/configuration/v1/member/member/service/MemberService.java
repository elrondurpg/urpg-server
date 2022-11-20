package com.pokemonurpg.configuration.v1.member.member.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.member.member.input.MemberInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends SimpleNamedConfigurationService<Member, MemberInputDto> {

    @Autowired
    public MemberService(MemberRepository repo) {
        super(repo, Member.class);
    }
}
