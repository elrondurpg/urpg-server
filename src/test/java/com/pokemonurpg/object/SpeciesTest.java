package com.pokemonurpg.object;

import factory.SpeciesTestFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpeciesTest {

    @Test
    public void createSpeciesTest() {
        Species pokemon = SpeciesTestFactory.createSpecies("SpeciesTest");
        Assert.assertEquals((Integer) 1, pokemon.getDbid());
        Assert.assertEquals("SpeciesTest", pokemon.getName());
        Assert.assertEquals((Integer) 1, pokemon.getType1Dbid());
        Assert.assertEquals((Integer) 1, pokemon.getType2Dbid());
        Assert.assertEquals("TestClass", pokemon.getClassification());
        Assert.assertEquals((Integer) 300, pokemon.getHp());
        Assert.assertEquals((Integer) 123, pokemon.getAttack());
        Assert.assertEquals((Integer) 321, pokemon.getDefense());
        Assert.assertEquals((Integer) 456, pokemon.getSpecialAttack());
        Assert.assertEquals((Integer) 321, pokemon.getSpecialDefense());
        Assert.assertEquals((Integer) 134, pokemon.getSpeed());
        Assert.assertEquals((Double) 13.0, pokemon.getHeight());
        Assert.assertEquals((Double) 10.0, pokemon.getWeight());
        Assert.assertEquals((Boolean) true, pokemon.getMaleAllowed());
        Assert.assertEquals((Boolean) false, pokemon.getFemaleAllowed());
        Assert.assertEquals("10000", pokemon.getPokemart());
        Assert.assertEquals((Integer) 1, pokemon.getStoryRank());
        Assert.assertEquals((Integer) 2, pokemon.getArtRank());
        Assert.assertEquals((Integer) 3, pokemon.getParkLocation());
        Assert.assertEquals((Integer) 4, pokemon.getParkRank());
        Assert.assertEquals("40000", pokemon.getContestCredits());
        Assert.assertEquals("Pikachu-Display", pokemon.getDisplayName());
        Assert.assertEquals("Pikachu-Form", pokemon.getFormName());
    }

}