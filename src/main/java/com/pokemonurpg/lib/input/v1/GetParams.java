package com.pokemonurpg.lib.input.v1;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class GetParams {
    private final static int DEFAULT_PAGE = 1;
    private final static int DEFAULT_ITEMS_PER_PAGE = 25;

    @Min(0)
    Integer page;

    @Min(1)
    @Max(50)
    Integer itemsPerPage;

    @Pattern(regexp = "^id|brief|full$")
    String detailLevel = "id";

    String sortBy;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    
    public Pageable asPageRequest() {
        if (shouldGetUnpagedResults()) {
            return Pageable.unpaged();
        }
        else {
            return getPagedRequest();
        }
    }

    private boolean shouldGetUnpagedResults() {
        return "id".equals(detailLevel) && page == null && itemsPerPage == null;
    }

    private Pageable getPagedRequest() {
        if (sortBy != null) {
            return PageRequest.of(getPageValueForPagedResults(), getItemsPerPageForPagedResults(), Sort.by(sortBy));
        }
        else {
            return PageRequest.of(getPageValueForPagedResults(), getItemsPerPageForPagedResults());
        }
    }

    private Integer getPageValueForPagedResults() {
        return page == null ? DEFAULT_PAGE : page;
    }

    private Integer getItemsPerPageForPagedResults() {
        return itemsPerPage == null ? DEFAULT_ITEMS_PER_PAGE : itemsPerPage;
    }

    public String getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(String detailLevel) {
        this.detailLevel = detailLevel;
    }
}
