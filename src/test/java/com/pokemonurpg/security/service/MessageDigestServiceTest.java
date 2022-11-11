package com.pokemonurpg.security.service;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDigestServiceTest {

    private MessageDigestService messageDigestService = new MessageDigestService();

    @Test
    public void getsSHA512() throws NoSuchAlgorithmException {
        assertNotNull(messageDigestService.findByName("SHA-512"));
    }

}