package com.pokemonurpg.service;

import com.pokemonurpg.dto.attack.AttackDto;
import com.pokemonurpg.dto.attack.AttackInputDto;
import com.pokemonurpg.object.Attack;
import com.pokemonurpg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;

@Service
public class AttackService {

    private AttackRepository attackRepository;
    private AttackCategoryRepository attackCategoryRepository;
    private AttackTargetTypeRepository attackTargetTypeRepository;
    private TypeRepository typeRepository;
    private ContestAttributeRepository contestAttributeRepository;
    private RSEContestMoveTypeRepository rseContestMoveTypeRepository;
    private ORASContestMoveTypeRepository orasContestMoveTypeRepository;
    private DPPContestMoveTypeRepository dppContestMoveTypeRepository;

    @Autowired
    public AttackService(AttackRepository attackRepository, AttackCategoryRepository attackCategoryRepository, AttackTargetTypeRepository attackTargetTypeRepository, TypeRepository typeRepository, ContestAttributeRepository contestAttributeRepository, RSEContestMoveTypeRepository rseContestMoveTypeRepository, ORASContestMoveTypeRepository orasContestMoveTypeRepository, DPPContestMoveTypeRepository dppContestMoveTypeRepository) {
        this.attackRepository = attackRepository;
        this.attackCategoryRepository = attackCategoryRepository;
        this.attackTargetTypeRepository = attackTargetTypeRepository;
        this.typeRepository = typeRepository;
        this.contestAttributeRepository = contestAttributeRepository;
        this.rseContestMoveTypeRepository = rseContestMoveTypeRepository;
        this.orasContestMoveTypeRepository = orasContestMoveTypeRepository;
        this.dppContestMoveTypeRepository = dppContestMoveTypeRepository;
    }

    public List<Object> findAll() {
        return attackRepository.findAllNames();
    }

    public AttackDto findByName(String name) {
        Attack attack = attackRepository.findByName(name);
        if (attack != null) {
            return new AttackDto(attack);
        }
        else {
            List<Attack> results = attackRepository.findByNameStartingWith(name);
            if (!results.isEmpty()) {
                return new AttackDto(results.get(0));
            }
            else return null;
        }
    }

