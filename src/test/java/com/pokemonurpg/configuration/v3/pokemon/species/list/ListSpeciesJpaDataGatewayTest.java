package com.pokemonurpg.configuration.v3.pokemon.species.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.request.JpaPageableFactoryFake;
import com.pokemonurpg.entities.v1.pokemon.species.Species;
import com.pokemonurpg.entities.v1.shared.PagedEntity;
import com.pokemonurpg.entities.v3.pokemon.species.SpeciesRepositoryFake;

public class ListSpeciesJpaDataGatewayTest {

    private ListSpeciesJpaDataGateway gateway;
    private JpaPageableFactoryFake pageableFactory;
    private SpeciesRepositoryFake repository;

    @BeforeEach
    void setup() {
        pageableFactory = new JpaPageableFactoryFake();
        repository = new SpeciesRepositoryFake();
        gateway = new ListSpeciesJpaDataGateway(pageableFactory, repository);
    }

    @Test
    void testGetWithOwnableOnly() {
        ListSpeciesRequest request = new ListSpeciesRequest();
        request.setOwnableOnly(true);

        PagedEntity<ListSpeciesView> response = gateway.getList(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getFindAllPageableArg());
        assertTrue(isProbeOwnable(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response.getPage());
    }

    boolean isProbeOwnable(Species probe) {
        return !probe.getBattleOnly() && !probe.getMegaEvolved();
    }

    @Test
    void testGetWithStartersOnly() {
        ListSpeciesRequest request = new ListSpeciesRequest();
        request.setStartersOnly(true);

        PagedEntity<ListSpeciesView> response = gateway.getList(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getFindAllPageableArg());
        assertTrue(isProbeStarter(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response.getPage());
    }

    boolean isProbeStarter(Species probe) {
        return !probe.getBattleOnly() && !probe.getEvolved() && !probe.getMegaEvolved() && !probe.getFullyEvolved() && probe.getLegendaryTier() == 0;
    }
}
