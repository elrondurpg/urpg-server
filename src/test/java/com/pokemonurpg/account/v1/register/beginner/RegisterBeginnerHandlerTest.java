package com.pokemonurpg.account.v1.register.beginner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateBeginnerHandlerFake;
import com.pokemonurpg.security.service.OAuthService;
import com.pokemonurpg.security.service.OAuthServiceFake;

public class RegisterBeginnerHandlerTest {

    private RegisterBeginnerHandler handler;
    protected OAuthService oAuthService = new OAuthServiceFake();
    protected CreateBeginnerHandlerFake memberHandler = new CreateBeginnerHandlerFake();
    
    @BeforeEach
    public void setup() {
        handler = new RegisterBeginnerHandler(oAuthService, memberHandler);
    }

    @Test
    public void test_handle() {
        RegisterBeginnerRequestFake request = new RegisterBeginnerRequestFake();
        RegisterBeginnerResponse response = handler.handle(request);
        RegisterBeginnerResponseFake.assertMatchesDefaults(response);
    }
}
