package com.pokemonurpg.configuration.v1.member.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.knownchampion.service.KnownChampionService;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.service.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.member.member.input.MemberInputTestDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepository;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.service.KnownNameClaimService;
import com.pokemonurpg.member.service.RoleService;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.AuthorizationService;
import com.pokemonurpg.security.service.HashService;
import com.pokemonurpg.stats.repository.OwnedPokemonRepository;
import com.pokemonurpg.test.RandomStringGenerator;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    private final static Member MODEL = new Member();
    private final static String DISCORD_ID = RandomStringGenerator.generate();

    @InjectMocks
    private MemberService service;

    @Mock
    private MemberRepository repository;

    @Mock
    private RoleService roleService;

    @Mock
    private HashService hashService;

    @Mock
    private SystemService systemService;

    @Mock
    private LegendaryProgressService legendaryProgressService;

    @Mock
    private OwnedItemService ownedItemService;

    @Mock
    private AesEncryptionService aesEncryptionService;

    @Mock
    private EliteFourVictoryService eliteFourVictoryService;

    @Mock
    private ChampionVictoryService championVictoryService;

    @Mock
    private GymVictoryService gymVictoryService;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Mock
    private KnownChampionService knownChampionService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private KnownNameClaimService knownNameClaimService;

    @Mock
    private SpeciesService speciesService;

    @Mock
    private CaptureMethodService obtainedService;

    @Mock
    private OwnedPokemonRepository ownedPokemonRepository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Member.class, service.getModelClass());
    }

    @Test
    public void test_findByDiscordId() {
        when(repository.findByDiscordId(DISCORD_ID)).thenReturn(MODEL);
        assertEquals(MODEL, service.findByDiscordId(DISCORD_ID));
    }

    @Test
    public void test_setKeyValues() {
        MemberInputTestDto input = new MemberInputTestDto();
        service.setKeyValues(MODEL, input);
        assertEquals(input.getDiscordId(), MODEL.getDiscordId());
        assertEquals(input.getBot(), MODEL.getBot());
    }
}