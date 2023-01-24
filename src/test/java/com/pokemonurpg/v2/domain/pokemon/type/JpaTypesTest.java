package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.request.PagedListRequest;
import com.pokemonurpg.v2.lib.response.PageResponse;
import com.pokemonurpg.v2.entities.pokemon.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.pokemonurpg.v2.domain.pokemon.type.JpaTypeRepositoryFake.*;
import static org.junit.jupiter.api.Assertions.*;

class JpaTypesTest {

    private JpaTypes entities;
    private JpaTypeRepositoryFake repository;
    private TypeValidatorFake validator;

    @BeforeEach
    public void setup() {
        repository = new JpaTypeRepositoryFake();
        validator = new TypeValidatorFake();
        entities = new JpaTypes(repository, validator);
    }

    @Test
    public void save() {
        TypeModel input = TypeModel.builder()
                .dbid(JpaTypeRepositoryFake.EXISTING_DBID)
                .name(JpaTypeRepositoryFake.EXISTING_NAME)
                .build();

        Type output = entities.save(input);
        assertEquals(JpaTypeRepositoryFake.EXISTING_DBID, repository.getSavedObject().getDbid());
        assertEquals(JpaTypeRepositoryFake.EXISTING_NAME, repository.getSavedObject().getName());
        validateExistingObject(output);
    }

    @Test
    public void save_DoesNothing_WhenInputNull() {
        assertNull(entities.save(null));
    }

    @Test
    public void existsByDbid_ReturnsTrue() {
        assertTrue(entities.existsByDbid(JpaTypeRepositoryFake.EXISTING_DBID));
    }

    @Test
    public void existsByDbid_ReturnsFalse() {
        assertFalse(entities.existsByDbid(JpaTypeRepositoryFake.NOT_FOUND_DBID));
    }

    @Test
    public void existsByName_ReturnsTrue() {
        assertTrue(entities.existsByName(JpaTypeRepositoryFake.EXISTING_NAME));
    }

    @Test
    public void existsByName_ReturnsFalse() {
        assertFalse(entities.existsByName(JpaTypeRepositoryFake.NOT_FOUND_NAME));
    }

    @Test
    public void getByDbid() {
        Type output = entities.getByDbid(JpaTypeRepositoryFake.EXISTING_DBID);
        validateExistingObject(output);
    }

    @Test
    public void getByDbid_ReturnsNull() {
        Type output = entities.getByDbid(JpaTypeRepositoryFake.NOT_FOUND_DBID);
        assertNull(output);
    }

    @Test
    public void getByName() {
        Type output = entities.getByName(JpaTypeRepositoryFake.EXISTING_NAME);
        validateExistingObject(output);
    }

    @Test
    public void getByName_ReturnsNull() {
        Type output = entities.getByName(JpaTypeRepositoryFake.NOT_FOUND_NAME);
        assertNull(output);
    }

    @Test
    public void getByNameStartingWith() {
        Type output = entities.getByNameStartingWith(JpaTypeRepositoryFake.EXISTING_NAME);
        validateExistingObject(output);
    }

    @Test
    public void getByNameStartingWith_ReturnsNull() {
        Type output = entities.getByNameStartingWith(JpaTypeRepositoryFake.NOT_FOUND_NAME);
        assertNull(output);
    }

    @Test
    public void getList_sorted() {
        PagedListRequest<Type> request = PagedListRequest.<Type>builder()
                .itemsPerPage(JpaTypeRepositoryFake.PAGE_SIZE)
                .page(JpaTypeRepositoryFake.PAGE_NUMBER)
                .sortBy(SORT_BY)
                .build();

        PageResponse<? extends Type> response = entities.getPageByExample(request);
        List<? extends Type> items = response.getItems();
        assertEquals(1, items.size());
        validateExistingObject(items.get(0));
        assertEquals(PAGE_NUMBER, response.getPage());
        assertEquals(PAGE_SIZE, response.getItemsPerPage());
        assertEquals(TOTAL_SORTED_ITEMS, response.getTotalItems());
        assertEquals(TOTAL_SORTED_ITEMS / PAGE_SIZE + 1, response.getTotalPages());
    }

    @Test
    public void getList_unsorted() {
        PagedListRequest<Type> request = PagedListRequest.<Type>builder()
                .itemsPerPage(JpaTypeRepositoryFake.PAGE_SIZE)
                .page(JpaTypeRepositoryFake.PAGE_NUMBER)
                .build();

        PageResponse<? extends Type> response = entities.getPageByExample(request);
        List<? extends Type> items = response.getItems();
        assertEquals(2, items.size());
        validateExistingObject(items.get(0));
        validateExistingObject(items.get(1));
        assertEquals(PAGE_NUMBER, response.getPage());
        assertEquals(PAGE_SIZE, response.getItemsPerPage());
        assertEquals(TOTAL_ITEMS, response.getTotalItems());
        assertEquals(TOTAL_ITEMS / PAGE_SIZE + 1, response.getTotalPages());
    }

    @Test
    public void getList_ReturnsEmptyPage() {
        PagedListRequest<Type> request = PagedListRequest.<Type>builder()
                .itemsPerPage(JpaTypeRepositoryFake.PAGE_SIZE)
                .page(PAGE_NUMBER_NOT_FOUND)
                .sortBy(SORT_BY)
                .build();

        PageResponse<? extends Type> response = entities.getPageByExample(request);
        List<? extends Type> items = response.getItems();
        assertEquals(0, items.size());
        assertEquals(0, response.getPage());
        assertEquals(0, response.getItemsPerPage());
        assertEquals(0, response.getTotalItems());
        assertEquals(1, response.getTotalPages());
    }

    @Test
    public void deleteByDbid() {
        Type output = entities.deleteByDbid(JpaTypeRepositoryFake.EXISTING_DBID);
        validateExistingObject(output);
    }

    @Test
    public void deleteByDbid_ReturnsNull() {
        Type output = entities.deleteByDbid(JpaTypeRepositoryFake.NOT_FOUND_DBID);
        assertNull(output);
    }

    private void validateExistingObject(Type object) {
        assertEquals(JpaTypeRepositoryFake.EXISTING_DBID, object.getDbid());
        assertEquals(JpaTypeRepositoryFake.EXISTING_NAME, object.getName());
    }

    @Test
    public void getValidator() {
        assertEquals(validator, entities.getValidator());
    }

}