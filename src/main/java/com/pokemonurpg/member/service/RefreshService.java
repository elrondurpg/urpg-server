package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.core.security.service.AesEncryptionService;
import com.pokemonurpg.core.security.service.OAuthService;
import com.pokemonurpg.core.security.service.SessionService;
import com.pokemonurpg.member.models.Member;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.inject.Provider;

@Service
public class RefreshService {

    @Resource
    private MemberService memberService;

    @Resource
    private OAuthService oAuthService;

    @Resource
    private AesEncryptionService aesEncryptionService;

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    public SessionDto refresh() {
        SessionService sessionService = sessionServiceProvider.get();
        Member member = sessionService.getAuthenticatedMember();
        if (member != null) {
            byte[] iv = member.getRefreshTokenIv();
            String encryptedRefreshToken = member.getRefreshToken();
            if (iv != null && encryptedRefreshToken != null) {
                IvParameterSpec ivParameterSpec = aesEncryptionService.createIvParameterSpec(iv);
                SecretKey key = aesEncryptionService.getKeyFromAccessToken(sessionService.getAccessToken(), member.getSalt());
                String refreshToken = aesEncryptionService.decrypt(encryptedRefreshToken, key, ivParameterSpec);
                OAuthAccessTokenResponse refreshTokenResponse = oAuthService.refreshAccessToken(refreshToken);
                if (refreshTokenResponse != null && refreshTokenResponse.isValid()) {
                    memberService.update(member, refreshTokenResponse);
                    return new SessionDto(member.getUsername(), member.getDiscordId(), refreshTokenResponse.getAccessToken());
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while refreshing your session.");
    }
}
