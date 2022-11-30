package com.pokemonurpg.account.v1.register.beginner;

public class RegisterBeginnerHandlerFake extends RegisterBeginnerHandler {
    public final static RegisterBeginnerRequest VALID_REQUEST = new RegisterBeginnerRequest();
    public final static RegisterBeginnerResponse VALID_RESPONSE = new RegisterBeginnerResponse();

    public RegisterBeginnerHandlerFake() {
        super(null, null);
    }

    @Override
    public RegisterBeginnerResponse handle(RegisterBeginnerRequest request) {
        if (VALID_REQUEST.equals(request)) {
            return VALID_RESPONSE;
        }
        else {
            throw new AssertionError("No test case was implemented for the provided request.");
        }
    }
    
}
