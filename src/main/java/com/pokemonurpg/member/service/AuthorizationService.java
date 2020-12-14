package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.annotation.Authenticated;
import com.pokemonurpg.core.security.models.DiscordUserResponse;
import com.pokemonurpg.core.security.service.HashService;
import com.pokemonurpg.core.security.service.OAuthService;
import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.models.*;
import com.pokemonurpg.member.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthorizationService
{

    @Resource
    private AuthenticationService authenticationService;

    public boolean isAuthorized(SessionDto session, String permission) {
        Member member = authenticationService.authenticate(session);
        if (member != null) {
            if (!member.getBanned()) {
                for (Role role : member.getRoles()) {
                    for (Permission perm : role.getPermissions()) {
                        if (perm.getName().equals(permission)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
