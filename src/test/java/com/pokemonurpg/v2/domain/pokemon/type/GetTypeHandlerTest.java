package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.PageNotFoundException;
import com.pokemonurpg.v2.lib.request.PagedListRequest;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundaryFake;
import com.pokemonurpg.v2.entities.pokemon.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class GetTypeHandlerTest {
    private GetTypeHandler handler;
    private TypesFake entities;
    private AuthorizationInputBoundaryFake sessions;

    @BeforeEach
    public void setup() {
        entities = new TypesFake();
        sessions = new AuthorizationInputBoundaryFake();
        handler = new GetTypeHandler(entities, sessions);
    }

    @Test
    public void existsByName_ReturnsTrue() {
        assertTrue(handler.existsByName(TypesFake.EXISTING_NAME));
    }

    @Test
    public void existsByName_ReturnsFalse() {
        assertFalse(handler.existsByName(TypesFake.NOT_FOUND_NAME));
    }

    @Test
    public void existsByDbid_ReturnsTrue() {
        assertTrue(handler.existsByDbid(TypesFake.EXISTING_DBID));
    }

    @Test
    public void existsByDbid_ReturnsFalse() {
        assertFalse(handler.existsByDbid(TypesFake.NOT_FOUND_DBID));
    }

    @Test
    public void getByName() {
        GetTypeResponse response = handler.getByName(TypesFake.EXISTING_NAME);
        assertEquals(TypesFake.EXISTING_DBID, response.getDbid());
        assertEquals(TypesFake.EXISTING_NAME, response.getName());
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
        assertEquals(TypesFake.EXISTING_DBID, response.getDbid());
        assertEquals(TypesFake.EXISTING_NAME, response.getName());
    }

    @Test
    public void getByNameStartingWith_ReturnsPartialMatch() {
        GetTypeResponse response = handler.getByNameStartingWith(TypesFake.EXISTING_NAME.substring(0, TypesFake.EXISTING_NAME.length() - 1));
        assertTrue(entities.isTriedPartialMatch());
        assertEquals(TypesFake.EXISTING_DBID, response.getDbid());
        assertEquals(TypesFake.EXISTING_NAME, response.getName());
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
        assertEquals(TypesFake.EXISTING_DBID, response.getDbid());
        assertEquals(TypesFake.EXISTING_NAME, response.getName());
    }

    @Test
    public void getByDbid_ReturnsNull() {
        GetTypeResponse response = handler.getByDbid(TypesFake.NOT_FOUND_DBID);
        assertNull(response);
    }

    @Test
    public void getList() {
        GetTypeListRequest request = new GetTypeListRequest();
        request.setItemsPerPage(TypesFake.ITEMS_PER_PAGE);
        request.setPage(TypesFake.PAGE);
        request.setSortBy(TypesFake.SORT_BY);

        GetTypeListResponse response = handler.getList(request);

        PagedListRequest<Type> requestedPage = entities.getRequestedPage();
        validateRequestConversionToPageRequest(requestedPage);
        validateRequestConversionToExample(requestedPage.getExample());
        validateResponseAttributes(response);
        validateResponseItems(response);
    }

    private void validateRequestConversionToPageRequest(PagedListRequest<Type> requestedPage) {
        assertEquals(TypesFake.ITEMS_PER_PAGE, requestedPage.getItemsPerPage());
        assertEquals(TypesFake.PAGE, requestedPage.getPage());
        assertEquals(TypesFake.SORT_BY.toLowerCase(), requestedPage.getSortBy());
    }

    private void validateRequestConversionToExample(Type example) {
        assertNull(example.getDbid());
        assertNull(example.getName());
    }

    private void validateResponseAttributes(GetTypeListResponse response) {
        assertEquals(TypesFake.TOTAL_PAGES, response.getTotalPages());
        assertEquals(TypesFake.TOTAL_ITEMS, response.getTotalItems());
        assertEquals(TypesFake.ITEMS_PER_PAGE, response.getItemsPerPage());
        assertEquals(TypesFake.PAGE, response.getPage());
        assertEquals(1, response.getItems().size());
    }

    private void validateResponseItems(GetTypeListResponse response) {
        GetTypeListResponseItem responseItem = response.getItems().get(0);
        assertEquals(TypesFake.EXISTING_DBID, responseItem.getDbid());
        assertEquals(TypesFake.EXISTING_NAME, responseItem.getName());
    }

    @Test
    public void getList_WhenPageNotFound() {
        GetTypeListRequest request = new GetTypeListRequest();
        request.setItemsPerPage(TypesFake.ITEMS_PER_PAGE);
        request.setPage(TypesFake.NOT_FOUND_PAGE);

        assertThrows(PageNotFoundException.class, () -> handler.getList(request));
    }

}