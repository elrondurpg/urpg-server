package com.pokemonurpg.login.service;

import com.pokemonurpg.entities.Flag;
import com.pokemonurpg.configuration.v1.featureflags.FlagService;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.registration.v1.members.KnownNameClaimService;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.dto.RegistrationInputDto;
import com.pokemonurpg.login.dto.SessionDto;
import com.pokemonurpg.login.models.DiscordUserResponse;
import com.pokemonurpg.login.models.OAuthAccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class RegistrationService {

    @Resource
    private FlagService flagService;

    @Resource
    private MemberService memberService;

    @Resource
    private OAuthService oAuthService;

    @Resource
    private KnownNameClaimService knownNameClaimService;

    public void claimKnownName(RegistrationInputDto input) {
        Flag canRegisterMembers = flagService.findByName("Can_Register_Members");
        if (canRegisterMembers.getBooleanValue()) {
            OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
            if (accessTokenResponse != null && accessTokenResponse.isValid()) {
                DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
                if (discordUserResponse != null && discordUserResponse.isValid()) {
                    Member member = memberService.findByDiscordId(discordUserResponse.getId());
                    if (member == null) {
                        knownNameClaimService.create(discordUserResponse.getId(), input.getName());
                    }
                }
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while registering your account.");
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Registration not permitted at this time.");
        }
    }

    public SessionDto registerNew(RegistrationInputDto input) {
        Flag canRegisterMembers = flagService.findByName("Can_Register_Members");
        if (canRegisterMembers != null && canRegisterMembers.getBooleanValue()) {
        OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
        if (accessTokenResponse != null && accessTokenResponse.isValid()) {
            DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
            if (discordUserResponse != null && discordUserResponse.isValid()) {
                Member member = memberService.findByDiscordId(discordUserResponse.getId());
                if (member == null) {
                    member = memberService.registerNew(input, discordUserResponse.getId(), accessTokenResponse);
                    if (member != null) {
                        return new SessionDto(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
                    }
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while registering your account.");
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Registration not permitted at this time.");
        }
    }

    public SessionDto registerVet(RegistrationInputDto input) {
        Flag canRegisterMembers = flagService.findByName("Can_Register_Members");
        if (canRegisterMembers.getBooleanValue()) {
            OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
            if (accessTokenResponse != null && accessTokenResponse.isValid()) {
                DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
                if (discordUserResponse != null && discordUserResponse.isValid()) {
                    Member member = memberService.findByDiscordId(discordUserResponse.getId());
                    if (member == null) {
                        member = memberService.registerVet(input, discordUserResponse.getId(), accessTokenResponse);
                        if (member != null) {
                            return new SessionDto(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
                        }
                    }
                }
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while registering your account.");
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Registration not permitted at this time.");
        }
    }
}
