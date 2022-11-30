package com.pokemonurpg.account.v1.register;

import static com.pokemonurpg.account.v1.register.RegistrationConstants.CAN_REGISTER_FLAG_NAME;

import javax.annotation.Resource;
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
import com.pokemonurpg.lib.security.v1.Session;

public abstract class RegisterPlayerController<Handler extends RegisterPlayerHandler<Dto, ?>, Dto extends RegisterPlayerDto> {

    @Resource
    private Handler handler;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @EnabledByFlag(flag = CAN_REGISTER_FLAG_NAME)
    @Validated({ObjectCreation.class, RegisterNewUser.class})
    @PostMapping
    public @ResponseBody
    Session registerNewPlayer(@Valid @RequestBody Dto input) {
        return handler.handle(input);
    }
    
}
