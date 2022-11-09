package com.pokemonurpg.test;

public class RandomNumberGenerator {
    private static int MIN = 0;
    private static int MAX = 100000;

    public static int generate() {
        return (int)(Math.random()*(MAX-MIN+1)+MIN);  
    }

    public static double generateDouble() {
        return (Math.random()*(MAX-MIN+1)+MIN);  
    }
}
