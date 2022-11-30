package com.pokemonurpg.configuration.v1.create.member.veteran;

import static com.pokemonurpg.configuration.v1.create.member.common.CreateMemberConstants.*;

import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.claim.username.ClaimKnownNameHandler;
import com.pokemonurpg.account.v1.claim.username.ClaimKnownNameRequest;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberHandler;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberRequest;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberResponse;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

@Service
public class CreateVeteranHandler extends CreateMemberHandler<CreateMemberRequest, CreateMemberResponse> {

    @Resource
    private ClaimKnownNameHandler claimKnownNameHandler;

    @Override
    protected void handleUsernameDuplication(CreateMemberRequest request) {
        if (!isUsernameUnique(request.getName())) {
            claimKnownNameHandler.handle(createClaimKnownNameRequest(request));
            throw new IllegalStateException(CREATED_KNOWN_NAME_CLAIM_EXCEPTION_MESSAGE);
        }
    }

    private ClaimKnownNameRequest createClaimKnownNameRequest(CreateMemberRequest request) {
        return ClaimKnownNameRequest.builder()
            .discordId(request.getDiscordId())
            .name(request.getName())
            .build();
    }

    @Override
    protected CreateMemberResponse createResponse(Member member, CreateMemberRequest request) {
        return CreateMemberResponse.builder()
            .accessToken(request.getAccessToken().getAccessToken())
            .name(member.getName())
            .discordId(member.getDiscordId())
            .roles(member.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
            .build();
    }
}
