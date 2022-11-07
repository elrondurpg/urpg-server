package com.pokemonurpg.configuration.v1.lib.controller;

import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews;

public class ConfigControllerDefinition {
    private Class<? extends ConfigurationViews.V1> idViewClass;
    private Class<? extends ConfigurationViews.V1> briefViewClass;
    private Class<? extends ConfigurationViews.V1> fullViewClass;
    private String resourceName;

    private ConfigControllerDefinition() {

    }

    public Class<? extends ConfigurationViews.V1> getIdViewClass() {
        return idViewClass;
    }

    public Class<? extends ConfigurationViews.V1> getBriefViewClass() {
        return briefViewClass;
    }

    public Class<? extends ConfigurationViews.V1> getFullViewClass() {
        return fullViewClass;
    }

    public String getResourceName() {
        return resourceName;
    }

    public static class Builder {
        private ConfigControllerDefinition definition = new ConfigControllerDefinition();

        public Builder withIdViewClass(Class<? extends ConfigurationViews.V1> idViewClass) {
            definition.idViewClass = idViewClass;
            return this;
        }   

        public Builder withBriefViewClass(Class<? extends ConfigurationViews.V1> briefViewClass) {
            definition.briefViewClass = briefViewClass;
            return this;
        }   

        public Builder withFullViewClass(Class<? extends ConfigurationViews.V1> fullViewClass) {
            definition.fullViewClass = fullViewClass;
            return this;
        }   

        public Builder withResourceName(String resourceName) {
            definition.resourceName = resourceName;
            return this;
        }   

        public ConfigControllerDefinition build() {
            return this.definition;
        }
    }
}
