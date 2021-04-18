package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.DiscordUserResponse;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.core.security.service.AesEncryptionService;
import com.pokemonurpg.core.security.service.HashService;
import com.pokemonurpg.core.security.service.OAuthService;
import com.pokemonurpg.core.security.service.SessionService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.input.LoginInputDto;
import com.pokemonurpg.member.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@Service
public class AuthenticationService {

    @Resource
    private MemberService memberService;

    @Resource
    private HashService hashService;

    @Resource
    private SystemService systemService;

    @Resource
    private OAuthService oAuthService;

    @Resource
    private SessionService sessionService;

    @Resource
    private AesEncryptionService aesEncryptionService;

    public SessionDto login(LoginInputDto input) {
        OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(input.getCode());
        if (accessTokenResponse.isValid()) {
            DiscordUserResponse discordUserResponse = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
            if (discordUserResponse.isValid()) {
                Member member = memberService.findByDiscordId(discordUserResponse.getId());
                if (member != null) {
                    memberService.update(member, accessTokenResponse);
                    return sessionService.create(member, accessTokenResponse);
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while logging you in.");
    }

    public SessionDto basicLogin() {
        SessionDto response = new SessionDto();

        return response;
    }

    public SessionDto refresh(SessionDto input) {
        Member member = authenticate(input);
        if (member != null) {
            byte[] iv = member.getRefreshTokenIv();
            String encryptedRefreshToken = member.getRefreshToken();
            if (iv != null && encryptedRefreshToken != null) {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                SecretKey key = aesEncryptionService.getKeyFromAccessToken(input.getAccessToken(), member.getSalt());
                String refreshToken = aesEncryptionService.decrypt(encryptedRefreshToken, key, ivParameterSpec);
                OAuthAccessTokenResponse refreshTokenResponse = oAuthService.refreshAccessToken(refreshToken);
                if (refreshTokenResponse.isValid()) {
                    memberService.update(member, refreshTokenResponse);
                    return sessionService.create(member, refreshTokenResponse);
                }
            }
        }

        return null;
    }

    public Member authenticate(SessionDto session) {
        String providedId = session.getId();
        Member matchedMember = memberService.findByDiscordId(providedId);
        if (matchedMember != null) {
            String providedAccessToken = session.getAccessToken();
            if (hasCorrectAccessToken(matchedMember, providedAccessToken)) {
                if (!isSessionExpired(matchedMember.getSessionExpire())) {
                    return matchedMember;
                }
            }
        }
        return null;
    }

    private boolean hasCorrectAccessToken(Member member, String accessTokenToVerify) {
        String accessToken = member.getAccessToken();
        int salt = member.getSalt();

        if (accessToken == null || accessToken.isEmpty()) return false;

        try {
            String loginCheck = hashService.hash(accessTokenToVerify + salt);
            return loginCheck.equals(accessToken);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isSessionExpired(long expireTime) {
        return (systemService.currentTimeMillis() / 1000) >= expireTime - 60;
    }

}
