package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.exception.PageNotFoundException;
import com.pokemonurpg.v2.domain.lib.request.PageRequest;
import com.pokemonurpg.v2.entities.pokemon.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetTypeHandlerTest {
    private final static int ITEMS_PER_PAGE = 10;
    private final static int PAGE = 0;

    private GetTypeHandler handler;
    private TypesFake entities;

    @BeforeEach
    public void setup() {
        entities = new TypesFake();
        handler = new GetTypeHandler(entities);
    }

    @Test
    public void existsByName() {
        assertTrue(handler.existsByName(TypesFake.EXISTING_NAME));
    }

    @Test
    public void existsByDbid() {
        assertTrue(handler.existsByDbid(TypesFake.EXISTING_DBID));
    }

    @Test
    public void getByName() {
        GetTypeResponse response = handler.getByName(TypesFake.EXISTING_NAME);
        assertEquals(TypesFake.EXISTING_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.EXISTING_OUTPUT.getName(), response.getName());
    }

    @Test
    public void getByName_ReturnsNull() {
        GetTypeResponse response = handler.getByName(TypesFake.NOT_FOUND_NAME);
        assertNull(response);
    }

    @Test
    public void getByNameStartingWith_ReturnsFullMatch() {
        GetTypeResponse response = handler.getByNameStartingWith(TypesFake.EXISTING_NAME);
        assertFalse(entities.isTriedPartialMatch());
        assertEquals(TypesFake.EXISTING_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.EXISTING_OUTPUT.getName(), response.getName());
    }

    @Test
    public void getByNameStartingWith_ReturnsPartialMatch() {
        GetTypeResponse response = handler.getByNameStartingWith(TypesFake.EXISTING_NAME.substring(0, TypesFake.EXISTING_NAME.length() - 1));
        assertTrue(entities.isTriedPartialMatch());
        assertEquals(TypesFake.EXISTING_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.EXISTING_OUTPUT.getName(), response.getName());
    }

    @Test
    public void getByNameStartingWith_ReturnsNull() {
        GetTypeResponse response = handler.getByNameStartingWith(TypesFake.NOT_FOUND_NAME);
        assertTrue(entities.isTriedFullMatch());
        assertTrue(entities.isTriedPartialMatch());
        assertNull(response);
    }

    @Test
    public void getByDbid() {
        GetTypeResponse response = handler.getByDbid(TypesFake.EXISTING_DBID);
        assertEquals(TypesFake.EXISTING_OUTPUT.getDbid(), response.getDbid());
        assertEquals(TypesFake.EXISTING_OUTPUT.getName(), response.getName());
    }

    @Test
    public void getByDbid_ReturnsNull() {
        GetTypeResponse response = handler.getByDbid(TypesFake.NOT_FOUND_DBID);
        assertNull(response);
    }

    @Test
    public void getList() {
        GetTypeListRequest request = GetTypeListRequest.builder()
                .itemsPerPage(ITEMS_PER_PAGE)
                .page(PAGE)
                .build();

        GetTypeListResponse response = handler.getList(request);

        PageRequest<Type> requestedPage = entities.getRequestedPage();
        validateRequestConversionToPageRequest(request, requestedPage);
        validateRequestConversionToExample(requestedPage.getExample());
        validateResponseAttributes(response);
        validateResponseItems(response);
    }

    private void validateRequestConversionToPageRequest(GetTypeListRequest request, PageRequest<Type> requestedPage) {
        assertEquals(request.getItemsPerPage(), requestedPage.getItemsPerPage());
        assertEquals(request.getPage(), requestedPage.getPage());
    }

    private void validateRequestConversionToExample(Type example) {
        assertNull(example.getDbid());
        assertNull(example.getName());
    }

    private void validateResponseAttributes(GetTypeListResponse response) {
        assertEquals(TypesFake.PAGED_TYPES.getTotalPages(), response.getTotalPages());
        assertEquals(TypesFake.PAGED_TYPES.getTotalItems(), response.getTotalItems());
        assertEquals(TypesFake.PAGED_TYPES.getItemsPerPage(), response.getItemsPerPage());
        assertEquals(TypesFake.PAGED_TYPES.getPage(), response.getPage());
        assertEquals(TypesFake.PAGED_TYPES.getItems().size(), response.getItems().size());
    }

    private void validateResponseItems(GetTypeListResponse response) {
        for (int i = 0; i < TypesFake.PAGED_TYPES.getItems().size(); i++) {
            Type item = TypesFake.PAGED_TYPES.getItems().get(i);
            GetTypeListResponseItem responseItem = response.getItems().get(i);
            assertEquals(item.getDbid(), responseItem.getDbid());
            assertEquals(item.getName(), responseItem.getName());
        }
    }

    @Test
    public void getList_WhenPageNotFound() {
        GetTypeListRequest request = GetTypeListRequest.builder()
                .itemsPerPage(ITEMS_PER_PAGE)
                .page(TypesFake.NOT_FOUND_PAGE)
                .build();

        assertThrows(PageNotFoundException.class, () -> handler.getList(request));
    }

}