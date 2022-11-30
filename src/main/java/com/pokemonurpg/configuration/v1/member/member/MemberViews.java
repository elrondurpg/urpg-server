package com.pokemonurpg.configuration.v1.member.member;

import com.pokemonurpg.configuration.v1.member.MemberSubdomainViews;

public interface MemberViews extends MemberSubdomainViews {
    public static interface Id extends MemberViews {

    }
    public static interface Brief extends Id {

    }
    public static interface Full extends Brief {

    }
}
