package com.pokemonurpg.login.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class MessageDigestService {

    public MessageDigest findByName(String name) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(name);
    }

}
