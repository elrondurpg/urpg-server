package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthorizationCredentialsService {
    @Resource
    private RequestHeaderService requestHeaderService;

    public String[] getCredentials() {
        String header = requestHeaderService.findByName("authorization");
        if (header != null && header.toLowerCase().startsWith("basic")) {
            String base64Credentials = header.substring("basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);
            if (values.length == 2 && !values[0].isEmpty() && !values[1].isEmpty()) {
                return values;
            }
        }
        return null;
    }

}
