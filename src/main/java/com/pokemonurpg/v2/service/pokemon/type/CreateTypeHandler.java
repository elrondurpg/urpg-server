package com.pokemonurpg.v2.service.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;

public class CreateTypeHandler implements CreateTypeInputBoundary {

    private TypeDataBoundary dataBoundary;

    public CreateTypeHandler(TypeDataBoundary dataBoundary) {
        this.dataBoundary = dataBoundary;
    }

    public CreateTypeResponse handle(CreateTypeRequest request) {
        Type type = createType(request);
        return createResponse(dataBoundary.create(type));
    }

    private Type createType(CreateTypeRequest request) {
        Type type = new TypeModel();
        type.setName(request.getName());
        return type;
    }

    private CreateTypeResponse createResponse(Type type) {
        return new CreateTypeResponse(type);
    }
}
