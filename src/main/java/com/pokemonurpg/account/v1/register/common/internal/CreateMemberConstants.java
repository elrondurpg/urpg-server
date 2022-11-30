package com.pokemonurpg.account.v1.register.common.internal;

public class CreateMemberConstants {
    public final static String NON_UNIQUE_ID_ERROR = "The provided Discord ID is already in use.";
    public final static String NON_UNIQUE_USERNAME_ERROR = "The provided username is already in use.";
    public final static String CREATED_KNOWN_NAME_CLAIM_EXCEPTION_MESSAGE = "The provided username belongs to a historical gym leader, elite four member, or URPG champion. Please seek approval from a member of URPG Staff to claim this username.";
    public final static String USERNAME_BEING_CLAIMED_ERROR = "Another claim has already been registered for the provided username. If you believe this is an error, please see a member of URPG Staff.";
}
