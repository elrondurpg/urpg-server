package com.pokemonurpg.account.v1.register.common;

import static com.pokemonurpg.account.v1.register.common.RegistrationConstants.CAN_REGISTER_FLAG_NAME;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.RegisterNewUser;
import com.pokemonurpg.lib.management.v1.EnabledByFlag;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

public abstract class RegisterMemberController<Request extends RegisterMemberRequest, Response extends RegisterMemberResponse> {

    private RegisterMemberHandler<Request, Response, ?, ?> handler;

    public RegisterMemberController(RegisterMemberHandler<Request, Response, ?, ?> handler) {
        this.handler = handler;
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @EnabledByFlag(flag = CAN_REGISTER_FLAG_NAME)
    @Validated({ObjectCreation.class, RegisterNewUser.class})
    @PostMapping
    public @ResponseBody
    Response register(@Valid @RequestBody Request request) {
        return handler.handle(request);
    }
    
}
