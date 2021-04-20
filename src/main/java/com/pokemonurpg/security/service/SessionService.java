package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SessionService {
    @Resource
    private RequestHeaderService requestHeaderService;

    @Resource
    private AuthenticationService authenticationService;

    private Member authenticatedMember = null;
    private String accessToken = null;

    public void createSession() {
        String header = requestHeaderService.findByName("authorization");
        if (header != null && header.toLowerCase().startsWith("basic")) {
            String base64Credentials = header.substring("basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);
            if (values.length == 2 && !values[0].isEmpty() && !values[1].isEmpty()) {
                Member member = authenticationService.authenticate(values[0], values[1]);
                if (member != null) {
                    setAuthenticatedMember(member);
                    setAccessToken(values[1]);
                }
            }
        }
    }

    public Member getAuthenticatedMember() {
        return authenticatedMember;
    }

    private void setAuthenticatedMember(Member authenticatedMember) {
        this.authenticatedMember = authenticatedMember;
    }

    public String getAccessToken() {
        return accessToken;
    }

    private void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
