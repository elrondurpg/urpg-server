package com.pokemonurpg.configuration.v3.shared.request;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class JpaPageableFactory {
    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_ITEMS_PER_PAGE = 25;

    public Pageable fromRequest(ListRequest request) {
        if (shouldGetUnpagedResults(request)) {
            return Pageable.unpaged();
        }
        else {
            return getPagedRequest(request);
        }
    }

    private boolean shouldGetUnpagedResults(ListRequest request) {
        return request.getPage() == null && request.getItemsPerPage() == null;
    }

    private Pageable getPagedRequest(ListRequest request) {
        if (request.getSortBy() != null) {
            return PageRequest.of(getPageValueForPagedResults(request.getPage()), getItemsPerPageForPagedResults(request.getItemsPerPage()), Sort.by(request.getSortBy()));
        }
        else {
            return PageRequest.of(getPageValueForPagedResults(request.getPage()), getItemsPerPageForPagedResults(request.getItemsPerPage()));
        }
    }

    private Integer getPageValueForPagedResults(Integer page) {
        return page == null ? DEFAULT_PAGE : page;
    }

    private Integer getItemsPerPageForPagedResults(Integer itemsPerPage) {
        return itemsPerPage == null ? DEFAULT_ITEMS_PER_PAGE : itemsPerPage;
    }
}
