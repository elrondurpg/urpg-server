package com.pokemonurpg.test;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomStringGenerator {
    private static Random RAND = new Random();
    public static String generate() {
        byte[] array = new byte[7]; 
        RAND.nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
