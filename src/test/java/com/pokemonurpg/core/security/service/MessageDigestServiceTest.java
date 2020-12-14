package com.pokemonurpg.core.security.service;

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