    public Errors createAttack(AttackInputDto input) {
        Errors errors = validateAttackCreate(input);
        if (!errors.hasErrors()) {
            Attack attack = new Attack(input);
            attack.setType(typeRepository.findByName(input.getType()));
            attack.setCategory(attackCategoryRepository.findByName(input.getCategory()));
            attack.setTarget(attackTargetTypeRepository.findByName(input.getTarget()));
            if (input.getRseContestAttribute() != null) {
                attack.setRseContestAttribute(contestAttributeRepository.findByName(input.getRseContestAttribute()));
            }
            if (input.getRseContestMoveType() != null) {
                attack.setRseContestMoveType(rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()));
            }
            if (input.getDppContestAttribute() != null) {
                attack.setDppContestAttribute(contestAttributeRepository.findByName(input.getDppContestAttribute()));
            }
            if (input.getDppContestMoveType() != null) {
                attack.setDppContestMoveType(dppContestMoveTypeRepository.findByName(input.getDppContestMoveType()));
            }
            if (input.getOrasContestAttribute() != null) {
                attack.setOrasContestAttribute(contestAttributeRepository.findByName(input.getOrasContestAttribute()));
            }
            if (input.getOrasContestMoveType() != null) {
                attack.setOrasContestMoveType(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()));
            }
            attackRepository.save(attack);
        }
        return errors;
    }

    public Errors updateAttack(AttackInputDto input) {
        Errors errors = validateAttackUpdate(input);
        if (!errors.hasErrors()) {
            Attack existingAttack = attackRepository.findByName(input.getName());
            if (input.getName() != null) {
                existingAttack.setName(input.getName());
            }
            if (input.getType() != null) {
                existingAttack.setType(typeRepository.findByName(input.getType()));
            }
            if (input.getDescription() != null) {
                existingAttack.setDescription(input.getDescription());
            }
            if (input.getPower() != null) {
                existingAttack.setPower(input.getPower());
            }
            if (input.getAccuracy() != null) {
                existingAttack.setAccuracy(input.getAccuracy());
            }
            if (input.getPp() != null) {
                existingAttack.setPp(input.getPp());
            }
            if (input.getCategory() != null) {
                existingAttack.setCategory(attackCategoryRepository.findByName(input.getCategory()));
            }
            if (input.getTarget() != null) {
                existingAttack.setTarget(attackTargetTypeRepository.findByName(input.getTarget()));
            }
            if (input.getContact() != null) {
                existingAttack.setContact(input.getContact());
            }
            if (input.getSnatch() != null) {
                existingAttack.setSnatch(input.getSnatch());
            }
            if (input.getSubstitute() != null) {
                existingAttack.setSubstitute(input.getSubstitute());
            }
            if (input.getSheerForce() != null) {
                existingAttack.setSheerForce(input.getSheerForce());
            }
            if (input.getMagicCoat() != null) {
                existingAttack.setMagicCoat(input.getMagicCoat());
            }
            if (input.getRseContestAttribute() != null) {
                existingAttack.setRseContestAttribute(contestAttributeRepository.findByName(input.getRseContestAttribute()));
            }
            if (input.getRseContestMoveType() != null) {
                existingAttack.setRseContestMoveType(rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()));
            }
            if (input.getDppContestAttribute() != null) {
                existingAttack.setDppContestAttribute(contestAttributeRepository.findByName(input.getDppContestAttribute()));
            }
            if (input.getDppContestMoveType() != null) {
                existingAttack.setDppContestMoveType(dppContestMoveTypeRepository.findByName(input.getDppContestMoveType()));
            }
            if (input.getOrasContestAttribute() != null) {
                existingAttack.setOrasContestAttribute(contestAttributeRepository.findByName(input.getOrasContestAttribute()));
            }
            if (input.getOrasContestMoveType() != null) {
                existingAttack.setOrasContestMoveType(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()));
            }
            attackRepository.save(existingAttack);
        }
        return errors;
    }

    public Errors validateAttackCreate(AttackInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Attack existingRecord = attackRepository.findByName(input.getName());
        if (existingRecord == null) {
            if (input.getName() == null || input.getName().length() < 3 || input.getName().length() > 17) {
                errors.reject("Attack name " + input.getName() + " is invalid.");
            }

            if (input.getType() == null) {
                errors.reject("Type cannot be null!");
            } else if (typeRepository.findByName(input.getType()) == null) {
                errors.reject("Type " + input.getType() + " is invalid.");
            }

            if (input.getDescription() == null || input.getDescription().length() < 3 || input.getDescription().length() > 100) {
                errors.reject("Description " + input.getDescription() + " is invalid.");
            }

            if (input.getPower() == null || input.getPower() < 0 || input.getPower() > 500) {
                errors.reject("Power value " + input.getPower() + " is invalid.");
            }

            if (input.getAccuracy() == null || input.getAccuracy() < 0 || input.getAccuracy() > 100) {
                errors.reject("Accuracy value " + input.getAccuracy() + " is invalid.");
            }

            if (input.getPp() == null || input.getPp() < 0 || input.getPp() > 100) {
                errors.reject("PP value " + input.getPp() + " is invalid.");
            }

            if (input.getCategory() == null) {
                errors.reject("Attack category cannot be null!");
            } else if (attackCategoryRepository.findByName(input.getCategory()) == null) {
                errors.reject("Attack category " + input.getCategory() + " is invalid.");
            }

            if (input.getTarget() == null) {
                errors.reject("Attack target type cannot be null!");
            } else if (attackTargetTypeRepository.findByName(input.getTarget()) == null) {
                errors.reject("Attack target type " + input.getTarget() + " is invalid.");
            }

            if (input.getContact() == null) {
                errors.reject("'Makes Contact' cannot be null.");
            }

            if (input.getSheerForce() == null) {
                errors.reject("'Affected by Sheer Force' cannot be null.");
            }

            if (input.getSnatch() == null) {
                errors.reject("'Stolen by Snatch' cannot be null.");
            }

            if (input.getSubstitute() == null) {
                errors.reject("'Blocked by Substitute' cannot be null.");
            }

            if (input.getMagicCoat() == null) {
                errors.reject("'Reflect by Magic Coat' cannot be null.");
            }

            if (input.getRseContestMoveType() != null && rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()) == null) {
                errors.reject("RSE Contest Move Type " + input.getRseContestMoveType() + " is invalid.");
            }

            if (input.getRseContestAttribute() != null && contestAttributeRepository.findByName(input.getRseContestAttribute()) == null) {
                errors.reject("RSE Contest Attribute " + input.getRseContestAttribute() + " is invalid.");
            }

            if (input.getDppContestMoveType() != null && dppContestMoveTypeRepository.findByName(input.getDppContestMoveType()) == null) {
                errors.reject("DPP Contest Move Type " + input.getDppContestMoveType() + " is invalid.");
            }

            if (input.getDppContestAttribute() != null && contestAttributeRepository.findByName(input.getDppContestAttribute()) == null) {
                errors.reject("DPP Contest Attribute " + input.getDppContestAttribute() + " is invalid.");
            }

            if (input.getOrasContestMoveType() != null && orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()) == null) {
                errors.reject("ORAS Contest Move Type " + input.getOrasContestMoveType() + " is invalid.");
            }

            if (input.getOrasContestAttribute() != null && contestAttributeRepository.findByName(input.getOrasContestAttribute()) == null) {
                errors.reject("ORAS Contest Attribute " + input.getOrasContestAttribute() + " is invalid.");
            }
        }
        else {
            errors.reject("Attack " + input.getName() + " already exists.");
        }

        return errors;
    }

    public Errors validateAttackUpdate(AttackInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Attack existingRecord = attackRepository.findByName(input.getName());
        if (existingRecord != null) {
            if (input.getName() != null && (input.getName().length() < 3 || input.getName().length() > 17)) {
                errors.reject("Attack name " + input.getName() + " is invalid.");
            }

            if (input.getType() != null && typeRepository.findByName(input.getType()) == null) {
                errors.reject("Type " + input.getType() + " is invalid.");
            }

            if (input.getDescription() != null && (input.getDescription().length() < 3 || input.getDescription().length() > 100)) {
                errors.reject("Description " + input.getDescription() + " is invalid.");
            }

            if (input.getPower() != null && (input.getPower() < 0 || input.getPower() > 500)) {
                errors.reject("Power value " + input.getPower() + " is invalid.");
            }

            if (input.getAccuracy() != null && (input.getAccuracy() < 0 || input.getAccuracy() > 100)) {
                errors.reject("Accuracy value " + input.getAccuracy() + " is invalid.");
            }

            if (input.getPp() != null && (input.getPp() < 0 || input.getPp() > 100)) {
                errors.reject("PP value " + input.getPp() + " is invalid.");
            }

            if (input.getCategory() != null && attackCategoryRepository.findByName(input.getCategory()) == null) {
                errors.reject("Attack category " + input.getCategory() + " is invalid.");
            }

            if (input.getTarget() != null && attackTargetTypeRepository.findByName(input.getTarget()) == null) {
                errors.reject("Attack target type " + input.getTarget() + " is invalid.");
            }

            if (input.getRseContestMoveType() != null && rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()) == null) {
                errors.reject("RSE Contest Move Type " + input.getRseContestMoveType() + " is invalid.");
            }

            if (input.getRseContestAttribute() != null && contestAttributeRepository.findByName(input.getRseContestAttribute()) == null) {
                errors.reject("RSE Contest Attribute " + input.getRseContestAttribute() + " is invalid.");
            }

            if (input.getDppContestMoveType() != null && dppContestMoveTypeRepository.findByName(input.getDppContestMoveType()) == null) {
                errors.reject("DPP Contest Move Type " + input.getDppContestMoveType() + " is invalid.");
            }

            if (input.getDppContestAttribute() != null && contestAttributeRepository.findByName(input.getDppContestAttribute()) == null) {
                errors.reject("DPP Contest Attribute " + input.getDppContestAttribute() + " is invalid.");
            }

            if (input.getOrasContestMoveType() != null && orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()) == null) {
                errors.reject("ORAS Contest Move Type " + input.getOrasContestMoveType() + " is invalid.");
            }

            if (input.getOrasContestAttribute() != null && contestAttributeRepository.findByName(input.getOrasContestAttribute()) == null) {
                errors.reject("ORAS Contest Attribute " + input.getOrasContestAttribute() + " is invalid.");
            }
        }
        else {
            errors.reject("Attack " + input.getName() + " doesn't exist.");
        }

        return errors;
    }
}
