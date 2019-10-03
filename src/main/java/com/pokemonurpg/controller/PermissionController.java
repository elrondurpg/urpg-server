package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import com.pokemonurpg.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {
    private PermissionService permissionService;
    private MemberService memberService;

    @Autowired
    public PermissionController(PermissionService permissionService, MemberService memberService) {
        this.permissionService = permissionService;
        this.memberService = memberService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllPermissions() {
        return new RestResponse(200, permissionService.findAll());
    }

    @PostMapping
    public @ResponseBody
    RestResponse createPermission(@RequestBody Authenticated<String> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Permission")) {
                String permission = input.getPayload();
                Errors errors = permissionService.createPermission(permission);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200, "Permission " + permission + " was created successfully!");
            }
            else return new RestResponse(401, "User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }
}
