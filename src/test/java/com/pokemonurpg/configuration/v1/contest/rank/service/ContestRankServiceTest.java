package com.pokemonurpg.configuration.v1.contest.rank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.contest.rank.model.ContestRank;
import com.pokemonurpg.configuration.v1.contest.rank.repository.ContestRankRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContestRankServiceTest {

    @InjectMocks
    private ContestRankService service;

    @Mock
    private ContestRankRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ContestRank.class, service.getModelClass());
    }

}