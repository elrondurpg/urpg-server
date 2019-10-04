package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.RoleDto;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.security.RoleInputDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import com.pokemonurpg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    private RoleService roleService;
    private MemberService memberService;

    @Autowired
    public RoleController(RoleService roleService, MemberService memberService) {
        this.roleService = roleService;
        this.memberService = memberService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllRoles() {
        return new RestResponse(200, roleService.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getRoleByName(@PathVariable("name") String name) {
        try {
            RoleDto dto = roleService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, "Role not found.");
        } catch (IllegalStateException e) {
            return new RestResponse(400, "Bad request.");
        }
    }

    @PostMapping
    public @ResponseBody
    RestResponse createRole(@RequestBody Authenticated<RoleInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Role")) {
                RoleInputDto role = input.getPayload();
                Errors errors = roleService.createRole(role);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200, "Role " + role.getName() + " was created successfully!");
            }
            else return new RestResponse(401, "User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateRole(@RequestBody Authenticated<RoleInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Role")) {
                RoleInputDto role = input.getPayload();
                Errors errors = roleService.updateRole(role);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200, "Role " + role.getName() + " was updated successfully!");
            }
            else return new RestResponse(401, "User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }
}
