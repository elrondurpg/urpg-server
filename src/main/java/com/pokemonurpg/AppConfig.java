package com.pokemonurpg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Value( "${server.image-base}" )
    private String imageBase;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("urpg_secure");
        dataSource.setPassword(System.getenv("URPG_DB_PASSWORD"));
        dataSource.setUrl("jdbc:mysql://localhost:3306/urpg_db");

        return dataSource;
    }

    public String getImageBase() {
        return imageBase;
    }
}
