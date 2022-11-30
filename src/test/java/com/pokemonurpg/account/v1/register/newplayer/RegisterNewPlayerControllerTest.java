package com.pokemonurpg.account.v1.register.newplayer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.account.v1.register.beginner.RegisterBeginnerController;
import com.pokemonurpg.account.v1.register.beginner.RegisterBeginnerHandler;

@ExtendWith(MockitoExtension.class)
public class RegisterNewPlayerControllerTest {

    @InjectMocks
    private RegisterBeginnerController controller;

    @Mock
    private RegisterBeginnerHandler handler;

    @Test
    void testRegisterNewPlayer() {
        
    }
}
