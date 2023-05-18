package com.pokemonurpg.registration.v1.members;

import com.pokemonurpg.entities.v1.FeatureFlag;
import com.pokemonurpg.configuration.v1.featureflags.FeatureFlagService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.login.v1.DiscordUserResponse;
import com.pokemonurpg.login.v1.OAuthAccessTokenResponse;
import com.pokemonurpg.login.v1.OAuthService;
import com.pokemonurpg.login.v1.Session;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class RegistrationService {

    @Resource
    private FeatureFlagService featureFlagService;

    @Resource
    private MemberService memberService;

    @Resource
    private OAuthService oAuthService;

    @Resource
    private KnownNameClaimService knownNameClaimService;

    public void claimKnownName(RegistrationRequest input) {
        FeatureFlag canRegisterMembers = featureFlagService.findByName("Can_Register_Members");
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

    public Session registerNew(RegistrationRequest input) {
        FeatureFlag canRegisterMembers = featureFlagService.findByName("Can_Register_Members");
        if (canRegisterMembers != null && canRegisterMembers.getBooleanValue()) {
        OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
        if (accessTokenResponse != null && accessTokenResponse.isValid()) {
            DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
            if (discordUserResponse != null && discordUserResponse.isValid()) {
                Member member = memberService.findByDiscordId(discordUserResponse.getId());
                if (member == null) {
                    member = memberService.registerNew(input, discordUserResponse.getId(), accessTokenResponse);
                    if (member != null) {
                        return new Session(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
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

    public Session registerVet(RegistrationRequest input) {
        FeatureFlag canRegisterMembers = featureFlagService.findByName("Can_Register_Members");
        if (canRegisterMembers.getBooleanValue()) {
            OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
            if (accessTokenResponse != null && accessTokenResponse.isValid()) {
                DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
                if (discordUserResponse != null && discordUserResponse.isValid()) {
                    Member member = memberService.findByDiscordId(discordUserResponse.getId());
                    if (member == null) {
                        member = memberService.registerVet(input, discordUserResponse.getId(), accessTokenResponse);
                        if (member != null) {
                            return new Session(member.getName(), member.getDiscordId(), accessTokenResponse.getAccessToken());
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
