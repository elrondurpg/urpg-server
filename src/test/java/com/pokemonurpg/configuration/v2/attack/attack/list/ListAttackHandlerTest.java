package com.pokemonurpg.configuration.v2.attack.attack.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.configuration.v2.shared.request.JpaPageableFactoryFake;
import com.pokemonurpg.configuration.v2.shared.view.ListResponse;
import com.pokemonurpg.entities.v1.attack.AttackRepositoryFake;

public class ListAttackHandlerTest {

    private ListAttackHandler handler;
    private AttackRepositoryFake repository;
    private JpaPageableFactoryFake pageableFactory;

    @BeforeEach
    public void setup() {
        repository = new AttackRepositoryFake();
        pageableFactory = new JpaPageableFactoryFake();
        handler = new ListAttackHandler(repository, pageableFactory);
    }

    @Test
    public void test_getList() {
        ListAttackRequest request = new ListAttackRequest();
        ListResponse<ListAttackResponse> response = handler.getList(request);
        assertEquals(AttackRepositoryFake.ATTACKS, response.getPage());
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getFindAllPageableArg());
    }

}
