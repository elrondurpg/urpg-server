package com.pokemonurpg.configuration.v1.create.member.common;

import static com.pokemonurpg.configuration.v1.create.member.common.CreateMemberConstants.*;

import javax.annotation.Resource;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepository;
import com.pokemonurpg.configuration.v1.update.member.common.UpdateMemberAccessTokensHandler;
import com.pokemonurpg.member.repository.KnownNameClaimRepository;
import com.pokemonurpg.member.repository.RoleRepository;

public abstract class CreateMemberHandler<Request extends CreateMemberRequest, Response extends CreateMemberResponse> {

    @Resource
    protected MemberRepository memberRepository;

    @Resource
    protected RoleRepository roleRepository;

    @Resource
    protected UpdateMemberAccessTokensHandler updateMemberAccessTokensHandler;

    @Resource
    protected KnownNameClaimRepository knownNameClaimRepository;

    public Response handle(Request request) {
        handleDiscordIdDuplication(request.getDiscordId());
        handleUsernameDuplication(request);
        handleKnownNameClaimDuplication(request);
        Member member = createMember(request);
        memberRepository.save(member);
        return createResponse(member, request);
    }

    protected void handleDiscordIdDuplication(String discordId) {
        if (!isDiscordIdUnique(discordId)) {
            throw new IllegalStateException(NON_UNIQUE_ID_ERROR);
        }
    }

    protected boolean isDiscordIdUnique(String discordId) {
        return memberRepository.findByDiscordId(discordId) == null;
    }

    protected void handleUsernameDuplication(Request request) {
        if (!isUsernameUnique(request.getName())) {
            throw new IllegalStateException(NON_UNIQUE_USERNAME_ERROR);
        }
    }

    protected boolean isUsernameUnique(String name) {
        return memberRepository.findByName(name) == null;
    }

    protected void handleKnownNameClaimDuplication(Request request) {
        if (isUsernameBeingClaimed(request.getName())) {
            throw new IllegalStateException(USERNAME_BEING_CLAIMED_ERROR);
        }
    }

    protected boolean isUsernameBeingClaimed(String name) {
        return knownNameClaimRepository.findByName(name) != null;
    }

    protected Member createMember(Request request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setDiscordId(request.getDiscordId());
        member.setBot(false);
        member.setDefaultValues();
        member.addRole(roleRepository.findByName("Member"));
        updateMemberAccessTokensHandler.updateLoginInformation(member, request.getAccessToken());
        return member;
    }

    protected abstract Response createResponse(Member member, Request request);
}
