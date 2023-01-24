package com.pokemonurpg.v2;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Arrays;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
        return beanFactory -> genericApplicationContext(
                (BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry)
                        .getBeanFactory());
    }

    void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
        beanDefinitionScanner.addIncludeFilter(removeModelFilter());
        beanDefinitionScanner.scan("com.pokemonurpg.v2.domain");
    }

    static TypeFilter removeModelFilter() {
        String[] fileTypesToFilter = { "Builder", "BuilderImpl", "Constants", "Model", "Request", "Response", "ResponseItem" };
        return (MetadataReader mr, MetadataReaderFactory mrf) -> {
            boolean filter = true;
            for (String fileType : fileTypesToFilter) {
                filter &= !mr.getClassMetadata().getClassName().endsWith(fileType);
            }
            return filter;
        };
    }
}
