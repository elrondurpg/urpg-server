package com.pokemonurpg.configuration.v3.pokemon.species.read;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v3.pokemon.species.get.GetPagedSpeciesConfigurationJpaDataGateway;
import com.pokemonurpg.configuration.v3.pokemon.species.get.GetPagedSpeciesConfigurationRequest;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesBriefResponseView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseView;
import com.pokemonurpg.configuration.v3.shared.request.JpaPageableFactoryFake;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.pokemonurpg.entities.v3.pokemon.species.SpeciesEntity;
import com.pokemonurpg.entities.v3.pokemon.species.SpeciesRepositoryFake;

public class GetPagedSpeciesConfigurationJpaDataGatewayTest {

    private GetPagedSpeciesConfigurationJpaDataGateway gateway;
    private JpaPageableFactoryFake pageableFactory;
    private SpeciesRepositoryFake repository;

    @BeforeEach
    void setup() {
        pageableFactory = new JpaPageableFactoryFake();
        repository = new SpeciesRepositoryFake();
        gateway = new GetPagedSpeciesConfigurationJpaDataGateway(pageableFactory, repository);
    }

    @Test
    void testGetWithOwnableOnly() {
        GetPagedSpeciesConfigurationRequest request = new GetPagedSpeciesConfigurationRequest();
        request.setOwnableOnly(true);

        PagedResponse<? extends NamedEntityResponseView> response = gateway.getWithIdView(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getPageableArg());
        assertTrue(isProbeOwnable(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response);
    }

    boolean isProbeOwnable(SpeciesEntity probe) {
        return !probe.getBattleOnly() && !probe.getMegaEvolved();
    }

    @Test
    void testGetWithStartersOnly() {
        GetPagedSpeciesConfigurationRequest request = new GetPagedSpeciesConfigurationRequest();
        request.setStartersOnly(true);

        PagedResponse<? extends NamedEntityResponseView> response = gateway.getWithIdView(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getPageableArg());
        assertTrue(isProbeStarter(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response);
    }

    boolean isProbeStarter(SpeciesEntity probe) {
        return !probe.getBattleOnly() && !probe.getEvolved() && !probe.getMegaEvolved() && !probe.getFullyEvolved() && probe.getLegendaryTier() == 0;
    }

    @Test
    void testGetWithBriefViewWithOwnableOnly() {
        GetPagedSpeciesConfigurationRequest request = new GetPagedSpeciesConfigurationRequest();
        request.setOwnableOnly(true);

        PagedResponse<? extends SpeciesBriefResponseView> response = gateway.getWithBriefView(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getPageableArg());
        assertTrue(isProbeOwnable(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response);
    }

    @Test
    void testGetWithBriefViewStartersOnly() {
        GetPagedSpeciesConfigurationRequest request = new GetPagedSpeciesConfigurationRequest();
        request.setStartersOnly(true);

        PagedResponse<? extends SpeciesBriefResponseView> response = gateway.getWithBriefView(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getPageableArg());
        assertTrue(isProbeStarter(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response);
    }

    @Test
    void testGetWithFullViewOwnableOnly() {
        GetPagedSpeciesConfigurationRequest request = new GetPagedSpeciesConfigurationRequest();
        request.setOwnableOnly(true);

        PagedResponse<? extends SpeciesFullResponseView> response = gateway.getWithFullView(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getPageableArg());
        assertTrue(isProbeOwnable(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response);
    }

    @Test
    void testGetWithFullViewStartersOnly() {
        GetPagedSpeciesConfigurationRequest request = new GetPagedSpeciesConfigurationRequest();
        request.setStartersOnly(true);

        PagedResponse<? extends SpeciesFullResponseView> response = gateway.getWithFullView(request);
        assertEquals(request, pageableFactory.getRequestArg());
        assertEquals(JpaPageableFactoryFake.PAGEABLE, repository.getPageableArg());
        assertTrue(isProbeStarter(repository.getProbeArg()));
        assertTrue(repository.getMatcherArg().isAllMatching());
        assertEquals(SpeciesRepositoryFake.PAGE, response);
    }
}
