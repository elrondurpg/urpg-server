package com.pokemonurpg.account.v1.register.veteran.internal;

import static com.pokemonurpg.account.v1.register.common.internal.CreateMemberConstants.CREATED_KNOWN_NAME_CLAIM_EXCEPTION_MESSAGE;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.common.internal.CreateMemberHandler;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberRequest;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberResponse;
import com.pokemonurpg.account.v1.register.common.internal.UpdateMemberAccessTokensHandler;
import com.pokemonurpg.entities.v1.member.KnownNameClaimRepository;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.member.MemberRepository;
import com.pokemonurpg.entities.v1.member.RoleRepository;

@Service
public class CreateVeteranHandler extends CreateMemberHandler<CreateMemberRequest, CreateMemberResponse> {

    private ClaimKnownNameHandler claimKnownNameHandler;

    @Autowired
    public CreateVeteranHandler(MemberRepository memberRepository, RoleRepository roleRepository,
            UpdateMemberAccessTokensHandler updateMemberAccessTokensHandler,
            KnownNameClaimRepository knownNameClaimRepository, ClaimKnownNameHandler claimKnownNameHandler) {
        super(memberRepository, roleRepository, updateMemberAccessTokensHandler, knownNameClaimRepository);
        this.claimKnownNameHandler = claimKnownNameHandler;
    }

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
