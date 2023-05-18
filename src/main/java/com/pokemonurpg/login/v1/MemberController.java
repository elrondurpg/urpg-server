package com.pokemonurpg.login.v1;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/urpg-login/v1/members")
@CrossOrigin
@Validated
public class MemberController {

    public static final String BOT_LOGIN_QUERY_PARAMETER = "bot";
    @Resource
    private LoginService loginService;

    @Resource
    private BotLoginService botLoginService;

    @Resource
    private RefreshService refreshService;

    @Resource
    private LogoutService logoutService;

    @AllowAll
    @PostMapping
    public @ResponseBody
    Session login(@Valid @RequestBody LoginRequest input, @QueryParam("type") String type) {
        if (BOT_LOGIN_QUERY_PARAMETER.equals(type)) {
            return botLoginService.login();
        }
        else {
            return loginService.login(input);
        }
    }

    @AllowAll
    @PutMapping
    public @ResponseBody
    Session refresh() {
        return refreshService.refresh();
    }

    @AllowAll
    @DeleteMapping
    public void logout() {
        logoutService.logout();
    }

}
