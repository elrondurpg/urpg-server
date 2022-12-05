package com.pokemonurpg.configuration.v1.member.member.input;

import org.springframework.data.domain.Example;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberGetParams extends FilterableGetParams<Member> {
    Boolean bot = false;
    public Example<Member> asExample() {
        Member example = new Member();
        example.setBot(bot);
        return Example.of(example);
    }
}
