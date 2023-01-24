package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.PageNotFoundException;
import com.pokemonurpg.v2.lib.request.PagedListRequest;
import com.pokemonurpg.v2.lib.response.PageResponse;
import com.pokemonurpg.v2.lib.validator.Validator;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.Getter;

import java.util.Collections;

@Getter
public class TypesFake implements Types {
    public final static int NEW_DBID = 234;
    public final static String NEW_NAME = "NEW_NAME";

    public final static int EXISTING_DBID = 123;
    public final static String EXISTING_NAME = "EXISTING_NAME";

    public final static int NOT_FOUND_PAGE = 10;
    public final static int TOTAL_PAGES = 5;
    public final static int TOTAL_ITEMS = 50;
    public final static int ITEMS_PER_PAGE = 10;
    public final static int PAGE = 2;

    public final static int NOT_FOUND_DBID = 234;
    public final static String NOT_FOUND_NAME = "NOT_FOUND_NAME";

    private final static TypeValidatorFake VALIDATOR = new TypeValidatorFake();

    private Type savedInput;
    private PagedListRequest<Type> requestedPage;
    private boolean triedFullMatch = false;
    private boolean triedPartialMatch = false;

    public Type save(Type model) {
        savedInput = model;
        return TypeModel.builder()
                .dbid(NEW_DBID)
                .name(NEW_NAME)
                .build();
    }

    public Type getByDbid(int dbid) {
        if (EXISTING_DBID == dbid) {
            return TypeModel.builder()
                    .dbid(EXISTING_DBID)
                    .name(EXISTING_NAME)
                    .build();
        }
        else {
            return null;
        }
    }

    public Type getByName(String name) {
        triedFullMatch = true;
        if (EXISTING_NAME.equals(name)) {
            return TypeModel.builder()
                    .dbid(EXISTING_DBID)
                    .name(EXISTING_NAME)
                    .build();
        }
        else {
            return null;
        }
    }

    public Type getByNameStartingWith(String begin) {
        triedPartialMatch = true;
        if (EXISTING_NAME.startsWith(begin)) {
            return TypeModel.builder()
                    .dbid(EXISTING_DBID)
                    .name(EXISTING_NAME)
                    .build();
        }
        else {
            return null;
        }
    }

    public boolean existsByDbid(int dbid) {
        return EXISTING_DBID == dbid;
    }

    public boolean existsByName(String name) {
        return EXISTING_NAME.equals(name);
    }

    public PageResponse<Type> getPageByExample(PagedListRequest<Type> pagedListRequest) throws PageNotFoundException {
        requestedPage = pagedListRequest;
        if (NOT_FOUND_PAGE == requestedPage.getPage()) {
            throw new PageNotFoundException();
        }
        else {
            return PageResponse.<Type>builder()
                    .totalPages(TOTAL_PAGES)
                    .totalItems(TOTAL_ITEMS)
                    .itemsPerPage(ITEMS_PER_PAGE)
                    .page(PAGE)
                    .items(Collections.singletonList(TypeModel.builder()
                            .dbid(EXISTING_DBID)
                            .name(EXISTING_NAME)
                            .build()))
                    .build();
        }
    }

    public Validator<Type> getValidator() {
        return VALIDATOR;
    }

    public Type deleteByDbid(int dbid) {
        if (EXISTING_DBID == dbid) {
            return TypeModel.builder()
                    .dbid(EXISTING_DBID)
                    .name(EXISTING_NAME)
                    .build();
        }
        else {
            return null;
        }
    }
}
