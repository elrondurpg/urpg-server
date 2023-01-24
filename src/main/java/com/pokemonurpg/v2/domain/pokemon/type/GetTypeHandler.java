package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.request.PageRequest;
import com.pokemonurpg.v2.domain.lib.response.PageResponse;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class GetTypeHandler implements GetTypeInputBoundary {
    private Types entities;

    @Override
    public boolean existsByName(String name) {
        return entities.existsByName(name);
    }

    @Override
    public boolean existsByDbid(int dbid) {
        return entities.existsByDbid(dbid);
    }

    @Override
    public GetTypeResponse getByName(String name) {
        Type entity = entities.getByName(name);
        return entity != null ? buildTypeResponse(entities.getByName(name)) : null;
    }

    @Override
    public GetTypeResponse getByNameStartingWith(String name) {
        Type entity = entities.getByName(name);
        if (entity == null) {
            entity = entities.getByNameStartingWith(name);
        }
        return entity != null ? buildTypeResponse(entity) : null;
    }

    @Override
    public GetTypeResponse getByDbid(int dbid) {
        Type entity = entities.getByDbid(dbid);
        return entity != null ? buildTypeResponse(entities.getByDbid(dbid)) : null;
    }

    private GetTypeResponse buildTypeResponse(Type entity) {
        return GetTypeResponse.builder()
                .dbid(entity.getDbid())
                .name(entity.getName())
                .build();
    }

    @Override
    public GetTypeListResponse getList(GetTypeListRequest request) {
        PageRequest<Type> pageRequest = buildPageRequest(request);
        PageResponse<Type> pageResponse = entities.getPageByExample(pageRequest);
        return buildListResponse(pageResponse);
    }

    private PageRequest<Type> buildPageRequest(GetTypeListRequest request) {
        return PageRequest.<Type>builder()
                .itemsPerPage(request.getItemsPerPage())
                .page(request.getPage())
                .example(TypeModel.builder().build())
                .build();
    }

    private GetTypeListResponse buildListResponse(PageResponse<Type> pageResponse) {
        List<GetTypeListResponseItem> items = new ArrayList<>();
        pageResponse.getItems().forEach(item -> items.add(buildListResponseItem(item)));

        return GetTypeListResponse.builder()
                .totalItems(pageResponse.getTotalItems())
                .totalPages(pageResponse.getTotalPages())
                .itemsPerPage(pageResponse.getItemsPerPage())
                .page(pageResponse.getPage())
                .items(items)
                .build();
    }

    private GetTypeListResponseItem buildListResponseItem(Type type) {
        return GetTypeListResponseItem.builder()
                .dbid(type.getDbid())
                .name(type.getName())
                .build();
    }
}
