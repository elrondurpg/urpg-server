package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.KnownNameClaimService;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.security.dto.RegistrationInputDto;
import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.models.DiscordUserResponse;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.servlet.Registration;

@Service
public class RegistrationService {

    @Resource
    private MemberService memberService;

    @Resource
    private OAuthService oAuthService;

    @Resource
    private KnownNameClaimService knownNameClaimService;

    public void claimKnownName(RegistrationInputDto input) {
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

    public SessionDto registerNew(RegistrationInputDto input) {
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

    public SessionDto registerVet(RegistrationInputDto input) {
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
}
