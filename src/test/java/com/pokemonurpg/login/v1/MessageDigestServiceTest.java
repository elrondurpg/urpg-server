package com.pokemonurpg.login.v1;

import com.pokemonurpg.login.v1.MessageDigestService;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class MessageDigestServiceTest {

    private MessageDigestService messageDigestService = new MessageDigestService();

    @Test
    public void getsSHA512() throws NoSuchAlgorithmException {
        assertNotNull(messageDigestService.findByName("SHA-512"));
    }

}