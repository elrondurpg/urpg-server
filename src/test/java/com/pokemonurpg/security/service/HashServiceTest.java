package com.pokemonurpg.security.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

    @Test
    public void unsuccessfulHash() throws NoSuchAlgorithmException {
        String cleartext = "CLEAR";
        when(messageDigestService.findByName(ALGORITHM)).thenThrow(NoSuchAlgorithmException.class);
        assertThrows(ResponseStatusException.class, () -> hashService.hash(cleartext));
    }

}