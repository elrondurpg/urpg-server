package com.pokemonurpg.stats.service;

import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.repository.ItemRepository;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.OwnedItemInputDto;
import com.pokemonurpg.stats.models.OwnedItem;
import com.pokemonurpg.stats.repository.OwnedItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnedItemServiceTest {
    private final static String ITEM_NAME = "ITEM_NAME";
    private final static Item ITEM = mock(Item.class);
    private final static Member MEMBER = mock(Member.class);
    private final static OwnedItem OWNED_ITEM = mock(OwnedItem.class);

    @InjectMocks
    private OwnedItemService ownedItemService;

    @Mock
    private OwnedItemRepository ownedItemRepository;

    @Mock
    private ItemRepository itemRepository;

    @Captor
    private ArgumentCaptor<OwnedItem> captor;

    @Test
    public void createsNewRecord() {
        OwnedItemInputDto input = new OwnedItemInputDto();
        input.setItem(ITEM_NAME);

        when(itemRepository.findByName(ITEM_NAME)).thenReturn(ITEM);
        when(ownedItemRepository.findByTrainerAndItem(MEMBER, ITEM)).thenReturn(null);

        ownedItemService.update(MEMBER, input);

        verify(ownedItemRepository).save(captor.capture());
        assertEquals(MEMBER, captor.getValue().getTrainer());
        assertEquals(ITEM, captor.getValue().getItem());
    }

    @Test
    public void updatesExistingRecord() {
        OwnedItemInputDto input = new OwnedItemInputDto();
        input.setItem(ITEM_NAME);

        when(itemRepository.findByName(ITEM_NAME)).thenReturn(ITEM);
        when(ownedItemRepository.findByTrainerAndItem(MEMBER, ITEM)).thenReturn(OWNED_ITEM);

        ownedItemService.update(MEMBER, input);
        verify(OWNED_ITEM, times(1)).update(input);
        verify(ownedItemRepository, times(1)).save(OWNED_ITEM);
    }

    @Test
    public void deletesExistingRecord() {
        OwnedItemInputDto input = new OwnedItemInputDto();
        input.setItem(ITEM_NAME);
        input.setDelete(true);

        when(itemRepository.findByName(ITEM_NAME)).thenReturn(ITEM);
        when(ownedItemRepository.findByTrainerAndItem(MEMBER, ITEM)).thenReturn(OWNED_ITEM);

        ownedItemService.update(MEMBER, input);
        verify(ownedItemRepository, times(1)).delete(OWNED_ITEM);
    }

}