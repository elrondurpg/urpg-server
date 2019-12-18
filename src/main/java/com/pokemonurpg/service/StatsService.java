package com.pokemonurpg.service;

import com.pokemonurpg.dto.stats.input.StatsInputDto;
import com.pokemonurpg.dto.stats.response.*;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.ItemRepository;
import com.pokemonurpg.repository.MemberRepository;
import com.pokemonurpg.repository.OwnedItemRepository;
import com.pokemonurpg.repository.OwnedPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class StatsService {
    private MemberRepository memberRepository;
    private OwnedPokemonRepository ownedPokemonRepository;
    private LogService logService;
    private ItemRepository itemRepository;
    private OwnedItemService ownedItemService;

    @Autowired
    public StatsService(MemberRepository memberRepository, OwnedPokemonRepository ownedPokemonRepository, LogService logService,
                        ItemRepository itemRepository, OwnedItemService ownedItemService) {
        this.memberRepository = memberRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.logService = logService;
        this.itemRepository = itemRepository;
        this.ownedItemService = ownedItemService;
    }

    public StatsDto findByName(String name) {
        Member trainer = memberRepository.findByUsername(name);
        if (trainer != null) {
            return buildStatsDto(trainer);
        }
        else {
            List<Member> results = memberRepository.findByUsernameStartingWith(name);
            if (!results.isEmpty()) {
                return buildStatsDto(results.get(0));
            }
            else return null;
        }
    }

    public StatsDto buildStatsDto(Member trainer) {
        StatsDto dto = new StatsDto(trainer);
        dto.setPokemon(buildOwnedPokemonDtoList(trainer));
        dto.setItems(buildOwnedItemsDtoList(trainer));
        dto.setLogs(buildLogRecordDtoList(trainer));
        return dto;
    }

    public List<OwnedPokemonBriefDto> buildOwnedPokemonDtoList(Member trainer) {
        List<OwnedPokemon> ownedPokemonRecords = trainer.getPokemon();
        List<OwnedPokemonBriefDto> result = new ArrayList<>();

        for (OwnedPokemon pokemon : ownedPokemonRecords) {
            OwnedPokemonBriefDto dto = new OwnedPokemonBriefDto(pokemon);
            result.add(dto);
        }

        return result;
    }

    public OwnedPokemonDto findOwnedPokemonByDbid(int dbid) {
        OwnedPokemon pokemon = ownedPokemonRepository.findByDbid(dbid);
        OwnedPokemonDto dto = new OwnedPokemonDto(pokemon);
        if (dto != null) {
            if (dto.getAttacks() != null) {
                dto.getAttacks().addAll(getLevelUpMoves(pokemon));
            }
            if (dto.getAbilities() != null) {
                dto.getAbilities().addAll(getNonHiddenAbilities(pokemon));
            }
        }
        return dto;
    }

    public List<String> getLevelUpMoves(OwnedPokemon pokemon) {
        List<String> result = new ArrayList<>();
        Species species = pokemon.getSpecies();
        if (species != null) {
            for (SpeciesAttack attack : species.getSpeciesAttacks()) {
                if (attack.getAttack() != null && attack.getMethod().equals("LEVEL-UP")) {
                    result.add(attack.getAttack().getName());
                }
            }
        }
        return result;
    }

    public List<String> getNonHiddenAbilities(OwnedPokemon pokemon) {
        List<String> result = new ArrayList<>();
        Species species = pokemon.getSpecies();
        if (species != null) {
            for (SpeciesAbility ability : species.getSpeciesAbilities()) {
                if (ability.getAbility() != null && !ability.getHidden()) {
                    result.add(ability.getAbility().getName());
                }
            }
        }
        return result;
    }

    public List<OwnedItemDto> buildOwnedItemsDtoList(Member trainer) {
        List<OwnedItem> ownedItemRecords = trainer.getItems();
        List<OwnedItemDto> result = new ArrayList<>();

        for (OwnedItem item : ownedItemRecords) {
            OwnedItemDto dto = new OwnedItemDto(item);
            result.add(dto);
        }

        return result;
    }

    public List<LogRecordDto> buildLogRecordDtoList(Member trainer) {
        Date date = getDateDaysAgo(30);
        List<LogRecordDto> dtos = logService.findLogsSinceDateForMember(date, trainer);
        return dtos;
    }

    public Date getDateDaysAgo(int days) {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        return new Date(System.currentTimeMillis() - (days * DAY_IN_MS));
    }

    public Errors updateStats(StatsInputDto input, String name) {
        Errors errors = validateStatsUpdate(input, name);

        if (!errors.hasErrors()) {
            List<LogRecord> logs = new ArrayList<>();

            Member existingRecord = memberRepository.findByUsername(name);

            if (input.getName() != null && !input.getName().equals(existingRecord.getUsername())) {
                existingRecord.setUsername(input.getName());
                logs.add(new LogRecord(existingRecord, "This user's name was changed to " + input.getName()));
            }

            if (input.getMoney() != null) {
                int currentMoney = existingRecord.getMoney();
                int difference = input.getMoney() - currentMoney;
                if (difference > 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " added $" + difference));
                }
                else if (difference < 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " subtracted $" + (difference * -1)));
                }
                existingRecord.setMoney(input.getMoney());
            }

            if (input.getWins() != null) {
                int current = existingRecord.getWins();
                int difference = input.getWins() - current;
                if (difference > 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " won " + difference + " games"));
                }
                else if (difference < 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " removed " + (difference * -1) + " wins"));
                }
                existingRecord.setWins(input.getWins());
            }

            if (input.getLosses() != null) {
                int current = existingRecord.getLosses();
                int difference = input.getLosses() - current;
                if (difference > 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " lost " + difference + " games"));
                }
                else if (difference < 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " removed " + (difference * -1) + " losses"));
                }
                existingRecord.setLosses(input.getLosses());
            }

            if (input.getDraws() != null) {
                int current = existingRecord.getDraws();
                int difference = input.getDraws() - current;
                if (difference > 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " drew " + difference + " games"));
                }
                else if (difference < 0) {
                    logs.add(new LogRecord(existingRecord, existingRecord.getUsername() + " removed " + (difference * -1) + " draws"));
                }
                existingRecord.setDraws(input.getDraws());
            }

            if (input.getItems() != null) {
                ownedItemService.updateAll(existingRecord, input.getItems());
            }

            ownedItemService.updateAll(existingRecord, input.getItems());

            for (LogRecord log : logs) {
                logService.log(log);
            }

            memberRepository.save(existingRecord);
        }

        return errors;
    }

    public Errors validateStatsUpdate(StatsInputDto input, String name) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Member existingRecord = memberRepository.findByUsername(name);
        if (existingRecord != null) {
            if (input.getName() == null || input.getName().length() < 3 || input.getName().length() > 20) {
                errors.reject("Name " + input.getName() + " is invalid.");
            }

            if (input.getItems() != null) {
                for (OwnedItemDto dto : input.getItems()) {
                    Item item = itemRepository.findByName(dto.getName());
                    if (item != null) {
                        int quantity = dto.getQuantity();
                        if (quantity < 0 || quantity > 1000) {
                            errors.reject("Quantity " + quantity + " for item " + dto.getName() + " is invalid.");
                        }
                    }
                    else {
                        errors.reject("Item " + dto.getName() + " is invalid.");
                    }
                }
            }

            if (input.getWins() != null) {
                if (input.getWins() < 0 || input.getWins() > 9999999) {
                    errors.reject(input.getWins() + " is an invalid number of wins.");
                }
            }

            if (input.getLosses() != null) {
                if (input.getLosses() < 0 || input.getLosses() > 9999999) {
                    errors.reject(input.getLosses() + " is an invalid number of losses.");
                }
            }

            if (input.getDraws() != null) {
                if (input.getDraws() < 0 || input.getDraws() > 9999999) {
                    errors.reject(input.getDraws() + " is an invalid number of draws.");
                }
            }
        }
        else {
            errors.reject("Trainer " + name + " doesn't exist.");
        }

        return errors;
    }
}
