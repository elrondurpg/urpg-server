package com.pokemonurpg.account.v1.register.beginner.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pokemonurpg.account.v1.register.common.internal.UpdateMemberAccessTokensHandlerFake;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepositoryFake;
import com.pokemonurpg.configuration.v1.member.member.service.OwnedItemServiceFake;
import com.pokemonurpg.member.repository.KnownNameClaimRepositoryFake;
import com.pokemonurpg.member.repository.RoleRepositoryFake;

import static com.pokemonurpg.account.v1.register.common.internal.CreateMemberConstants.*;

public class CreateBeginnerHandlerTest {
    
    private CreateBeginnerHandler handler;
    private MemberRepositoryFake memberRepository;

    @BeforeEach
    public void setup() {
        memberRepository = new MemberRepositoryFake();

        handler = new CreateBeginnerHandler(
            memberRepository, 
            new RoleRepositoryFake(), 
            new UpdateMemberAccessTokensHandlerFake(), 
            new KnownNameClaimRepositoryFake(), 
            new OwnedItemServiceFake(), 
            new CreateStarterPokemonHandlerFake());
    }

    @Test
    public void test_handle_whenDiscordIdDuplicated() {
        CreateBeginnerRequest request = new CreateBeginnerRequestFake();
        request.setDiscordId(MemberRepositoryFake.DISCORD_ID_FOR_EXISTING_MEMBER);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> handler.handle(request));
        assertEquals(NON_UNIQUE_ID_ERROR, exception.getMessage());
    }

    @Test
    public void test_handle_whenUsernameDuplicated() {
        CreateBeginnerRequest request = new CreateBeginnerRequestFake();
        request.setName(MemberRepositoryFake.NAME_FOR_EXISTING_MEMBER);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> handler.handle(request));
        assertEquals(NON_UNIQUE_USERNAME_ERROR, exception.getMessage());
    }

    @Test
    public void test_handle_whenUsernameIsBeingClaimed() {
        CreateBeginnerRequest request = new CreateBeginnerRequestFake();
        request.setName(KnownNameClaimRepositoryFake.CLAIMED_NAME);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> handler.handle(request));
        assertEquals(USERNAME_BEING_CLAIMED_ERROR, exception.getMessage());
    }

    @Test
    public void test_handle_success() {
        CreateBeginnerRequest request = new CreateBeginnerRequestFake();
        CreateBeginnerResponse response = handler.handle(request);
        verify()
    }

}
