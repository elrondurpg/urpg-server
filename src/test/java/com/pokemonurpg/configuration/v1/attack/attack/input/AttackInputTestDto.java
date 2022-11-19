package com.pokemonurpg.configuration.v1.attack.attack.input;

import java.util.Collections;

import com.pokemonurpg.configuration.v1.attack.category.model.AttackCategory;
import com.pokemonurpg.configuration.v1.attack.target.model.AttackTargetType;
import com.pokemonurpg.configuration.v1.contest.attribute.model.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.oras.model.OrasContestMoveType;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class AttackInputTestDto extends AttackInputDto {
    public final static Type TYPE = new Type();
    public final static AttackCategory CATEGORY = new AttackCategory();
    public final static AttackTargetType TARGET = new AttackTargetType();
    public final static ContestAttribute RSE_ATTR = new ContestAttribute();
    public final static RseContestMoveType RSE_MOVE_TYPE = new RseContestMoveType();
    public final static ContestAttribute ORAS_ATTR = new ContestAttribute();
    public final static OrasContestMoveType ORAS_MOVE_TYPE = new OrasContestMoveType();
    public final static Item TM = new Item();

    public AttackInputTestDto() {
        setName(RandomStringGenerator.generate());
        setDescription(RandomStringGenerator.generate());
        setType(RandomStringGenerator.generate());
        setPower(RandomNumberGenerator.generate());
        setAccuracy(RandomNumberGenerator.generate());
        setPp(RandomNumberGenerator.generate());
        setCategory(RandomStringGenerator.generate());
        setTarget(RandomStringGenerator.generate());
        setContact(true);
        setSnatch(true);
        setSheerForce(true);
        setSubstitute(true);
        setMagicCoat(true);
        setRseContestAttribute(RandomStringGenerator.generate());
        setRseContestMoveType(RandomStringGenerator.generate());
        setOrasContestAttribute(RandomStringGenerator.generate());
        setOrasContestMoveType(RandomStringGenerator.generate());
        setTm(RandomStringGenerator.generate());
        setContestCombos(Collections.singletonList(new ContestComboInputDto()));
    }
}
