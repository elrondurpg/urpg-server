package com.pokemonurpg.configuration.v3.shared.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GetParams {

    @Min(0)
    Integer page;

    @Min(1)
    @Max(50)
    Integer itemsPerPage;

    @Pattern(regexp = "^id|brief|full$")
    String detailLevel = "id";

    String sortBy;
    
    /*
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
    */
}
