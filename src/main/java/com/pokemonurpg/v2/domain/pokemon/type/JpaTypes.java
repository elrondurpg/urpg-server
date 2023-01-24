package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.exception.PageNotFoundException;
import com.pokemonurpg.v2.lib.request.PagedListRequest;
import com.pokemonurpg.v2.lib.response.PageResponse;
import com.pokemonurpg.v2.lib.validator.EntityValidator;
import com.pokemonurpg.v2.lib.validator.Validator;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
class JpaTypes implements Types {
    private JpaTypeRepository repository;
    private EntityValidator<Type> validator;

    @Override
    public Validator<Type> getValidator() {
        return validator;
    }

    @Override
    public Type save(Type entity) {

        if (entity != null) {
            return repository.save(createJpaEntity(entity));
        }
        else {
            return null;
        }
    }

    private JpaTypeModel createJpaEntity(Type entity) {
        JpaTypeModel jpaEntity = new JpaTypeModel();
        jpaEntity.setName(entity.getName());
        jpaEntity.setDbid(entity.getDbid());
        return jpaEntity;
    }

    @Override
    public Type getByDbid(int dbid) {
        return repository.findByDbid(dbid);
    }

    @Override
    public Type getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Type getByNameStartingWith(String begin) {
        return repository.findFirstByNameStartingWith(begin);
    }

    @Override
    public boolean existsByDbid(int dbid) {
        return repository.existsByDbid(dbid);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public PageResponse<JpaTypeModel> getPageByExample(PagedListRequest<Type> pagedListRequest) throws PageNotFoundException {
        Pageable pageable;
        if (pagedListRequest.getSortBy() == null) {
            pageable = PageRequest.of(pagedListRequest.getPage(), pagedListRequest.getItemsPerPage());
        }
        else {
            pageable = PageRequest.of(pagedListRequest.getPage(), pagedListRequest.getItemsPerPage(), Sort.by(pagedListRequest.getSortBy()));
        }
        Page<JpaTypeModel> page = repository.findAll(pageable);
        return createPageResponse(page);
    }

    private PageResponse<JpaTypeModel> createPageResponse(Page<JpaTypeModel> page) {
        return PageResponse.<JpaTypeModel>builder()
                .page(page.getNumber())
                .itemsPerPage(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    @Override
    public Type deleteByDbid(int dbid) {
        return repository.deleteByDbid(dbid);
    }
}
