package com.pokemonurpg.account.v1.register.newplayer;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.CreatePlayerHandler;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.service.OwnedItemService;

@Service
public class CreateNewPlayerHandler extends CreatePlayerHandler<CreateNewPlayerDto>{

    @Resource
    private OwnedItemService ownedItemService;

    @Resource
    private CreateStarterPokemonHandler createStarterPokemonHandler;

    protected Member create(CreateNewPlayerDto dto) {
        Member member = super.create(dto);
        member.setJoinDate(new Date());
        member.setMoney(15000);
        return member;
    }

    @Override
    protected void doPostSaveActions(Member member, CreateNewPlayerDto dto) {
        addStartingItems(member);
        createStarter(member, dto.getInput());
    }

    private void addStartingItems(Member member) {
        ownedItemService.add(member, "Pokemon Mart Ticket", 3);
        ownedItemService.add(member, "EM Mart Ticket", 3);
        ownedItemService.add(member, "HM Mart Ticket", 1);
        ownedItemService.add(member, "Starter Ribbon Coupon", 1);
        ownedItemService.add(member, "Rare Candy", 5);
    }

    private void createStarter(Member member, RegisterNewPlayerDto input) {
        CreateStarterPokemonDto dto = 
            CreateStarterPokemonDto.builder()
                .member(member)
                .input(input)
                .build();
        createStarterPokemonHandler.handle(dto);
    }
}
