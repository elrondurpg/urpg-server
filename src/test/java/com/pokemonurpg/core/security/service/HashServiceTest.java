package com.pokemonurpg.core.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HashServiceTest {
    private final static String ALGORITHM = "SHA-512";

    @InjectMocks
    private HashService hashService;

    @Mock
    private MessageDigestService messageDigestService;

    @Test
    public void successfulHash() throws NoSuchAlgorithmException {
        String cleartext = "CLEAR";
        when(messageDigestService.findByName(ALGORITHM)).thenReturn(MessageDigest.getInstance(ALGORITHM));
        assertNotEquals(cleartext, hashService.hash(cleartext));
    }

    @Test(expected = ResponseStatusException.class)
    public void unsuccessfulHash() throws NoSuchAlgorithmException {
        String cleartext = "CLEAR";
        when(messageDigestService.findByName(ALGORITHM)).thenThrow(NoSuchAlgorithmException.class);
        hashService.hash(cleartext);
    }

}