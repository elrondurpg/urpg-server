package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.battle.BattleLeadsDto;
import com.pokemonurpg.dto.battle.BattleRulesDto;
import com.pokemonurpg.dto.battle.BattleTeamsDto;
import com.pokemonurpg.object.battle.Battle;
import com.pokemonurpg.service.battle.BattleService;
import com.pokemonurpg.service.trainer.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battle")
@CrossOrigin
public class BattleController {
    private BattleService battleService;
    private MemberService memberService;

    @Autowired
    public BattleController(BattleService battleService, MemberService memberService) {
        this.battleService = battleService;
        this.memberService = memberService;
    }

    @PostMapping
    public @ResponseBody
    RestResponse initBattle(@RequestBody BattleRulesDto rules) {
        Battle battle = new Battle();
        Errors errors = battleService.initBattle(battle, rules);
        if (errors.hasErrors() || battle == null) {
            return new RestResponse(400, errors.getAllErrors());
        }
        else {
            return new RestResponse(200, battle.getDbid());
        }
    }

    @PutMapping("/teams")
    public @ResponseBody
    RestResponse setTeams(@RequestBody BattleTeamsDto teams) {
        Errors errors = battleService.setTeams(teams);
        if (errors.hasErrors()) {
            return new RestResponse(400, errors.getAllErrors());
        }
        else {
            return new RestResponse(200, "Teams set successfully!");
        }
    }

    @PutMapping("/leads")
    public @ResponseBody
    RestResponse setLeads(@RequestBody BattleLeadsDto leads) {
        Errors errors = battleService.setLeads(leads);
        if (errors.hasErrors()) {
            return new RestResponse(400, errors.getAllErrors());
        }
        else {
            return new RestResponse(200, "Leads set successfully!");
        }
    }

}
