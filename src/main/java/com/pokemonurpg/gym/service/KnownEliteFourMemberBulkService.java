package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.KnownEliteFourMemberBulkInputDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnownEliteFourMemberBulkService {

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    public List<String> update(KnownEliteFourMemberBulkInputDto input) {
        input.getEliteFourMembers().forEach(knownEliteFourMemberService::update);
        return knownEliteFourMemberService.findAllNames();
    }
}
