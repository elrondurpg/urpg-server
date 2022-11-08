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

import com.pokemonurpg.lib.security.v1.SessionCreator;
import com.pokemonurpg.security.interceptor.AuthorizationHandler;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

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

    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(authenticationInterceptor()).excludePathPatterns("/error");
        registry.addInterceptor(sessionHandlerInterceptor());
        registry.addInterceptor(libSecurityV1authenticationInterceptor()).excludePathPatterns("/error");
    }
}
