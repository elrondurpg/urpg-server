package com.pokemonurpg.configuration.v1.lib.config;

import java.io.IOException;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;

public class PagedConfigurationSerializer extends JsonSerializer<PagedConfiguration> {
	@Override
	public void serialize(PagedConfiguration configuration, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Page<?> page = configuration.getPage();
		gen.writeStartObject();
        useJsonGeneratorToWritePageFields(gen, page);
        useJsonGeneratorToWritePageContentMaskedByView(gen, page, configuration.getView());
		gen.writeEndObject();
	}

    public void useJsonGeneratorToWritePageFields(JsonGenerator gen, Page<?> page) throws IOException {
		gen.writeNumberField("page", page.getNumber());
		gen.writeNumberField("itemsPerPage", page.getNumberOfElements());
		gen.writeNumberField("totalItems", page.getTotalElements());
		gen.writeNumberField("totalPages", page.getTotalPages());
		gen.writeNumberField("size", page.getSize());
    }

    public void useJsonGeneratorToWritePageContentMaskedByView(JsonGenerator gen, Page<?> page, Class<? extends ConfigurationViews.V1> view) throws IOException {
        ObjectMapper mapper = getMapperForView(view);
		gen.writeFieldName("content");
		mapper.writeValue(gen, page.getContent());
    }

    public ObjectMapper getMapperForView(Class<? extends ConfigurationViews.V1> view) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setConfig(mapper.getSerializationConfig().withView(view));
        return mapper;
    }

}