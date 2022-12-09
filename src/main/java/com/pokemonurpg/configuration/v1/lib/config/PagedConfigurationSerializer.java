package com.pokemonurpg.configuration.v1.lib.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pokemonurpg.configuration.v2.shared.view.ListView;

@SuppressWarnings("rawtypes")
public class PagedConfigurationSerializer extends JsonSerializer<PagedConfiguration> {
	@Override
	public void serialize(PagedConfiguration configuration, JsonGenerator gen, SerializerProvider provider) throws IOException {
        ListView<?> page = configuration.getPage();
		gen.writeStartObject();
        useJsonGeneratorToWritePageFields(gen, page);
        useJsonGeneratorToWritePageContentMaskedByView(gen, page, configuration.getView());
		gen.writeEndObject();
	}

    public void useJsonGeneratorToWritePageFields(JsonGenerator gen, ListView<?> page) throws IOException {
		gen.writeNumberField("page", page.getPage());
		gen.writeNumberField("totalItems", page.getTotalItems());
		gen.writeNumberField("totalPages", page.getTotalPages());
		gen.writeNumberField("size", page.getSize());
    }

    public void useJsonGeneratorToWritePageContentMaskedByView(JsonGenerator gen, ListView<?> page, Class<?> view) throws IOException {
        ObjectMapper mapper = getMapperForView(view);
		gen.writeFieldName("content");
		mapper.writeValue(gen, page.getContent());
    }

    public ObjectMapper getMapperForView(Class<?> view) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setConfig(mapper.getSerializationConfig().withView(view).without(MapperFeature.DEFAULT_VIEW_INCLUSION));
        return mapper;
    }

}