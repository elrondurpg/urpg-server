package com.pokemonurpg.service.battle;

import com.pokemonurpg.dto.battle.*;
import com.pokemonurpg.object.battle.*;
import com.pokemonurpg.object.pokemon.Attack;
import com.pokemonurpg.object.pokemon.Species;
import com.pokemonurpg.object.pokemon.SpeciesAbility;
import com.pokemonurpg.object.pokemon.SpeciesAttack;
import com.pokemonurpg.object.trainer.Member;
import com.pokemonurpg.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BattleService {

    private BattleRepository battleRepository;
    private BattleGenerationRepository battleGenerationRepository;
    private BattleTypeRepository battleTypeRepository;
    private TeamTypeRepository teamTypeRepository;
    private SendTypeRepository sendTypeRepository;
    private WeatherRepository weatherRepository;
    private TerrainRepository terrainRepository;
    private MemberRepository memberRepository;
    private BattleTrainerRepository battleTrainerRepository;
    private BattleRefereeRepository battleRefereeRepository;
    private SpeciesRepository speciesRepository;
    private ItemRepository itemRepository;
    private AbilityRepository abilityRepository;
    private BattlePokemonRepository battlePokemonRepository;
    private AttackRepository attackRepository;
    private BattlePokemonAttackRepository battlePokemonAttackRepository;

    private Logger logger = LogManager.getLogger(BattleService.class);
    private String[] forbiddenForms = { "Mega", "Crowned", "Sunny", "Rainy", "Snowy", "Primal", "Origin", "Zen", "Pirouette", "Blade", "School", "Ultra", "Complete", "Ash", "Noice" };

    @Autowired
    public BattleService(BattleRepository battleRepository, BattleGenerationRepository battleGenerationRepository, BattleTypeRepository battleTypeRepository, TeamTypeRepository teamTypeRepository, SendTypeRepository sendTypeRepository, WeatherRepository weatherRepository, TerrainRepository terrainRepository, MemberRepository memberRepository, BattleTrainerRepository battleTrainerRepository, BattleRefereeRepository battleRefereeRepository, SpeciesRepository speciesRepository, ItemRepository itemRepository, AbilityRepository abilityRepository, BattlePokemonRepository battlePokemonRepository, AttackRepository attackRepository, BattlePokemonAttackRepository battlePokemonAttackRepository) {
        this.battleRepository = battleRepository;
        this.battleGenerationRepository = battleGenerationRepository;
        this.battleTypeRepository = battleTypeRepository;
        this.teamTypeRepository = teamTypeRepository;
        this.sendTypeRepository = sendTypeRepository;
        this.weatherRepository = weatherRepository;
        this.terrainRepository = terrainRepository;
        this.memberRepository = memberRepository;
        this.battleTrainerRepository = battleTrainerRepository;
        this.battleRefereeRepository = battleRefereeRepository;
        this.speciesRepository = speciesRepository;
        this.itemRepository = itemRepository;
        this.abilityRepository = abilityRepository;
        this.battlePokemonRepository = battlePokemonRepository;
        this.attackRepository = attackRepository;
        this.battlePokemonAttackRepository = battlePokemonAttackRepository;
    }

    public Errors initBattle(Battle battle, BattleRulesDto rules) {
        Errors errors = validateInitBattle(rules);

        if (!errors.hasErrors()) {
            createBattle(battle, rules);
            battle = battleRepository.save(battle);

            List<BattleTrainerDto> trainers = rules.getTrainers();
            for (BattleTrainerDto dto : trainers) {
                BattleTrainer trainer = new BattleTrainer();
                BattleTrainerKey key = new BattleTrainerKey();
                key.setBattleDbid(battle.getDbid());
                key.setTrainerDbid(memberRepository.findByUsername(dto.getName()).getDbid());
                trainer.setId(key);
                if (dto.getTeam() != null) {
                    trainer.setTeam(dto.getTeam());
                }
                battleTrainerRepository.save(trainer);
            }

            String refereeName = rules.getReferee();
            BattleReferee referee = new BattleReferee();
            BattleRefereeKey key = new BattleRefereeKey();
            key.setBattleDbid(battle.getDbid());
            key.setRefereeDbid(memberRepository.findByUsername(refereeName).getDbid());
            referee.setId(key);
            battleRefereeRepository.save(referee);
        }

        return errors;
    }

    public void createBattle(Battle battle, BattleRulesDto rules) {
        battle.setBattleGeneration(battleGenerationRepository.findByName(rules.getBattleGeneration()));
        battle.setBattleType(battleTypeRepository.findByName(rules.getBattleType()));
        battle.setSendType(sendTypeRepository.findByName(rules.getSendType()));
        battle.setTeamType(teamTypeRepository.findByName(rules.getTeamType()));
        battle.setTeamSize(rules.getTeamSize());

        Boolean ohkoClause = rules.getOhkoClause();
        if (ohkoClause == null) {
            battle.setOhkoClause(true);
        }
        else battle.setOhkoClause(ohkoClause);

        Boolean sleepClause = rules.getSleepClause();
        if (sleepClause == null) {
            battle.setSleepClause(true);
        }
        else battle.setSleepClause(sleepClause);

        Boolean freezeClause = rules.getFreezeClause();
        if (freezeClause == null) {
            battle.setFreezeClause(true);
        }
        else battle.setFreezeClause(freezeClause);

        Boolean accuracyClause = rules.getAccuracyClause();
        if (accuracyClause == null) {
            battle.setAccuracyClause(true);
        }
        else battle.setAccuracyClause(accuracyClause);

        Boolean evasionClause = rules.getEvasionClause();
        if (evasionClause == null) {
            battle.setEvasionClause(true);
        }
        else battle.setEvasionClause(evasionClause);

        Boolean speciesClause = rules.getSpeciesClause();
        if (speciesClause == null) {
            battle.setSpeciesClause(false);
        }
        else battle.setSpeciesClause(speciesClause);

        Boolean itemsAllowed = rules.getItemsAllowed();
        if (itemsAllowed == null) {
            battle.setItemsAllowed(false);
        }
        else battle.setItemsAllowed(itemsAllowed);

        Boolean itemClause = rules.getItemClause();
        if (itemClause == null) {
            battle.setItemClause(false);
        }
        else battle.setItemClause(itemClause);

        Boolean megaClause = rules.getMegaClause();
        if (megaClause == null) {
            battle.setMegaClause(false);
        }
        else battle.setMegaClause(megaClause);

        Boolean zmoveClause = rules.getZmoveClause();
        if (zmoveClause == null) {
            battle.setZmoveClause(false);
        }
        else battle.setZmoveClause(zmoveClause);

        Boolean dynamaxClause = rules.getDynamaxClause();
        if (dynamaxClause == null) {
            battle.setDynamaxClause(true);
        }
        else battle.setDynamaxClause(dynamaxClause);

        Boolean legendClause = rules.getLegendClause();
        if (legendClause == null) {
            battle.setLegendClause(false);
        }
        else battle.setLegendClause(legendClause);

        String weatherName = rules.getPermWeather();
        if (weatherName != null) {
            Weather weather = weatherRepository.findByName(weatherName);
            if (weather != null) {
                battle.setPermWeather(weather);
            }
        }

        String terrainName = rules.getPermTerrain();
        if (terrainName != null) {
            Terrain terrain = terrainRepository.findByName(terrainName);
            if (terrain != null) {
                battle.setPermTerrain(terrain);
            }
        }

        Boolean basicsClause = rules.getBasicsClause();
        if (basicsClause == null) {
            battle.setBasicsClause(false);
        }
        else battle.setBasicsClause(basicsClause);

        Boolean randomClause = rules.getRandomClause();
        if (randomClause == null) {
            battle.setRandomClause(false);
        }
        else battle.setRandomClause(randomClause);

        Boolean inversionClause = rules.getInversionClause();
        if (inversionClause == null) {
            battle.setInversionClause(false);
        }
        else battle.setInversionClause(inversionClause);

        Boolean skyClause = rules.getSkyClause();
        if (skyClause == null) {
            battle.setSkyClause(false);
        }
        else battle.setSkyClause(skyClause);

        Boolean gameboyClause = rules.getGameboyClause();
        if (gameboyClause == null) {
            battle.setGameboyClause(false);
        }
        else battle.setGameboyClause(gameboyClause);

        Boolean wonderLauncherClause = rules.getWonderLauncherClause();
        if (wonderLauncherClause == null) {
            battle.setWonderLauncherClause(false);
        }
        else battle.setWonderLauncherClause(wonderLauncherClause);

        battle.setState("WAIT_TEAMS");
    }

    public Errors validateInitBattle(BattleRulesDto rules) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        if (rules != null) {
            String generation = rules.getBattleGeneration();
            if (generation == null || battleGenerationRepository.findByName(generation) == null) {
                errors.reject("Battle Generation " + generation + " is invalid.");
            }

            String battleType = rules.getBattleType();
            if (battleType == null || battleTypeRepository.findByName(battleType) == null) {
                errors.reject("Battle Type " + battleType + " is invalid.");
            }

            String sendType = rules.getSendType();
            if (sendType == null || sendTypeRepository.findByName(sendType) == null) {
                errors.reject("Send Type " + sendType + " is invalid.");
            }
            else {
                if (!"Private".equals(sendType)) {
                    if (!"Singles".equals(battleType)) {
                        errors.reject("Battle Type " + battleType + " requires Private sends.");
                    }

                    if (rules.getGameboyClause() != null && rules.getGameboyClause() == true) {
                        errors.reject("Gameboy-style battles require Private sends.");
                    }
                }
            }

            String teamType = rules.getTeamType();
            if (teamType == null || teamTypeRepository.findByName(teamType) == null) {
                errors.reject("Team Type " + teamType + " is invalid.");
            }

            Integer teamSize = rules.getTeamSize();
            if (teamSize == null || teamSize < 1) {
                errors.reject("Team sizes less than one are invalid.");
            }

            if ("Battle Royale".equals(battleType) && teamSize != 3) {
                errors.reject("Battle Royales require a team size of exactly three.");
            }

            if ("Doubles".equals(battleType) && teamSize < 2) {
                errors.reject("Double Battles require a team size of at least two.");
            }

            if ("Triples".equals(battleType) && teamSize < 3) {
                errors.reject("Triple Battles require a team size of at least three.");
            }

            if ("Rotation".equals(battleType) && teamSize != 3) {
                errors.reject("Rotation Battles require a team size of at exactly three.");
            }

            List<BattleTrainerDto> trainers = rules.getTrainers();
            ArrayList<String> trainerNames = new ArrayList<>();
            if (trainers == null || trainers.size() < 2) {
                errors.reject("All battles require at least two trainers.");
            } else if (("Multi".equals(battleType) || ("Battle Royale").equals(battleType)) && trainers.size() != 4) {
                errors.reject(battleType + " battles require exactly four trainers.");
            } else {
                HashMap<Integer, Integer> teamSizes = new HashMap<>();

                for (BattleTrainerDto trainer : trainers) {

                    String name = trainer.getName();
                    if (name == null || memberRepository.findByUsername(name) == null) {
                        errors.reject("Trainer " + name + " is invalid.");
                    }
                    else if (trainerNames.contains(name)) {
                        errors.reject("Trainer " + name + " is duplicated.");
                    }
                    else {
                        trainerNames.add(name);

                        Integer team = trainer.getTeam();
                        if (team != null) {
                            if (!teamSizes.containsKey(team)) {
                                teamSizes.put(team, 1);
                            } else {
                                teamSizes.put(team, teamSizes.get(team) + 1);
                            }
                        }
                    }
                }

                if ("Multi".equals(battleType)) {
                    if (!teamSizes.containsKey(1) || !teamSizes.containsKey(2) || teamSizes.get(1) != 2 || teamSizes.get(2) != 2) {
                        errors.reject("Multi battles require exactly two teams, with exactly two trainers each.");
                    }
                }
            }

            Boolean dynamaxClause = rules.getDynamaxClause();
            if (dynamaxClause == null || dynamaxClause == false) {
                Boolean megaClause = rules.getMegaClause();
                Boolean zmoveClause = rules.getZmoveClause();
                if (megaClause == null || megaClause == false || zmoveClause == null || zmoveClause == false) {
                    errors.reject("Dynamax Clause must be ON when either the Mega Clause or Z-Move Clause are OFF.");
                }
            }

            String weather = rules.getPermWeather();
            if (weather != null && weatherRepository.findByName(weather) == null) {
                errors.reject("Weather " + weather + " is invalid.");
            }

            String terrain = rules.getPermTerrain();
            if (terrain != null) {
                Terrain t = terrainRepository.findByName(terrain);
                if (t == null || !t.isPermaAllowed()) {
                    errors.reject("Terrain " + terrain + " is invalid.");
                }
            }

            String referee = rules.getReferee();
            if (referee == null || memberRepository.findByUsername(referee) == null) {
                errors.reject("Referee " + referee + " is invalid.");
            }
            else if (trainerNames.contains(referee)) {
                errors.reject("Referee " + referee + " cannot also be a battler.");
            }
        }
        else errors.reject("Found a null ruleset.");

        return errors;
    }

    public Errors setTeams(BattleTeamsDto teams) {
        Errors errors = validateSetTeams(teams);

        if (!errors.hasErrors()) {
            Battle battle = battleRepository.findByDbid(teams.getBattleDbid());

            for (BattleTeamDto teamDto : teams.getTrainers()) {
                HashMap<Integer, Integer> numEachSpecies = new HashMap<>();
                for (BattlePokemonDto pokemonDto : teamDto.getPokemon()) {
                    Species species = speciesRepository.findByName(pokemonDto.getSpecies());
                    int dexno = species.getDexno();

                    int sequenceNo = 1;
                    if (numEachSpecies.containsKey(dexno)) {
                        sequenceNo = numEachSpecies.get(dexno) + 1;
                    }
                    numEachSpecies.put(dexno, sequenceNo);

                    createBattlePokemon(battle, teamDto, pokemonDto, sequenceNo);
                }
            }

            String teamType = battle.getTeamType().getName();
            if ("OPEN".equals(teamType) || battle.getTeamSize() == 1) {
                battle.setState("WAIT_MOVES");
            }
            else battle.setState("WAIT_LEADS");
            battleRepository.save(battle);
        }

        return errors;
    }

    private void createBattlePokemon(Battle battle, BattleTeamDto teamDto, BattlePokemonDto pokemonDto, int sequenceNo) {
        BattlePokemon pokemon = new BattlePokemon();
        pokemon.setTrainer(memberRepository.findByUsername(teamDto.getName()));
        pokemon.setSpecies(speciesRepository.findByName(pokemonDto.getSpecies()));
        pokemon.setBattle(battle);
        pokemon.setGender(pokemonDto.getGender());
        pokemon.setNickname(pokemonDto.getNickname());
        pokemon.setSequenceNo(sequenceNo);

        if (!battle.getBattleGeneration().getName().equals("GSC")) {
            pokemon.setAbility(abilityRepository.findByName(pokemonDto.getAbility()));
        }

        if (battle.isItemsAllowed()) {
            pokemon.setItem(itemRepository.findByName(pokemonDto.getItem()));
        }

        String teamType = battle.getTeamType().getName();

        if ("OPEN".equals(teamType) || battle.getTeamSize() == 1) {
            pokemon.setActive(true);
            pokemon.setPosition(1);
        }

        pokemon = battlePokemonRepository.save(pokemon);

        List<String> attacks = pokemonDto.getAttacks();
        if (attacks != null) {
            for (String attackName : attacks) {
                createBattlePokemonAttack(pokemon, attackName);
            }
        }
    }

    private void createBattlePokemonAttack(BattlePokemon pokemon, String attackName) {
        Attack attack = attackRepository.findByName(attackName);
        BattlePokemonAttack bpa = new BattlePokemonAttack(pokemon, attack);
        battlePokemonAttackRepository.save(bpa);
    }

    public Errors validateSetTeams(BattleTeamsDto teams) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        if (teams != null) {
            Long battleDbid = teams.getBattleDbid();
            if (battleDbid != null) {
                Battle battle = battleRepository.findByDbid(battleDbid);
                if (battle != null) {
                    if ("WAIT_TEAMS".equalsIgnoreCase(battle.getState())) {
                        List<BattleTeamDto> trainerDtos = teams.getTrainers();
                        ArrayList<String> checkedTrainerNames = new ArrayList<>();
                        if (trainerDtos != null) {
                            for (BattleTeamDto dto : trainerDtos) {
                                validateTeam(errors, battle, dto);

                                String trainerName = dto.getName();
                                if (trainerName != null) {
                                    if (!checkedTrainerNames.contains(trainerName.toUpperCase())) {
                                        checkedTrainerNames.add(trainerName.toUpperCase());
                                    } else {
                                        errors.reject("Found more than one team input for trainer " + trainerName);
                                    }
                                }
                            }
                        }

                        List<BattleTrainer> battleTrainers = battle.getTrainers();
                        for (BattleTrainer trainer : battleTrainers) {
                            String trainerName = trainer.getMember().getUsername();
                            if (!checkedTrainerNames.contains(trainerName.toUpperCase())) {
                                errors.reject("Didn't find starting team information for trainer " + trainerName);
                            }
                        }
                    }
                    else {
                        errors.reject("Battle with DBID " + battleDbid + " is not accepting teams now.");
                    }
                }
                else {
                    errors.reject("Battle with DBID " + battleDbid + " could not be found.");
                }
            }
            else {
                errors.reject("Battle DBID must not be null.");
            }
        }

        return errors;
    }

    public void validateTeam(Errors errors, Battle battle, BattleTeamDto dto) {
        List<BattleTrainer> trainers = battle.getTrainers();
        String name = dto.getName();
        if (name != null) {
            Member member = memberRepository.findByUsername(name);
            if (member != null) {
                boolean found = false;
                for (BattleTrainer trainer : trainers) {
                    if (name.equals(trainer.getMember().getUsername())) {
                        found = true;
                        List<BattlePokemonDto> pokemonDtos = dto.getPokemon();
                        if (pokemonDtos != null) {
                            String teamType = battle.getTeamType().getName();
                            if ("Full".equalsIgnoreCase(teamType) || "Preview".equalsIgnoreCase(teamType)) {
                                if (pokemonDtos.size() != battle.getTeamSize()) {
                                    errors.reject("Each trainer must send all " + battle.getTeamSize() + " Pokemon before the battle can start.");
                                }
                            }
                            else if ("Open".equalsIgnoreCase(teamType)) {
                                if (pokemonDtos.size() != 1) {
                                    errors.reject("Each trainer must send exactly one Pokemon to start the battle.");
                                }
                            }

                            ArrayList<Integer> dexnos = new ArrayList<>();
                            ArrayList<String> heldItems = new ArrayList<>();
                            for (BattlePokemonDto pokemonDto : pokemonDtos) {
                                int dexno = validateTeamPokemon(errors, battle, pokemonDto);
                                if (!dexnos.contains(dexno)) {
                                    dexnos.add(dexno);
                                }
                                else if (battle.isSpeciesClause()){
                                    errors.reject("Each trainer may send no more than one of each Pokemon species.");
                                }

                                if (pokemonDto.getItem() != null) {
                                    if (!heldItems.contains(pokemonDto.getItem().toUpperCase())) {
                                        heldItems.add(pokemonDto.getItem().toUpperCase());
                                    } else if (battle.isItemClause()) {
                                        errors.reject("Each trainer may send no more than one Pokemon holding a given item.");
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                if (!found) {
                    errors.reject("Trainer with name " + name + " is not a participant in this battle.");
                }
            }
            else {
                errors.reject("Trainer with name " + name + " could not be found.");
            }
        }
        else {
            errors.reject("Trainer name must not be null.");
        }
    }

    public int validateTeamPokemon(Errors errors, Battle battle, BattlePokemonDto dto) {
        String name = dto.getSpecies();
        if (name != null) {
            Species species = speciesRepository.findByName(name);
            if (species != null) {
                for (String suffix : forbiddenForms) {
                    if (species.getName().contains("-" + suffix)) {
                        errors.reject("Pokemon species with name " + name + " is a form that can't be sent at the start of battle.");
                    }
                }

                validatePokemonGender(errors, species, dto);
                validatePokemonAbility(errors, species, dto);
                if (battle.isGameboyClause()) {
                    validatePokemonAttacks(errors, species, dto);
                }

                if (battle.isLegendClause()) {
                    if (species.getLegendaryTier() != 0) {
                        errors.reject("Pokemon " + name + " cannot be sent in battles with Legend Clause ON.");
                    }
                }

                String itemName = dto.getItem();
                if (itemName != null && itemRepository.findByName(itemName) == null) {
                    errors.reject("Item with name " + itemName + " could not be found.");
                }

                return species.getDexno();
            }
            else {
                errors.reject("Pokemon species with name " + name + " could not be found.");
            }
        }
        else {
            errors.reject("Pokemon species name must not be null.");
        }

        return -1;
    }

    public void validatePokemonGender(Errors errors, Species species, BattlePokemonDto dto) {
        String name = species.getName();
        Character gender = dto.getGender();
        if (gender != null) {
            if (gender == 'M') {
                if (!species.getMaleAllowed()) {
                    errors.reject("Pokemon species with name " + name + " cannot be male.");
                }
            }
            else if (gender == 'F') {
                if (!species.getFemaleAllowed()) {
                    errors.reject("Pokemon species with name " + name + " cannot be female.");
                }
            }
            else if (gender == 'G') {
                if (species.getMaleAllowed() || species.getFemaleAllowed()) {
                    errors.reject("Pokemon species with name " + name + " cannot be genderless.");
                }
            }
            else {
                errors.reject("Pokemon gender must be 'M', 'F', or 'G'.");
            }
        }
        else {
            errors.reject("Pokemon gender must not be null.");
        }
    }

    public void validatePokemonAbility(Errors errors, Species species, BattlePokemonDto dto) {
        String name = species.getName();
        List<SpeciesAbility> speciesAbilities = species.getSpeciesAbilities();
        String abilityName = dto.getAbility();
        if (abilityName != null) {
            boolean found = false;
            for (SpeciesAbility ability : speciesAbilities) {
                if (abilityName.equalsIgnoreCase(ability.getAbility().getName())) {
                    found = true;
                }
            }
            if (!found) {
                errors.reject("Pokemon species with name " + name + " can't have ability " + abilityName);
            }
        }
        else {
            errors.reject("Pokemon ability must not be null.");
        }
    }

    public void validatePokemonAttacks(Errors errors, Species species, BattlePokemonDto dto) {
        String name = species.getName();
        List<SpeciesAttack> speciesAttacks = species.getSpeciesAttacks();
        List<String> attackNames = dto.getAttacks();
        if (attackNames != null && attackNames.size() > 0 && attackNames.size() <= 4) {
            ArrayList<String> checkedAttackNames = new ArrayList<>();
            for (String attackName : attackNames) {
                if (!checkedAttackNames.contains(attackName.toUpperCase())) {
                    checkedAttackNames.add(attackName.toUpperCase());
                }
                else {
                    errors.reject("Found a duplicated attack " + attackName + " on Pokemon " + name);
                }

                boolean found = false;
                for (SpeciesAttack speciesAttack : speciesAttacks) {
                    if (attackName.equalsIgnoreCase(speciesAttack.getAttack().getName())) {
                        found = true;
                    }
                }
                if (!found) {
                    errors.reject("Pokemon species with name " + name + " can't have attack " + attackName);
                }
            }
        }
        else {
            errors.reject("Gameboy rules require at least one attack and no more than four attacks per Pokemon.");
        }

    }

    public Errors setLeads(BattleLeadsDto leads) {
        Errors errors = validateSetLeads(leads);

        if (!errors.hasErrors()) {
            long battleDbid = leads.getBattleDbid();
            Battle battle = battleRepository.findByDbid(battleDbid);

            for (BattleLeadDto leadDto : leads.getTrainers()) {
                List<BattleLeadPokemonDto> pokemonDtos = leadDto.getPokemon();
                Member member = memberRepository.findByUsername(leadDto.getName());
                BattleTrainer trainer = battleTrainerRepository.findById(new BattleTrainerKey(member.getDbid(), battleDbid)).get();
                List<BattlePokemon> pokemons = battlePokemonRepository.findByTrainer(trainer.getMember());
                for (BattleLeadPokemonDto dto : pokemonDtos) {
                    for (BattlePokemon pokemon : pokemons) {
                        if (pokemon.getSpecies().getName().equals(dto.getSpecies())) {
                            if (pokemon.getSequenceNo() == dto.getSequenceNo()) {
                                if (dto.getPosition() != null) {
                                    pokemon.setPosition(dto.getPosition());
                                }
                                pokemon.setActive(true);
                                battlePokemonRepository.save(pokemon);
                            }
                        }
                    }
                }
            }

            battle.setState("WAIT_MOVES");
            battleRepository.save(battle);
        }

        return errors;
    }

    public Errors validateSetLeads(BattleLeadsDto leads) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        if (leads != null) {
            Long battleDbid = leads.getBattleDbid();
            if (battleDbid != null) {
                Battle battle = battleRepository.findByDbid(battleDbid);
                if (battle != null) {
                    if ("WAIT_LEADS".equalsIgnoreCase(battle.getState())) {
                        List<BattleLeadDto> trainerDtos = leads.getTrainers();
                        ArrayList<String> checkedTrainerNames = new ArrayList<>();
                        if (trainerDtos != null) {
                            for (BattleLeadDto dto : trainerDtos) {
                                validateLead(errors, battle, dto);

                                String trainerName = dto.getName();
                                if (trainerName != null) {
                                    if (!checkedTrainerNames.contains(trainerName.toUpperCase())) {
                                        checkedTrainerNames.add(trainerName.toUpperCase());
                                    } else {
                                        errors.reject("Found more than one lead input for trainer " + trainerName);
                                    }
                                }
                            }
                        }

                        List<BattleTrainer> battleTrainers = battle.getTrainers();
                        for (BattleTrainer trainer : battleTrainers) {
                            String trainerName = trainer.getMember().getUsername();
                            if (!checkedTrainerNames.contains(trainerName.toUpperCase())) {
                                errors.reject("Didn't find lead Pokemon information for trainer " + trainerName);
                            }
                        }
                    }
                    else {
                        errors.reject("Battle with DBID " + battleDbid + " is not accepting leads now.");
                    }
                }
                else {
                    errors.reject("Battle with DBID " + battleDbid + " could not be found.");
                }
            }
            else {
                errors.reject("Battle DBID must not be null.");
            }
        }

        return errors;
    }

    public void validateLead(Errors errors, Battle battle, BattleLeadDto dto) {
        List<BattleTrainer> trainers = battle.getTrainers();
        String name = dto.getName();
        if (name != null) {
            Member member = memberRepository.findByUsername(name);
            if (member != null) {
                boolean found = false;
                for (BattleTrainer trainer : trainers) {
                    if (name.equals(trainer.getMember().getUsername())) {
                        found = true;
                        List<BattleLeadPokemonDto> pokemonDtos = dto.getPokemon();
                        if (pokemonDtos != null) {
                            HashMap<Integer, Integer> positions = new HashMap<>();
                            ArrayList<String> checkedPokemon = new ArrayList<>();

                            for (BattleLeadPokemonDto pokemon : pokemonDtos) {
                                if (!checkedPokemon.contains((pokemon.getSpecies() + "|" + pokemon.getSequenceNo()).toUpperCase())) {
                                    checkedPokemon.add((pokemon.getSpecies() + "|" + pokemon.getSequenceNo()).toUpperCase());
                                }
                                else {
                                    errors.reject("Trainer " + trainer.getMember().getUsername() + " duplicated a Pokemon: " + pokemon.getSpecies() + " " + pokemon.getSequenceNo());
                                }

                                validateLeadPokemon(errors, trainer, pokemon);
                                Integer position = pokemon.getPosition();
                                if (position != null) {
                                    if (!positions.containsKey(position)) {
                                        positions.put(position, 1);
                                    }
                                    else positions.put(position, positions.get(position) + 1);
                                }
                            }

                            validatePositions(errors, battle, trainer, pokemonDtos, positions);
                        }
                        else {
                            errors.reject("Pokemon array must not be null!");
                        }
                        break;
                    }
                }
                if (!found) {
                    errors.reject("Trainer with name " + name + " is not a participant in this battle.");
                }
            }
            else {
                errors.reject("Trainer with name " + name + " could not be found.");
            }
        }
        else {
            errors.reject("Trainer name must not be null.");
        }
    }

    public void validateLeadPokemon(Errors errors, BattleTrainer trainer, BattleLeadPokemonDto dto) {
        List<BattlePokemon> pokemons = battlePokemonRepository.findByTrainer(trainer.getMember());
        boolean found = false;
        String speciesName = dto.getSpecies();
        if (speciesName != null) {
            Species species = speciesRepository.findByName(speciesName);
            if (species != null) {
                if (dto.getSequenceNo() != null) {
                    for (BattlePokemon pokemon : pokemons) {
                        if (dto.getSpecies().equals(pokemon.getSpecies().getName())) {
                            if (dto.getSequenceNo() == pokemon.getSequenceNo()) {
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        errors.reject("Trainer " + trainer.getMember().getUsername() + " did not send a " + dto.getSpecies() + " with seq. no. " + dto.getSequenceNo());
                    }
                }
                else {
                    errors.reject("Pokemon sequence number must not be null.");
                }
            } else {
                errors.reject("Pokemon species with name " + speciesName + " could not be found.");
            }
        }
        else {
            errors.reject("Pokemon species name must not be null.");
        }
    }

    public void validatePositions(Errors errors, Battle battle, BattleTrainer trainer, List<BattleLeadPokemonDto> pokemonDtos, HashMap<Integer,Integer> positions) {
        String battleType = battle.getBattleType().getName();

        if ("Singles".equals(battleType) || "FFA".equals(battleType) || "Multi".equals(battleType) || "Battle Royale".equals(battleType)) {
            if (pokemonDtos.size() != 1) {
                errors.reject("Wrong number of leads specified for trainer " + trainer.getMember().getUsername());
            }
        }

        if ("Doubles".equals(battleType)) {
            if (pokemonDtos.size() != 2) {
                errors.reject("Wrong number of leads specified for trainer " + trainer.getMember().getUsername());
            }
        }

        if ("Triples".equals(battleType) || "Rotation".equals(battleType)) {
            if (pokemonDtos.size() != 3) {
                errors.reject("Wrong number of leads specified for trainer " + trainer.getMember().getUsername());
            }

            boolean[] found = new boolean[3];
            for (int position : positions.keySet()) {
                int value = positions.get(position);
                if (position < 1 || position > 3) {
                    errors.reject("Found a Pokemon in an invalid position (" + position + ").");
                }
                else if (value != 1) {
                    errors.reject("Found the wrong number of Pokemon in position " + position);
                }
                else {
                    found[position - 1] = true;
                }
            }

            int i = 0;
            for (boolean position : found) {
                i++;
                if (!position) {
                    errors.reject("Didn't find a Pokemon in required position (" + i + ").");
                }
            }
        }
    }
}
