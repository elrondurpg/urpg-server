package com.pokemonurpg.member.service;

import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.HashService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.input.MemberRoleInputDto;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.repository.MemberRepository;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import com.pokemonurpg.stats.input.OwnedItemInputDto;
import com.pokemonurpg.stats.service.EarnedBadgeService;
import com.pokemonurpg.stats.service.LegendaryProgressService;
import com.pokemonurpg.stats.service.OwnedItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    private final static Integer DBID = 32432;
    private final static String DISCORD_ID = "DISCORD_ID";
    private final static String NAME = "TEST";
    private final static Role CURRENT_ROLE = mock(Role.class);
    private final static String CURRENT_ROLE_NAME = "CURRENT_ROLE_NAME";
    private final static Role NEW_ROLE = mock(Role.class);
    private final static String NEW_ROLE_NAME = "NEW_ROLE_NAME";
    private final static Member CURRENT_MEMBER = new Member();
    private final static Integer SALT = 34222;
    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final static Long EXPIRES_IN = 69L;
    private final static Long CURRENT_TIME_MILLIS = 432000L;
    private final static String HASHED_ACCESS_TOKEN = "43242324";
    private final static String HASHED_REFRESH_TOKEN = "432411523562";
    private final static EarnedBadgeInputDto EARNED_BADGE = mock(EarnedBadgeInputDto.class);
    private final static OwnedItemInputDto OWNED_ITEM = mock(OwnedItemInputDto.class);
    private final static LegendaryProgressInputDto LEGENDARY_PROGRESS = mock(LegendaryProgressInputDto.class);
    private final static byte[] IV = { 12, 32 };
    private final static IvParameterSpec IV_PARAMETER_SPEC = new IvParameterSpec(IV);
    private final static SecretKey SECRET_KEY = mock(SecretKey.class);
    private final static String ENCRYPTED_REFRESH_TOKEN = "ENCRYPTED_REFRESH_TOKEN";
    private final static String BOT_ACCESS_TOKEN = "BOT_ACCESS_TOKEN";
    private final static String HASHED_BOT_ACCESS_TOKEN = "HASHED_BOT_ACCESS_TOKEN";

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private HashService hashService;

    @Mock
    private SystemService systemService;

    @Mock
    private EarnedBadgeService earnedBadgeService;

    @Mock
    private LegendaryProgressService legendaryProgressService;

    @Mock
    private OwnedItemService ownedItemService;

    @Mock
    private AesEncryptionService aesEncryptionService;

    private Member member = new Member();

    @Mock
    private RoleService roleService;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(memberRepository.findAllNames()).thenReturn(types);

        assertEquals(types, memberService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(memberRepository.findByDbid(DBID)).thenReturn(member);
        assertEquals(member, memberService.findByDbid(DBID));
    }

    @Test
    public void findByDiscordId() {
        when(memberRepository.findByDiscordId(DISCORD_ID)).thenReturn(member);
        assertEquals(member, memberService.findByDiscordId(DISCORD_ID));
    }

    @Test
    public void findByName() {
        when(memberRepository.findByUsername(NAME)).thenReturn(member);
        assertEquals(member, memberService.findByName(NAME));
    }

    @Test
    public void findByUsernameExactMatch() {
        when(memberRepository.findByUsername(NAME)).thenReturn(member);
        assertEquals(member, memberService.findByUsername(NAME));
    }

    @Test
    public void findByUsernameNotExactMatch() {
        when(memberRepository.findByUsername(NAME)).thenReturn(null);
        when(memberRepository.findFirstByUsernameStartingWith(NAME)).thenReturn(member);
        assertEquals(member, memberService.findByUsername(NAME));
    }

    @Test
    public void create() throws NoSuchAlgorithmException {
        // Given a Role "newRole" known by name "NEW_ROLE_NAME"
        when(roleService.findByName(NEW_ROLE_NAME)).thenReturn(NEW_ROLE);

        // Given a MemberRoleInputDto with name = "NEW_ROLE_NAME"
        MemberRoleInputDto permInput1 = new MemberRoleInputDto();
        permInput1.setName(NEW_ROLE_NAME);

        // Given a MemberInputDto whose "roles" list includes permInput1
        MemberInputDto input = new MemberInputDto();
        input.setUsername(NAME);
        input.setRoles(Collections.singletonList(permInput1));
        input.setBadges(Collections.singletonList(EARNED_BADGE));
        input.setItems(Collections.singletonList(OWNED_ITEM));
        input.setLegendaryProgress(Collections.singletonList(LEGENDARY_PROGRESS));
        input.setBot(true);
        input.setBotAccessToken(BOT_ACCESS_TOKEN);

        when(hashService.hash(any())).thenReturn(HASHED_BOT_ACCESS_TOKEN);

        // When I call memberService.create(input)
        Member member = memberService.create(input);
        assertEquals(NAME, member.getUsername());
        verify(memberRepository, times(1)).save(member);
        verify(earnedBadgeService, times(1)).update(member, EARNED_BADGE);
        verify(ownedItemService, times(1)).update(member, OWNED_ITEM);
        verify(legendaryProgressService, times(1)).update(LEGENDARY_PROGRESS, member);
        assertEquals(HASHED_BOT_ACCESS_TOKEN, member.getAccessToken());

        // Then that member's roles will contain NEW_ROLE
        assertTrue(member.getRoles().contains(NEW_ROLE));
    }

    @Test
    public void updateExistingRecord() {
        // Given a Role "currentRole" known by name "CURRENT_ROLE_NAME"
        when(roleService.findByName(CURRENT_ROLE_NAME)).thenReturn(CURRENT_ROLE);

        // Given a Role "newRole" known by name "NEW_ROLE_NAME"
        when(roleService.findByName(NEW_ROLE_NAME)).thenReturn(NEW_ROLE);

        // Given a member that has role CURRENT_ROLE
        Set<Role> currentRoles = new HashSet<>();
        currentRoles.add(CURRENT_ROLE);
        CURRENT_MEMBER.setRoles(currentRoles);

        // Given a MemberRoleInputDto with name = "NEW_ROLE_NAME"
        MemberRoleInputDto permInput1 = new MemberRoleInputDto();
        permInput1.setName(NEW_ROLE_NAME);

        // Given a MemberRoleInputDto with name = "CURRENT_ROLE_NAME" and delete = true
        MemberRoleInputDto permInput2 = new MemberRoleInputDto();
        permInput2.setName(CURRENT_ROLE_NAME);
        permInput2.setDelete(true);

        // Given a MemberInputDto whose "roles" list includes permInput1 and permInput2
        MemberInputDto input = new MemberInputDto();
        input.setUsername(NAME);
        input.setRoles(Arrays.asList(permInput1, permInput2));

        when(memberRepository.findByDbid(DBID)).thenReturn(CURRENT_MEMBER);

        // When I call memberService.updateAll(input, DBID)
        Member newMember = memberService.update(input, DBID);
        assertEquals(NAME, newMember.getUsername());
        verify(memberRepository, times(1)).save(newMember);

        // Then currentRoles will contain NEW_ROLE and not CURRENT_ROLE
        assertTrue(currentRoles.contains(NEW_ROLE));
        assertFalse(currentRoles.contains(CURRENT_ROLE));
    }

    @Test
    public void updateNonExistingRecord() {
        MemberInputDto input = new MemberInputDto();
        input.setUsername(NAME);

        when(memberRepository.findByDbid(DBID)).thenReturn(null);

        Member member1 = memberService.update(input, DBID);
        assertNull(member1);
        verify(memberRepository, times(0)).save(Matchers.any());
    }

    @Test
    public void updateWithAccessTokenResponse() {
        Member member = new Member();
        member.setSalt(SALT);

        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        accessTokenResponse.setExpiresIn("" + EXPIRES_IN);

        when(hashService.hash(ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);

        when(aesEncryptionService.createIvParameterSpec()).thenReturn(IV_PARAMETER_SPEC);
        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.encrypt(REFRESH_TOKEN, SECRET_KEY, IV_PARAMETER_SPEC)).thenReturn(ENCRYPTED_REFRESH_TOKEN);

        when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME_MILLIS);

        memberService.update(member, accessTokenResponse);
        assertEquals(HASHED_ACCESS_TOKEN, member.getAccessToken());
        assertEquals(ENCRYPTED_REFRESH_TOKEN, member.getRefreshToken());
        assertArrayEquals(IV_PARAMETER_SPEC.getIV(), member.getRefreshTokenIv());

        long expires = EXPIRES_IN + CURRENT_TIME_MILLIS / 1000;
        assertEquals((Long) expires, member.getSessionExpire());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void logout() {
        Member member = new Member();
        member.setRefreshTokenIv(IV);
        member.setRefreshToken(REFRESH_TOKEN);
        member.setAccessToken(ACCESS_TOKEN);

        memberService.logout(member);
        assertNull(member.getRefreshToken());
        assertNull(member.getRefreshTokenIv());
        assertNull(member.getAccessToken());
        verify(memberRepository, times(1)).save(member);
    }
}