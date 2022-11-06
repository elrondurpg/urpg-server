package com.pokemonurpg.lib.v1;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class GetParams {

    @Min(0)
    Integer page = 1;

    @Min(1)
    @Max(50)
    Integer itemsPerPage = 25;

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
        if (sortBy != null) {
            return PageRequest.of(page, itemsPerPage, Sort.by(sortBy));
        }
        else {
            return PageRequest.of(page, itemsPerPage);
        }
    }

    public String getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(String detailLevel) {
        this.detailLevel = detailLevel;
    }
}
