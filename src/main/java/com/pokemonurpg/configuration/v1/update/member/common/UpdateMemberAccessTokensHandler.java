package com.pokemonurpg.configuration.v1.update.member.common;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.HashService;

@Service
public class UpdateMemberAccessTokensHandler {

    @Resource
    protected HashService hashService;

    @Resource
    protected AesEncryptionService aesEncryptionService;

    @Resource
    protected SystemService systemService;

    public void updateLoginInformation(Member member, OAuthAccessTokenResponse accessTokenResponse) {
        member.setAccessToken(hashService.hash(accessTokenResponse.getAccessToken() + member.getSalt()));
        if (accessTokenResponse.getRefreshToken() != null) {
            updateRefreshToken(member, accessTokenResponse);
        }   
        member.setSessionExpire(Long.parseLong(accessTokenResponse.getExpiresIn()) + (systemService.currentTimeMillis() / 1000));
    }

    private void updateRefreshToken(Member member, OAuthAccessTokenResponse accessTokenResponse) {
        javax.crypto.spec.IvParameterSpec iv = aesEncryptionService.createIvParameterSpec();
        SecretKey key = aesEncryptionService.getKeyFromAccessToken(accessTokenResponse.getAccessToken(), member.getSalt());
        String encryptedRefreshToken = aesEncryptionService.encrypt(accessTokenResponse.getRefreshToken(), key, iv);
        member.setRefreshToken(encryptedRefreshToken);
        member.setRefreshTokenIv(iv.getIV());
    }
}
