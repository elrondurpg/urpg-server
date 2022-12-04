package com.pokemonurpg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pokemonurpg.lib.management.v1.FeatureFlagInterceptor;
import com.pokemonurpg.lib.security.v1.SessionCreator;
import com.pokemonurpg.security.interceptor.AuthorizationHandler;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class AppConfig implements WebMvcConfigurer {
    
    // Swagger documentation available at /swagger-ui/index.html
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/configuration.v3.*"))
            .build();
    }

    @Value( "${server.image-base}" )
    private String imageBase;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUsername("urpg_secure");
        dataSource.setPassword(System.getenv("URPG_DB_PASSWORD"));
        dataSource.setUrl("jdbc:mysql://localhost:3306/URPG_DB");

        return dataSource;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*");
    }

    public String getImageBase() {
        return imageBase;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public AuthorizationHandler authenticationInterceptor() {
        return new AuthorizationHandler();
    }

    @Bean
    public com.pokemonurpg.lib.security.v1.PreRequestAuthorizationInterceptor libSecurityV1authenticationInterceptor() {
        return new com.pokemonurpg.lib.security.v1.PreRequestAuthorizationInterceptor();
    }

    @Bean SessionCreator sessionHandlerInterceptor() {
        return new SessionCreator();
    }

    @Bean FeatureFlagInterceptor featureFlagInterceptor() {
        return new FeatureFlagInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePaths = { 
            "/error", 
            "/swagger-ui/**", 
            "/v3/api-docs", 
            "/v3/api-docs/**", 
            "/v2/api-docs", 
            "/v2/api-docs/**",
            "/swagger-resources/**" 
        };
        registry.addInterceptor(authenticationInterceptor()).excludePathPatterns(excludePaths);
        registry.addInterceptor(sessionHandlerInterceptor()).excludePathPatterns(excludePaths);
        registry.addInterceptor(libSecurityV1authenticationInterceptor()).excludePathPatterns(excludePaths);
        registry.addInterceptor(featureFlagInterceptor()).excludePathPatterns(excludePaths);
    }
}
